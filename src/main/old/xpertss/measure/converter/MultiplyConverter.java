package xpertss.measure.converter;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ONE;
import static java.math.MathContext.DECIMAL128;

/**
 * This class represents a converter multiplying numeric values by a constant
 * scaling factor. For exact scaling conversions {@link RationalConverter} is
 * preferred.
 * <p/>
 * Instances of this class are immutable.
 */
public final class MultiplyConverter extends UnitConverter {

   /**
    * Holds the scale factor.
    */
   private final BigDecimal factor;

   /**
    * Creates a multiply converter with the specified scale factor.
    *
    * @param factor the scale factor.
    * @throws IllegalArgumentException if offset is one (or close to one).
    */
   public MultiplyConverter(BigDecimal factor)
   {
      if(ONE.equals(factor)) throw new IllegalArgumentException("Identity converter not allowed");
      this.factor = Objects.requireNonNull(factor);
   }

   /**
    * Returns the scale factor.
    *
    * @return the scale factor.
    */
   public BigDecimal getFactor()
   {
      return factor;
   }

   @Override
   public UnitConverter inverse()
   {
      return new DivideConverter(factor);
   }

   @Override
   public BigDecimal convert(BigDecimal amount)
   {
      return strip(amount.multiply(factor, DECIMAL128));
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
         MultiplyConverter cvtr = (MultiplyConverter) converter;
         if(factor.multiply(cvtr.factor).equals(ONE)) return UnitConverter.IDENTITY;
      } else if(converter instanceof DivideConverter) {
         DivideConverter cvtr = (DivideConverter) converter;
         if(factor.equals(cvtr.getFactor())) return UnitConverter.IDENTITY;
      } else if(converter instanceof RationalConverter) {
         RationalConverter rc = (RationalConverter) converter;
         if(factor.multiply(rc.getDividend()).equals(rc.getDivisor())) return UnitConverter.IDENTITY;
      }
      return super.concat(converter);
   }


   public boolean equals(Object obj)
   {
      if(obj == this) return true;
      if(obj instanceof MultiplyConverter) {
         MultiplyConverter cvtr = (MultiplyConverter) obj;
         return factor.equals(cvtr.factor);
      }
      return false;
   }

   public int hashCode()
   {
      return factor.hashCode();
   }


   private static final long serialVersionUID = 1L;
}