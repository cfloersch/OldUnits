package xpertss.unit.types;


import java.io.Serializable;

import org.xpertss.measure.Dimension;
import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.UnitConverter;
import org.xpertss.measure.units.SI;

/**
 * <p>  This class represents units formed by the product of rational powers of
 * existing physical units.</p>
 * <p/>
 * <p> This class maintains the canonical form of this product (simplest form
 * after factorization). For example: <code>METRE.pow(2).divide(METRE)</code>
 * returns <code>METRE</code>.</p>
 *
 * @param <Q> The type of the quantity measured by this unit.
 */
public final class ProductUnit<Q extends Quantity<Q>> extends Unit<Q> {

   /**
    * Holds the units composing this product unit.
    */
   private final Element[] elements;

   /**
    * Holds the hashcode (optimization).
    */
   private int hashCode;


   /**
    * Default constructor (used solely to create <code>ONE</code> instance).
    */
   public ProductUnit()
   {
      elements = new Element[0];
   }

   /**
    * Copy constructor (allows for parameterization of product units).
    *
    * @param productUnit the product unit source.
    * @throws ClassCastException if the specified unit is not a product unit.
    */
   public ProductUnit(Unit<?> productUnit)
   {
      this.elements = ((ProductUnit<?>) productUnit).elements;
   }

   /**
    * Product unit constructor.
    *
    * @param elements the product elements.
    */
   private ProductUnit(Element[] elements)
   {
      this.elements = elements;
   }



   @Override
   public Unit<Q> getSystemUnit()
   {
      Unit<?> systemUnit = SI.ONE;
      for(Element element : elements) {
         Unit<?> unit = element.unit.getSystemUnit();
         unit = unit.pow(element.pow);
         unit = unit.root(element.root);
         systemUnit = systemUnit.multiply(unit);
      }
      return (Unit<Q>) systemUnit;
   }

   @Override
   public UnitConverter toSystemUnit()
   {
      UnitConverter converter = UnitConverter.IDENTITY;
      for(Element e : elements) {
         UnitConverter cvtr = e.unit.toSystemUnit();
         if(!(cvtr.isLinear())) throw new UnsupportedOperationException(e.unit + " is non-linear, cannot convert");
         if(e.root != 1)
            throw new UnsupportedOperationException(e.unit + " holds a base unit with fractional exponent");
         int pow = e.pow;
         if(pow < 0) { // Negative power.
            pow = -pow;
            cvtr = cvtr.inverse();
         }
         for(int j = 0; j < pow; j++) {
            converter = converter.concatenate(cvtr);
         }
      }
      return converter;
   }


   @Override
   public Dimension getDimension()
   {
      Dimension dimension = Dimension.NONE;
      for(int i = 0; i < this.getUnitCount(); i++) {
         Unit<?> unit = this.getUnit(i);
         Dimension d = unit.getDimension().pow(this.getUnitPow(i)).root(this.getUnitRoot(i));
         dimension = dimension.multiply(d);
      }
      return dimension;
   }


   /**
    * Returns the product of the specified units.
    *
    * @param left  the left unit operand.
    * @param right the right unit operand.
    * @return <code>left * right</code>
    */
   public static Unit<?> getProductInstance(Unit<?> left, Unit<?> right)
   {
      Element[] leftElems;
      if(left instanceof ProductUnit<?>) leftElems = ((ProductUnit<?>) left).elements;
      else leftElems = new Element[] { new Element(left, 1, 1) };
      Element[] rightElems;
      if(right instanceof ProductUnit<?>) rightElems = ((ProductUnit<?>) right).elements;
      else rightElems = new Element[] { new Element(right, 1, 1) };
      return getInstance(leftElems, rightElems);
   }

