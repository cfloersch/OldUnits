package xpertss.unit.converters;


import java.math.BigDecimal;
import java.util.Objects;
import org.xpertss.measure.UnitConverter;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;

/**
 * This class represents a converter multiplying numeric values by an exact
 * scaling factor (represented as the quotient of two <code>long</code>
 * numbers).
 */
public final class RationalConverter extends BaseConverter {


   /**
    * Holds the converter dividend.
    */
   private BigDecimal dividend;

   /**
    * Holds the converter divisor (always positive).
    */
   private BigDecimal divisor;


   /**
    * Creates a rational converter with the specified dividend and
    * divisor.
    *
    * @param dividend the dividend.
    * @param divisor  the positive divisor.
    * @throws IllegalArgumentException if <code>divisor &lt;= 0</code>
    * @throws IllegalArgumentException if <code>dividend == 0</code>
    * @throws IllegalArgumentException if <code>dividend == divisor</code>
    */
   public RationalConverter(BigDecimal dividend, BigDecimal divisor)
   {
      if(Objects.requireNonNull(divisor).compareTo(ZERO) <= 0)
         throw new IllegalArgumentException("Negative or zero divisor");
      if(equals(divisor, Objects.requireNonNull(dividend)))
         throw new IllegalArgumentException("Would result in identity converter");
      if(equals(ZERO, Objects.requireNonNull(dividend)))
         throw new IllegalArgumentException("Zero dividend not supported");
      this.dividend = clean(dividend); // Exact conversion.
      this.divisor = clean(divisor); // Exact conversion.
   }

   /**
    * Convenience method equivalent to
    * <code>RationalConverter.valueOf(BigInteger.valueOf(dividend), BigInteger.valueOf(divisor))</code>
    *
    * @param dividend the dividend.
    * @param divisor  the positive divisor.
    * @throws IllegalArgumentException if <code>divisor &lt;= 0</code>
    * @throws IllegalArgumentException if <code>dividend == divisor</code>
    */
   public RationalConverter(long dividend, long divisor)
   {
      this(BigDecimal.valueOf(dividend), BigDecimal.valueOf(divisor));
   }

   /**
    * Returns the integer dividend for this rational converter.
    *
    * @return this converter dividend.
    */
   public BigDecimal getDividend()
   {
      return dividend;
   }

   /**
    * Returns the integer (positive) divisor for this rational converter.
    *
    * @return this converter divisor.
    */
   public BigDecimal getDivisor()
   {
      return divisor;
   }


   @Override
   public boolean isLinear()
   {
      return true;
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
   public BigDecimal convert(BigDecimal value)
   {
      return clean(value.multiply(dividend, DECIMAL128).divide(divisor, DECIMAL128));
   }

   @Override
   public UnitConverter concatenate(UnitConverter converter)
   {
      if(converter instanceof MultiplyConverter) {
         MultiplyConverter that = (MultiplyConverter) converter;
         BigDecimal dividend = clean(this.dividend.multiply(that.getFactor(), DECIMAL128));
         if(equals(dividend, this.divisor)) return UnitConverter.IDENTITY;
      } else if(converter instanceof DivideConverter) {
         DivideConverter that = (DivideConverter) converter;
         BigDecimal divisor = clean(this.divisor.multiply(that.getFactor(), DECIMAL128));
         if(equals(divisor, this.dividend)) return UnitConverter.IDENTITY;
      } else if(converter instanceof RationalConverter) {
         RationalConverter that = (RationalConverter) converter;
         BigDecimal dividend = clean(this.dividend.multiply(that.dividend, DECIMAL128));
         BigDecimal divisor = clean(this.divisor.multiply(that.divisor, DECIMAL128));
         if(equals(dividend, divisor)) return UnitConverter.IDENTITY;
         return new RationalConverter(dividend, divisor);
      }
      return super.concatenate(converter);
   }


   @Override
   public boolean equals(Object obj)
   {
      if(!(obj instanceof RationalConverter)) return false;
      RationalConverter that = (RationalConverter) obj;
      return Objects.equals(dividend, that.dividend) &&
               Objects.equals(divisor, that.divisor);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(dividend, divisor);
   }

   @Override
   public final String toString()
   {
      return "RationalConverter(" + dividend + "," + divisor + ")";
   }
}
