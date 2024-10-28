package xpertss.measure.converter;

import xpertss.measure.spi.CurrencyProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class CurrencyConverter extends UnitConverter {

   private String code;
   private boolean invert;

   public CurrencyConverter(String code)
   {
      this(code, false);
   }

   private CurrencyConverter(String code, boolean invert)
   {
      this.code = Objects.requireNonNull(code);
      this.invert = invert;
   }

   @Override
   public UnitConverter inverse()
   {
      return new CurrencyConverter(code, !invert);
   }

   @Override
   public BigDecimal convert(BigDecimal x)
      throws ConversionException
   {
      // TODO Use ServiceProvider framework
      BigDecimal refAmount = CurrencyProvider.getProvider().getExchangeRate(code);
      if(refAmount == null) throw new ConversionException("Exchange rate not set for " + code);
      return (invert) ? x.divide(refAmount, RoundingMode.HALF_EVEN) : x.multiply(refAmount);
   }

   @Override
   public boolean isLinear()
   {
      return true;
   }

   @Override
   public boolean equals(Object obj)
   {
      if(this == obj) return true;
      if(obj instanceof CurrencyConverter) {
         CurrencyConverter that = (CurrencyConverter) obj;
         return code.equals(that.code) && (invert == that.invert);
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return invert ? code.hashCode() : -code.hashCode();
   }

   private static final long serialVersionUID = 1L;

}
