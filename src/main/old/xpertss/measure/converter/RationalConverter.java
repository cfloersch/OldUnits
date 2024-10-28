package xpertss.measure.converter;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;

/**
 * This class represents a converter multiplying numeric values by an exact scaling
 * factor (represented as the quotient of two <code>long</code> numbers).
 * <p/>
 * Instances of this class are immutable.
 */
public final class RationalConverter extends UnitConverter {

   /**
    * Holds the converter dividend.
    */
   private final BigDecimal dividend;

   /**
    * Holds the converter divisor (always positive).
    */
   private final BigDecimal divisor;

   /**
    * Creates a rational converter with the specified dividend and
    * divisor.
    *
    * @param dividend the dividend.
    * @param divisor  the positive divisor.
    * @throws IllegalArgumentException if <code>divisor &lt; 0</code> or if
    *                                  <code>dividend == divisor</code>
    */
   public RationalConverter(long dividend, long divisor)
   {
      if(divisor < 0) {
         throw new IllegalArgumentException("Negative divisor");
      }
      if(dividend == divisor) {
         throw new IllegalArgumentException("Identity converter not allowed");
      }
      this.dividend = new BigDecimal(dividend);
      this.divisor = new BigDecimal(divisor);
   }

   public RationalConverter(BigDecimal dividend, BigDecimal divisor)
   {
      if(divisor.compareTo(ZERO) < 0) {
         throw new IllegalArgumentException("Negative divisor");
      }
      if(dividend.equals(divisor)) {
         throw new IllegalArgumentException("Identity converter not allowed");
      }
      this.dividend = Objects.requireNonNull(dividend);
      this.divisor = Objects.requireNonNull(divisor);
   }


   /**
    * Returns the dividend for this rational converter.
    *
    * @return this converter dividend.
    */
   public BigDecimal getDividend()
   {
      return dividend;
   }

   /**
    * Returns the positive divisor for this rational converter.
    *
    * @return this converter divisor.
    */
   public BigDecimal getDivisor()
   {
      return divisor;
   }

   @Override
   public UnitConverter inverse()
   {
      if(dividend.compareTo(ZERO) < 0) {
         return new RationalConverter(divisor.negate(DECIMAL128),
                                       dividend.negate(DECIMAL128));
      }
      return new RationalConverter(divisor, dividend);
   }

   @Override
   public BigDecimal convert(BigDecimal amount)
   {
      return strip(amount.divide(divisor, DECIMAL128).multiply(dividend));
   }

   @Override
   public boolean isLinear()
   {
      return true;
   }

   @Override
   public UnitConverter concat(UnitConverter converter)
   {
      if(converter instanceof MultiplyConverter) {
         return converter.concat(this);
      } else if(converter instanceof RationalConverter) {
         RationalConverter that = (RationalConverter) converter;
         BigDecimal dividend = this.dividend.multiply(that.dividend);
         BigDecimal divisor = this.divisor.multiply(that.divisor);
         if(dividend.equals(divisor)) return UnitConverter.IDENTITY;
      }
      return super.concat(converter);
   }




   public boolean equals(Object obj)
   {
      if(obj == this) return true;
      if(obj instanceof RationalConverter) {
         RationalConverter o = (RationalConverter) obj;
         return Objects.equals(dividend, o.dividend) &&
                  Objects.equals(divisor, o.divisor);
      }
      return false;
   }

   public int hashCode()
   {
      return Objects.hash(dividend, divisor);
   }


   private static final long serialVersionUID = 1L;
}