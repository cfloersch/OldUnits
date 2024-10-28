package xpertss.unit.converters;


import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;

/**
 * <p> This class represents a exponential converter of limited precision.
 * Such converter is used to create inverse of logarithmic unit.
 * <p/>
 * <p> This class is package private, instances are created
 * using the {@link LogConverter#inverse()} method.</p>
 * <p/>
 * TODO Impl this with BigMath package
 */
final class ExpConverter extends BaseConverter {

   /**
    * Holds the logarithmic base.
    */
   private double base;

   /**
    * Holds the natural logarithm of the base.
    */
   private double logOfBase;

   /**
    * Creates a logarithmic converter having the specified base.
    *
    * @param base the logarithmic base (e.g. <code>Math.E</code> for
    *             the Natural Logarithm).
    */
   public ExpConverter(double base)
   {
      this.base = base;
      this.logOfBase = Math.log(base);
   }

   /**
    * Returns the exponential base of this converter.
    *
    * @return the exponential base (e.g. <code>Math.E</code> for
    * the Natural Exponential).
    */
   public double getBase()
   {
      return base;
   }


   @Override
   public boolean isLinear()
   {
      return false;
   }


   @Override
   public BigDecimal convert(BigDecimal value)
      throws ArithmeticException
   {
      return null;
   }


   @Override
   public UnitConverter inverse()
   {
      return new LogConverter(base);
   }


   @Override
   public boolean equals(Object obj)
   {
      if(!(obj instanceof ExpConverter)) return false;
      ExpConverter that = (ExpConverter) obj;
      return this.base == that.base;
   }

   @Override
   public int hashCode()
   {
      long bits = Double.doubleToLongBits(base);
      return (int) (bits ^ (bits >>> 32));
   }


   @Override
   public final String toString()
   {
      if(base == Math.E) {
         return "e";
      } else {
         return "Exp(" + base + ")";
      }
   }


}