   /**
    * Returns the quotient of the specified units.
    *
    * @param left  the dividend unit operand.
    * @param right the divisor unit operand.
    * @return <code>dividend / divisor</code>
    */
   public static Unit<?> getQuotientInstance(Unit<?> left, Unit<?> right)
   {
      Element[] leftElems;
      if(left instanceof ProductUnit<?>) leftElems = ((ProductUnit<?>) left).elements;
      else leftElems = new Element[] { new Element(left, 1, 1) };
      Element[] rightElems;
      if(right instanceof ProductUnit<?>) {
         Element[] elems = ((ProductUnit<?>) right).elements;
         rightElems = new Element[elems.length];
         for(int i = 0; i < elems.length; i++) {
            rightElems[i] = new Element(elems[i].unit, -elems[i].pow, elems[i].root);
         }
      } else rightElems = new Element[] { new Element(right, -1, 1) };
      return getInstance(leftElems, rightElems);
   }

   /**
    * Returns the product unit corresponding to the specified root of the
    * specified unit.
    *
    * @param unit the unit.
    * @param n    the root's order (n &gt; 0).
    * @return <code>unit^(1/nn)</code>
    * @throws ArithmeticException if <code>n == 0</code>.
    */
   public static Unit<?> getRootInstance(Unit<?> unit, int n)
   {
      Element[] unitElems;
      if(unit instanceof ProductUnit<?>) {
         Element[] elems = ((ProductUnit<?>) unit).elements;
         unitElems = new Element[elems.length];
         for(int i = 0; i < elems.length; i++) {
            int gcd = gcd(Math.abs(elems[i].pow), elems[i].root * n);
            unitElems[i] = new Element(elems[i].unit, elems[i].pow / gcd, elems[i].root * n / gcd);
         }
      } else unitElems = new Element[] { new Element(unit, 1, n) };
      return getInstance(unitElems, new Element[0]);
   }

   /**
    * Returns the product unit corresponding to this unit raised to the
    * specified exponent.
    *
    * @param unit the unit.
    * @param n    the exponent (nn &gt; 0).
    * @return <code>unit^n</code>
    */
   static Unit<?> getPowInstance(Unit<?> unit, int n)
   {
      Element[] unitElems;
      if(unit instanceof ProductUnit<?>) {
         Element[] elems = ((ProductUnit<?>) unit).elements;
         unitElems = new Element[elems.length];
         for(int i = 0; i < elems.length; i++) {
            int gcd = gcd(Math.abs(elems[i].pow * n), elems[i].root);
            unitElems[i] = new Element(elems[i].unit, elems[i].pow * n / gcd, elems[i].root / gcd);
         }
      } else unitElems = new Element[] { new Element(unit, n, 1) };
      return getInstance(unitElems, new Element[0]);
   }


   /**
    * Returns the number of unit elements in this product.
    *
    * @return the number of unit elements.
    */
   public int getUnitCount()
   {
      return elements.length;
   }

   /**
    * Returns the unit element at the specified position.
    *
    * @param index the index of the unit element to return.
    * @return the unit element at the specified position.
    * @throws IndexOutOfBoundsException if index is out of range
    *                                   <code>(index &lt; 0 || index &gt;= getUnitCount())</code>.
    */
   public Unit<?> getUnit(int index)
   {
      return elements[index].getUnit();
   }

   /**
    * Returns the power exponent of the unit element at the specified position.
    *
    * @param index the index of the unit element.
    * @return the unit power exponent at the specified position.
    * @throws IndexOutOfBoundsException if index is out of range
    *                                   <code>(index &lt; 0 || index &gt;= getUnitCount())</code>.
    */
   public int getUnitPow(int index)
   {
      return elements[index].getPow();
   }

   /**
    * Returns the root exponent of the unit element at the specified position.
    *
    * @param index the index of the unit element.
    * @return the unit root exponent at the specified position.
    * @throws IndexOutOfBoundsException if index is out of range
    *                                   <code>(index &lt; 0 || index &gt;= getUnitCount())</code>.
    */
   public int getUnitRoot(int index)
   {
      return elements[index].getRoot();
   }


