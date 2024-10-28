package xpertss.measure.unit;

import xpertss.measure.converter.CurrencyConverter;
import xpertss.measure.converter.UnitConverter;
import xpertss.measure.quantity.Money;

import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

/**
 * This class represents the currency units.
 */
public class CurrencyUnit extends DerivedUnit<Money> {

   /**
    * Holds the converter to the {@link Money#BASE_UNIT money base unit}.
    */
   private final CurrencyConverter toBaseUnit;
   private final String code;

   /**
    * Creates the currency unit for the given currency code. See the
    * <a href="http://en.wikipedia.org/wiki/ISO_4217"> ISO 4217</a>
    * for more information, including a table of currency codes.
    *
    * @param code the ISO-4217 code of the currency (e.g.
    *             <code>"EUR", "USD", "JPY"</code>).
    * @throws IllegalArgumentException if the specified code is not an ISO-4217
    *                                  code.
    */
   public CurrencyUnit(String code)
   {
      this.code = Objects.requireNonNull(code);
      this.toBaseUnit = new CurrencyConverter(code);
   }

   public String getCode()
   {
      return code;
   }

   public String getSymbol()
   {
      return Currency.getInstance(code).getSymbol();
   }

   public String getSymbol(Locale locale)
   {
      return Currency.getInstance(code).getSymbol(locale);
   }

   public int getFractionalDigits()
   {
      return Currency.getInstance(code).getDefaultFractionDigits();
   }

   @Override
   public boolean equals(Object obj)
   {
      if(this == obj) return true;
      if(obj instanceof CurrencyUnit) {
         CurrencyUnit that = (CurrencyUnit) obj;
         return toBaseUnit.equals(that.toBaseUnit);
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return toBaseUnit.hashCode();
   }

   @Override
   public Unit<? super Money> getStandardUnit()
   {
      return Money.BASE_UNIT;
   }

   @Override
   public UnitConverter toStandardUnit()
   {
      return toBaseUnit;
   }

   private static final long serialVersionUID = 1L;

}
