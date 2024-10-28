package xpertss.measure.converter;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ONE;
import static java.math.MathContext.DECIMAL128;


public class DivideConverter extends UnitConverter {

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
   public DivideConverter(BigDecimal factor)
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
      return new MultiplyConverter(factor);
   }

   @Override
   public BigDecimal convert(BigDecimal amount)
   {
      return strip(amount.divide(factor, DECIMAL128));
   }

   @Override
   public boolean isLinear()
   {
      return true;
   }

   @Override
   public UnitConverter concat(UnitConverter converter)
   {
      if(converter instanceof DivideConverter) {
         DivideConverter cvtr = (DivideConverter) converter;
         if(factor.multiply(cvtr.factor).equals(ONE)) return UnitConverter.IDENTITY;
      } else if(converter instanceof MultiplyConverter) {
         MultiplyConverter cvtr = (MultiplyConverter) converter;
         if(factor.equals(cvtr.getFactor())) return UnitConverter.IDENTITY;
      } else if(converter instanceof RationalConverter) {
         RationalConverter rc = (RationalConverter) converter;
         if(factor.multiply(rc.getDivisor()).equals(rc.getDividend())) return UnitConverter.IDENTITY;
      }
      return super.concat(converter);
   }


   public boolean equals(Object obj)
   {
      if(obj == this) return true;
      if(obj instanceof DivideConverter) {
         DivideConverter cvtr = (DivideConverter) obj;
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
