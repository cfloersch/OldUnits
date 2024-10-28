package xpertss.unit.converters;


import java.math.BigDecimal;
import java.util.Objects;
import org.xpertss.measure.UnitConverter;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;

/**
 * This class represents a converter multiplying numeric values by a constant
 * scaling factor.
 */
public final class MultiplyConverter extends BaseConverter {

   /**
    * Holds the scale factor.
    */
   private BigDecimal factor;

   /**
    * Creates a multiply converter with the specified scale factor.
    *
    * @param factor the scaling factor.
    * @throws IllegalArgumentException if coefficient is <code>1.0</code>
    *                                  (would result in identity converter)
    */
   public MultiplyConverter(BigDecimal factor)
   {
      if(equals(ONE, Objects.requireNonNull(factor)))
         throw new IllegalArgumentException("Identity converter not allowed");
      if(equals(ZERO, Objects.requireNonNull(factor)))
         throw new IllegalArgumentException("Zero factor not supported");
      this.factor = clean(factor);
   }

   /**
    * Returns the scale factor of this converter.
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
      return new DivideConverter(factor);
   }


   @Override
   public BigDecimal convert(BigDecimal value)
   {
      return clean(value.multiply(factor, DECIMAL128));
   }


   @Override
   public UnitConverter concatenate(UnitConverter converter)
   {
      if(converter instanceof MultiplyConverter) {
         MultiplyConverter cvtr = (MultiplyConverter) converter;
         if(equals(factor.multiply(cvtr.factor), ONE)) return UnitConverter.IDENTITY;
      } else if(converter instanceof DivideConverter) {
         DivideConverter cvtr = (DivideConverter) converter;
         if(equals(factor, cvtr.getFactor())) return UnitConverter.IDENTITY;
      } else if(converter instanceof RationalConverter) {
         RationalConverter rc = (RationalConverter) converter;
         if(equals(factor.multiply(rc.getDividend()), rc.getDivisor())) return UnitConverter.IDENTITY;
      }
      return super.concatenate(converter);
   }




   @Override
   public boolean equals(Object obj)
   {
      if(obj instanceof MultiplyConverter) {
         MultiplyConverter that = (MultiplyConverter) obj;
         return Objects.equals(this.factor, that.factor);
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return factor.hashCode();
   }

   @Override
   public final String toString()
   {
      return "MultiplyConverter(" + factor + ")";
   }



}
