package xpertss.unit.converters;


import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;

/**
 * <p> This class represents a logarithmic converter of limited precision.
 * Such converter  is typically used to create logarithmic unit.
 * For example:[code]
 * Unit<Dimensionless> BEL = Unit.ONE.transform(new LogConverter(10).inverse());
 * [/code]</p>
 * <p/>
 * TODO Impl this with BigMath package
 * TODO Convert to use BigDecimal
 */
public final class LogConverter extends BaseConverter {

   /**
    * Holds the logarithmic base.
    */
   private double base;
   /**
    * Holds the natural logarithm of the base.
    */
   private double logOfBase;

   /**
    * Returns a logarithmic converter having the specified base.
    *
    * @param base the logarithmic base (e.g. <code>Math.E</code> for
    *             the Natural Logarithm).
    */
   public LogConverter(double base)
   {
      this.base = base;
      this.logOfBase = Math.log(base);
   }

   /**
    * Returns the logarithmic base of this converter.
    *
    * @return the logarithmic base (e.g. <code>Math.E</code> for
    * the Natural Logarithm).
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
   public UnitConverter inverse()
   {
      return new ExpConverter(base);
   }



   /*
    public double convert(double amount) {
        return Math.log(amount) / logOfBase;
    }
    */

   @Override
   public BigDecimal convert(BigDecimal value)
   {
      // TODO Impl
      return null;
   }


   @Override
   public boolean equals(Object obj)
   {
      if(!(obj instanceof LogConverter)) return false;
      LogConverter that = (LogConverter) obj;
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
         return "ln";
      } else {
         return "Log(" + base + ")";
      }
   }


}
