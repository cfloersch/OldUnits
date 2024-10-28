package org.xpertss.measure;


import org.xpertss.measure.quantity.Dimensionless;
import xpertss.unit.types.BaseUnit;
import xpertss.unit.types.ProductUnit;


/**
 * TODO - Should this be an Enum vs a class?
 * <p/>
 * Represents the dimension of a unit.
 * <p/>
 * Concrete dimensions are obtained through the {@link Unit#getDimension()}
 * method.
 * <p/>
 * Two units {@code u1} and {@code u2} are {@link Unit#isCompatible(Unit)
 * compatible} if and only if
 * {@code u1.getDimension().equals(u2.getDimension())}.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Dimensional_analysis">Wikipedia:
 * Dimensional Analysis</a>
 */
public class Dimension {


   /**
    * Holds dimensionless.
    */
   public static final Dimension NONE = new Dimension(new ProductUnit<Dimensionless>());

   /**
    * Holds length dimension (L).
    */
   public static final Dimension LENGTH = new Dimension('L');

   /**
    * Holds mass dimension (M).
    */
   public static final Dimension MASS = new Dimension('M');

   /**
    * Holds time dimension (T).
    */
   public static final Dimension TIME = new Dimension('T');

   /**
    * Holds electric current dimension (I).
    */
   public static final Dimension ELECTRIC_CURRENT = new Dimension('I');

   /**
    * Holds temperature dimension (Θ).
    */
   public static final Dimension TEMPERATURE = new Dimension('Θ');

   /**
    * Holds amount of substance dimension (N).
    */
   public static final Dimension AMOUNT_OF_SUBSTANCE = new Dimension('N');

   /**
    * Holds luminous intensity dimension (J).
    */
   public static final Dimension LUMINOUS_INTENSITY = new Dimension('J');


   /**
    * Holds the pseudo unit associated to this dimension.
    */
   private final Unit<?> pseudoUnit;


   /**
    * Returns the physical dimension having the specified symbol.
    *
    * @param symbol the associated symbol.
    */
   public Dimension(char symbol)
   {
      pseudoUnit = new BaseUnit("[" + symbol + ']', NONE);
   }

   /**
    * Constructor from pseudo-unit (not visible).
    *
    * @param pseudoUnit the pseudo-unit.
    */
   private Dimension(Unit<?> pseudoUnit)
   {
      this.pseudoUnit = pseudoUnit;
   }


   /**
    * Returns the product of this dimension with the one specified.
    *
    * @param that the dimension multiplicand.
    * @return <code>this * that</code>
    */
   public Dimension multiply(Dimension that)
   {
      return new Dimension(this.pseudoUnit.multiply(that.pseudoUnit));
   }

   /**
    * Returns the quotient of this dimension with the one specified.
    *
    * @param that the dimension divisor.
    * @return <code>this.multiply(that.pow(-1))</code>
    */
   public Dimension divide(Dimension that)
   {
      return this.multiply(that.pow(-1));
   }


   /**
    * Returns this dimension raised to an exponent.
    *
    * @param n the exponent.
    * @return the result of raising this dimension to the exponent.
    */
   public final Dimension pow(int n)
   {
      return new Dimension(this.pseudoUnit.pow(n));
   }

   /**
    * Returns the given root of this dimension.
    *
    * @param n the root's order.
    * @return the result of taking the given root of this dimension.
    * @throws ArithmeticException if <code>n == 0</code>.
    */
   public final Dimension root(int n)
   {
      return new Dimension(this.pseudoUnit.root(n));
   }


   @Override
   public boolean equals(Object that)
   {
      return this == that || (that instanceof Dimension) && pseudoUnit.equals(((Dimension) that).pseudoUnit);
   }

   @Override
   public int hashCode()
   {
      return pseudoUnit.hashCode();
   }

   @Override
   public String toString()
   {
      return pseudoUnit.toString();
   }


}