   @Override
   public boolean equals(Object that)
   {
      if(this == that) return true;
      if(!(that instanceof ProductUnit<?>)) return false;
      // Two products are equals if they have the same elements
      // regardless of the elements' order.
      Element[] elems = ((ProductUnit<?>) that).elements;
      if(elements.length != elems.length) return false;
      for(Element e : elements) {
         boolean unitFound = false;
         for(Element elem : elems) {
            if(e.unit.equals(elem.unit)) {
               if((e.pow != elem.pow) || (e.root != elem.root)) {
                  return false;
               } else {
                  unitFound = true;
                  break;
               }
            }
         }
         if(!unitFound) return false;
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      if(this.hashCode != 0) return this.hashCode;
      int code = 0;
      for(Element element : elements) {
         code += element.unit.hashCode() * (element.pow * 3 - element.root * 2);
      }
      this.hashCode = code;
      return code;
   }


   @Override
   public String toString()
   {
      return "Not Yet Impled";   // TODO
   }





   /**
    * Returns the unit defined from the product of the specified elements.
    *
    * @param leftElems  left multiplicand elements.
    * @param rightElems right multiplicand elements.
    * @return the corresponding unit.
    */
   private static Unit<?> getInstance(Element[] leftElems, Element[] rightElems)
   {

      // Merges left elements with right elements.
      Element[] result = new Element[leftElems.length + rightElems.length];
      int resultIndex = 0;
      for(Element leftElem : leftElems) {
         Unit unit = leftElem.unit;
         int p1 = leftElem.pow;
         int r1 = leftElem.root;
         int p2 = 0;
         int r2 = 1;
         for(Element rightElem : rightElems) {
            if(unit.equals(rightElem.unit)) {
               p2 = rightElem.pow;
               r2 = rightElem.root;
               break; // No duplicate.
            }
         }
         int pow = (p1 * r2) + (p2 * r1);
         int root = r1 * r2;
         if(pow != 0) {
            int gcd = gcd(Math.abs(pow), root);
            result[resultIndex++] = new Element(unit, pow / gcd, root / gcd);
         }
      }

      // Appends remaining right elements not merged.
      for(Element rightElem : rightElems) {
         Unit<?> unit = rightElem.unit;
         boolean hasBeenMerged = false;
         for(Element leftElem : leftElems) {
            if(unit.equals(leftElem.unit)) {
               hasBeenMerged = true;
               break;
            }
         }
         if(!hasBeenMerged) result[resultIndex++] = rightElem;
      }

      // Returns or creates instance.
      if(resultIndex == 0) {
         return SI.ONE;
      } else if((resultIndex == 1) && (result[0].pow == result[0].root)) {
         return result[0].unit;
      } else {
         Element[] elems = new Element[resultIndex];
         System.arraycopy(result, 0, elems, 0, resultIndex);
         return new ProductUnit(elems);
      }
   }

   /**
    * Returns the greatest common divisor (Euclid's algorithm).
    *
    * @param m the first number.
    * @param n the second number.
    * @return the greatest common divisor.
    */
   private static int gcd(int m, int n)
   {
      if(n == 0) return m;
      else return gcd(n, m % n);
   }


   /**
    * Inner product element represents a rational power of a single unit.
    */
   private final static class Element implements Serializable {

      /**
       * Holds the single unit.
       */
      private final Unit<?> unit;

      /**
       * Holds the power exponent.
       */
      private final int pow;

      /**
       * Holds the root exponent.
       */
      private final int root;

      /**
       * Structural constructor.
       *
       * @param unit the unit.
       * @param pow  the power exponent.
       * @param root the root exponent.
       */
      private Element(Unit<?> unit, int pow, int root)
      {
         this.unit = unit;
         this.pow = pow;
         this.root = root;
      }

      /**
       * Returns this element's unit.
       *
       * @return the single unit.
       */
      public Unit<?> getUnit()
      {
         return unit;
      }

      /**
       * Returns the power exponent. The power exponent can be negative but is
       * always different from zero.
       *
       * @return the power exponent of the single unit.
       */
      public int getPow()
      {
         return pow;
      }

      /**
       * Returns the root exponent. The root exponent is always greater than
       * zero.
       *
       * @return the root exponent of the single unit.
       */
      public int getRoot()
      {
         return root;
      }
   }
}
