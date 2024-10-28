package xpertss.measure.unit;

import xpertss.measure.converter.ConversionException;
import xpertss.measure.converter.UnitConverter;
import xpertss.measure.quantity.Quantity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents units formed by the product of rational powers of existing
 * units.
 * <p/>
 * This class maintains the canonical form of this product (simplest form after
 * factorization). For example:
 * <code>METER.pow(2).divide(METER)</code> returns
 * <code>METER</code>.</p>
 *
 * @see xpertss.measure.unit.Unit#multiply(Unit)
 * @see xpertss.measure.unit.Unit#divide(Unit)
 * @see xpertss.measure.unit.Unit#pow(int)
 * @see xpertss.measure.unit.Unit#root(int)
 */
public final class ProductUnit<Q extends Quantity> extends DerivedUnit<Q> {

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
   ProductUnit()
   {
      elements = new Element[0];
   }

   /**
    * Copy constructor (allows for parameterization of product units).
    *
    * @param productUnit the product unit source.
    * @throws ClassCastException if the specified unit is not
    *                            a product unit.
    */
   public ProductUnit(Unit<?> productUnit)   {
      this.elements = ((ProductUnit<?>) productUnit).elements;
   }

   /**
    * Product unit constructor.
    *
    * @param elements the product elements.
    */
   private ProductUnit(Element[] elements)
   {
      this.elements = Objects.requireNonNull(elements);
   }

