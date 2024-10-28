package xpertss.unit.converters;


import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;


public class DivideConverter extends BaseConverter {

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
      if(equals(ONE, Objects.requireNonNull(factor)))
         throw new IllegalArgumentException("Identity converter not allowed");
      if(equals(ZERO, Objects.requireNonNull(factor)))
         throw new IllegalArgumentException("Zero factor not supported");
      this.factor = clean(factor);
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
   public boolean isLinear()
   {
      return true;
   }

   @Override
   public UnitConverter inverse()
   {
      return new MultiplyConverter(factor);
   }

   @Override
   public BigDecimal convert(BigDecimal amount)
   {
      return clean(amount.divide(factor, DECIMAL128));
   }


   @Override
   public UnitConverter concatenate(UnitConverter converter)
   {
      if(converter instanceof DivideConverter) {
         DivideConverter cvtr = (DivideConverter) converter;
         if(equals(factor.multiply(cvtr.factor), ONE)) return UnitConverter.IDENTITY;
      } else if(converter instanceof MultiplyConverter) {
         MultiplyConverter cvtr = (MultiplyConverter) converter;
         if(equals(factor, cvtr.getFactor())) return UnitConverter.IDENTITY;
      } else if(converter instanceof RationalConverter) {
         RationalConverter rc = (RationalConverter) converter;
         if(equals(factor.multiply(rc.getDivisor()), rc.getDividend())) return UnitConverter.IDENTITY;
      }
      return super.concatenate(converter);
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


   @Override
   public final String toString()
   {
      return "DivideConverter(" + factor + ")";
   }


}
