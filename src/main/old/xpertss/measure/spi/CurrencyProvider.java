
package xpertss.measure.spi;


import java.math.BigDecimal;
import java.util.Locale;

/**
 * TODO CurrencyProvider impls should be loaded using the java ServiceLocator
 * system.
 * <p/>
 * TODO I can create a basic impl that uses Yahoo Finance REST API to
 * retrieve near real time exchange rates.
 *
 * https://code.google.com/p/yahoo-finance-managed/
 * http://stackoverflow.com/questions/5777985/need-api-for-currency-converting/21627583#21627583
 * http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22USDLTL%22)&format=json&env=store://datatables.org/alltableswithkeys
 *
 */
public abstract class CurrencyProvider {

   // TODO Build this so that it is bound to a ClassLoader and all children
   // class loaders except those where it is explicitly overriden.

   private static CurrencyProvider PROVIDER;

   public static CurrencyProvider getProvider()
   {
      return PROVIDER;
   }

   public static void setCurrencyProvider(CurrencyProvider provider)
   {
      // TODO Need to have a Permission to allow this
      PROVIDER = provider;
   }


   public abstract String getSymbol(String currencyCode, Locale locale);

   public abstract int getFractionalDigits(String currencyCode);

   public abstract BigDecimal getExchangeRate(String currencyCode);

}