   /**
    * Returns the unit defined from the product of the specifed elements.
    *
    * @param leftElems  left multiplicand elements.
    * @param rightElems right multiplicand elements.
    * @return the corresponding unit.
    */
   @SuppressWarnings("unchecked")
   private static Unit<? extends Quantity> getInstance(Element[] leftElems, Element[] rightElems)
   {

      // Merges left elements with right elements.
      Element[] result = new Element[leftElems.length + rightElems.length];
      int resultIndex = 0;
      for(int i = 0; i < leftElems.length; i++) {
         Unit unit = leftElems[i].unit;
         int p1 = leftElems[i].pow;
         int r1 = leftElems[i].root;
         int p2 = 0;
         int r2 = 1;
         for(int j = 0; j < rightElems.length; j++) {
            if(unit.equals(rightElems[j].unit)) {
               p2 = rightElems[j].pow;
               r2 = rightElems[j].root;
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
      for(int i = 0; i < rightElems.length; i++) {
         Unit unit = rightElems[i].unit;
         boolean hasBeenMerged = false;
         for(int j = 0; j < leftElems.length; j++) {
            if(unit.equals(leftElems[j].unit)) {
               hasBeenMerged = true;
               break;
            }
         }
         if(!hasBeenMerged) {
            result[resultIndex++] = rightElems[i];
         }
      }

      // Returns or creates instance.
      if(resultIndex == 0) {
         return ONE;
      } else if((resultIndex == 1) && (result[0].pow == result[0].root)) {
         return result[0].unit;
      } else {
         Element[] elems = new Element[resultIndex];
         System.arraycopy(result, 0, elems, 0, resultIndex);
         return new ProductUnit<Quantity>(elems);
      }
   }

   /**
    * Returns the product of the specified units.
    *
    * @param left  the left unit operand.
    * @param right the right unit operand.
    * @return <code>left * right</code>
    */
   static Unit<? extends Quantity> getProductInstance(Unit<?> left, Unit<?> right)
   {
      Element[] leftElems;
      if(left instanceof ProductUnit) {
         leftElems = ((ProductUnit<?>) left).elements;
      } else {
         leftElems = new Element[] { new Element(left, 1, 1) };
      }
      Element[] rightElems;
      if(right instanceof ProductUnit) {
         rightElems = ((ProductUnit<?>) right).elements;
      } else {
         rightElems = new Element[] { new Element(right, 1, 1) };
      }
      return getInstance(leftElems, rightElems);
   }

   /**
    * Returns the quotient of the specified units.
    *
    * @param left  the dividend unit operand.
    * @param right the divisor unit operand.
    * @return <code>dividend / divisor</code>
    */
   static Unit<? extends Quantity> getQuotientInstance(Unit<?> left, Unit<?> right)
   {
      Element[] leftElems;
      if(left instanceof ProductUnit) {
         leftElems = ((ProductUnit<?>) left).elements;
      } else {
         leftElems = new Element[] { new Element(left, 1, 1) };
      }
      Element[] rightElems;
      if(right instanceof ProductUnit) {
         Element[] elems = ((ProductUnit<?>) right).elements;
         rightElems = new Element[elems.length];
         for(int i = 0; i < elems.length; i++) {
            rightElems[i] = new Element(elems[i].unit, -elems[i].pow, elems[i].root);
         }
      } else {
         rightElems = new Element[] { new Element(right, -1, 1) };
      }
      return getInstance(leftElems, rightElems);
   }

   /**
    * Returns the product unit corresponding to the specified root of
    * the specified unit.
    *
    * @param unit the unit.
    * @param n    the root's order (n &gt; 0).
    * @return <code>unit^(1/nn)</code>
    * @throws ArithmeticException if <code>n == 0</code>.
    */
   static Unit<? extends Quantity> getRootInstance(Unit<?> unit, int n)
   {
      Element[] unitElems;
      if(unit instanceof ProductUnit) {
         Element[] elems = ((ProductUnit<?>) unit).elements;
         unitElems = new Element[elems.length];
         for(int i = 0; i < elems.length; i++) {
            int gcd = gcd(Math.abs(elems[i].pow), elems[i].root * n);
            unitElems[i] = new Element(elems[i].unit, elems[i].pow / gcd, elems[i].root * n / gcd);
         }
      } else {
         unitElems = new Element[] { new Element(unit, 1, n) };
      }
      return getInstance(unitElems, new Element[0]);
   }

   /**
    * Returns the product unit corresponding to this unit raised to
    * the specified exponent.
    *
    * @param unit the unit.
    * @param n   the exponent (n &gt; 0).
    * @return <code>unit^n</code>
    */
   static Unit<? extends Quantity> getPowInstance(Unit<?> unit, int n)
   {
      Element[] unitElems;
      if(unit instanceof ProductUnit) {
         Element[] elems = ((ProductUnit<?>) unit).elements;
         unitElems = new Element[elems.length];
         for(int i = 0; i < elems.length; i++) {
            int gcd = gcd(Math.abs(elems[i].pow * n), elems[i].root);
            unitElems[i] = new Element(elems[i].unit, elems[i].pow * n / gcd, elems[i].root / gcd);
         }
      } else {
         unitElems = new Element[] { new Element(unit, n, 1) };
      }
      return getInstance(unitElems, new Element[0]);
   }

   /**
    * Returns the number of units in this product.
    *
    * @return the number of units being multiplied.
    */
   public int getUnitCount()
   {
      return elements.length;
   }

   /**
    * Returns the unit at the specified position.
    *
    * @param index the index of the unit to return.
    * @return the unit at the specified position.
    * @throws IndexOutOfBoundsException if index is out of range
    *                                   <code>(index &lt; 0 || index &gt;= size())</code>.
    */
   @SuppressWarnings("unchecked")
   public Unit<? extends Quantity> getUnit(int index)
   {
      return elements[index].getUnit();
   }

   /**
    * Returns the power exponent of the unit at the specified position.
    *
    * @param index the index of the unit to return.
    * @return the unit power exponent at the specified position.
    * @throws IndexOutOfBoundsException if index is out of range
    *                                   <code>(index &lt; 0 || index &gt;= size())</code>.
    */
   public int getUnitPow(int index)
   {
      return elements[index].getPow();
   }

   /**
    * Returns the root exponent of the unit at the specified position.
    *
    * @param index the index of the unit to return.
    * @return the unit root exponent at the specified position.
    * @throws IndexOutOfBoundsException if index is out of range
    *                                   <code>(index &lt; 0 || index &gt;= size())</code>.
    */
   public int getUnitRoot(int index)
   {
      return elements[index].getRoot();
   }

   /**
    * Indicates if this product unit is considered equals to the specified
    * object.
    *
    * @param that the object to compare for equality.
    * @return <code>true</code> if <code>this</code> and <code>that</code>
    *         are considered equals; <code>false</code>otherwise.
    */
   public boolean equals(Object that)
   {
      if(this == that) {
         return true;
      }
      if(that instanceof ProductUnit) {
         // Two products are equals if they have the same elements
         // regardless of the elements' order.
         Element[] elems = ((ProductUnit<?>) that).elements;
         if(elements.length == elems.length) {
            for(int i = 0; i < elements.length; i++) {
               boolean unitFound = false;
               for(int j = 0; j < elems.length; j++) {
                  if(elements[i].unit.equals(elems[j].unit)) {
                     if((elements[i].pow != elems[j].pow) || (elements[i].root != elems[j].root)) {
                        return false;
                     } else {
                        unitFound = true;
                        break;
                     }
                  }
               }
               if(!unitFound) {
                  return false;
               }
            }
            return true;
         }
      }
      return false;
   }

   @Override
   // Implements abstract method.
   public int hashCode()
   {
      if(hashCode != 0) {
         return hashCode;
      }
      int code = 0;
      for(int i = 0; i < elements.length; i++) {
         code += elements[i].unit.hashCode() * (elements[i].pow * 3 - elements[i].root * 2);
      }
      hashCode = code;
      return code;
   }

   @Override
   @SuppressWarnings("unchecked")
   public Unit<? super Q> getStandardUnit()
   {
      if(hasOnlyStandardUnit()) {
         return this;
      }
      Unit systemUnit = ONE;
      for(int i = 0; i < elements.length; i++) {
         Unit unit = elements[i].unit.getStandardUnit();
         unit = unit.pow(elements[i].pow);
         unit = unit.root(elements[i].root);
         systemUnit = systemUnit.multiply(unit);
      }
      return systemUnit;
   }

   @Override
   public UnitConverter toStandardUnit()
   {
      if(hasOnlyStandardUnit()) {
         return UnitConverter.IDENTITY;
      }
      UnitConverter converter = UnitConverter.IDENTITY;
      for(int i = 0; i < elements.length; i++) {
         UnitConverter cvtr = elements[i].unit.toStandardUnit();
         if(!cvtr.isLinear()) {
            throw new ConversionException(elements[i].unit + " is non-linear, cannot convert");
         }
         if(elements[i].root != 1) {
            throw new ConversionException(elements[i].unit + " holds a base unit with fractional exponent");
         }
         int pow = elements[i].pow;
         if(pow < 0) { // Negative power.
            pow = -pow;
            cvtr = cvtr.inverse();
         }
         for(int j = 0; j < pow; j++) {
            converter = converter.concat(cvtr);
         }
      }
      return converter;
   }

   /**
    * Indicates if this product unit is a standard unit.
    *
    * @return <code>true</code> if all elements are standard units;
    *         <code>false</code> otherwise.
    */
   private boolean hasOnlyStandardUnit()
   {
      for(int i = 0; i < elements.length; i++) {
         Unit<?> u = elements[i].unit;
         if(!u.isStandardUnit()) {
            return false;
         }
      }
      return true;
   }

   /**
    * Returns the greatest common divisor (Euclid's algorithm).
    *
    * @param m  the first number.
    * @param n the second number.
    * @return the greatest common divisor.
    */
   private static int gcd(int m, int n)
   {
      if(n == 0) {
         return m;
      } else {
         return gcd(n, m % n);
      }
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
       * Returns the power exponent. The power exponent can be negative
       * but is always different from zero.
       *
       * @return the power exponent of the single unit.
       */
      public int getPow()
      {
         return pow;
      }

      /**
       * Returns the root exponent. The root exponent is always greater
       * than zero.
       *
       * @return the root exponent of the single unit.
       */
      public int getRoot()
      {
         return root;
      }

      private static final long serialVersionUID = 1L;
   }

   private static final long serialVersionUID = 1L;
}