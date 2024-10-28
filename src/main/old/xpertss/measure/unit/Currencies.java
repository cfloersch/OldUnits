package xpertss.measure.unit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Currency;
import java.util.HashMap;

/**
 * TODO Rethink this in the context of java.util.Currency
 */
public final class Currencies extends SystemOfUnits {

   /**
    * Holds collection of SI units.
    */
   private static HashMap<String,CurrencyUnit> UNITS = new HashMap<String,CurrencyUnit>();

   /**
    * Returns the unique instance of this class.
    *
    * @return the SI instance.
    */
   public static Currencies getInstance()
   {
      return INSTANCE;
   }

   private static final Currencies INSTANCE = new Currencies();

   private Currencies() { }


   // Main International Currencies
   
   /**
    * The Australian Dollar currency.
    */
   public static final CurrencyUnit AUD = add(new CurrencyUnit("AUD"));

   /**
    * The Canadian Dollar currency.
    */
   public static final CurrencyUnit CAD = add(new CurrencyUnit("CAD"));

   /**
    * The China Yan currency.
    */
   public static final CurrencyUnit CNY = add(new CurrencyUnit("CNY"));

   /**
    * The Euro currency.
    */
   public static final CurrencyUnit EUR = add(new CurrencyUnit("EUR"));

   /**
    * The Indian Rupee currency.
    */
   public static final CurrencyUnit INR = add(new CurrencyUnit("INR"));

   /**
    * The British Pound currency.
    */
   public static final CurrencyUnit GBP = add(new CurrencyUnit("GBP"));

   /**
    * The Japanese Yen currency.
    */
   public static final CurrencyUnit JPY = add(new CurrencyUnit("JPY"));

   /**
    * The Korean Republic Won currency.
    */
   public static final CurrencyUnit KRW = add(new CurrencyUnit("KRW"));

   /**
    * The Russian Ruble currency.
    */
   public static final CurrencyUnit RUB = add(new CurrencyUnit("RUB"));

   /**
    * The Taiwanese dollar currency.
    */
   public static final CurrencyUnit TWD = add(new CurrencyUnit("TWD"));

   /**
    * The United State Dollar currency.
    */
   public static final CurrencyUnit USD = add(new CurrencyUnit("USD"));

   /**
    * The Mexican Peso currency.
    */
   public static final CurrencyUnit MXN = add(new CurrencyUnit("MXN"));



   // ASIA


   /**
    * The Azerbaijanian Manat currency.
    */
   public static final CurrencyUnit AZN = add(new CurrencyUnit("AZN"));

   /**
    * The Armenian Dram currency.
    */
   public static final CurrencyUnit AMD = add(new CurrencyUnit("AMD"));

   /**
    * The Malaysian Ringgit currency.
    */
   public static final CurrencyUnit MYR = add(new CurrencyUnit("MYR"));

   /**
    * The Singapore Dollar currency.
    */
   public static final CurrencyUnit SGD = add(new CurrencyUnit("SGD"));

   /**
    * The Pakistan Rupee currency.
    */
   public static final CurrencyUnit PKR = add(new CurrencyUnit("PKR"));

   /**
    * The Mongolian Tugrik currency.
    */
   public static final CurrencyUnit MNT = add(new CurrencyUnit("MNT"));

   /**
    * The Hong Kong Dollar currency.
    */
   public static final CurrencyUnit HKD = add(new CurrencyUnit("HKD"));

   /**
    * The Bangladeshi Taka currency.
    */
   public static final CurrencyUnit BDT = add(new CurrencyUnit("BDT"));

   /**
    * The Vietnamese D?ng currency.
    */
   public static final CurrencyUnit VND = add(new CurrencyUnit("VND"));

   /**
    * The Uzbekistan Som currency.
    */
   public static final CurrencyUnit UZS = add(new CurrencyUnit("UZS"));

   /**
    * The Georgian Lari currency.
    */
   public static final CurrencyUnit GEL = add(new CurrencyUnit("GEL"));



   // South Pacific

   /**
    * The Philippine Peso currency.
    */
   public static final CurrencyUnit PHP = add(new CurrencyUnit("PHP"));

   /**
    * The New Zealand Dollar currency.
    */
   public static final CurrencyUnit NZD = add(new CurrencyUnit("NZD"));

   /**
    * The Indonesian Rupiah currency.
    */
   public static final CurrencyUnit IDR = add(new CurrencyUnit("IDR"));

   /**
    * The Fiji Dollar currency.
    */
   public static final CurrencyUnit FJD = add(new CurrencyUnit("FJD"));



   // Middle East

   /**
    * The Qatari Rial currency.
    */
   public static final CurrencyUnit QAR = add(new CurrencyUnit("QAR"));

   /**
    * The Saudi Riyal currency.
    */
   public static final CurrencyUnit SAR = add(new CurrencyUnit("SAR"));

   /**
    * The Omani Rial currency.
    */
   public static final CurrencyUnit OMR = add(new CurrencyUnit("OMR"));

   /**
    * The Lebanese Pound currency.
    */
   public static final CurrencyUnit LBP = add(new CurrencyUnit("LBP"));

   /**
    * The Kuwaiti Dinar currency.
    */
   public static final CurrencyUnit KWD = add(new CurrencyUnit("KWD"));

   /**
    * The Jordanian Dinar currency.
    */
   public static final CurrencyUnit JOD = add(new CurrencyUnit("JOD"));

   /**
    * The New Israeli Shekel currency.
    */
   public static final CurrencyUnit ILS = add(new CurrencyUnit("ILS"));

   /**
    * The Egyptian Pound currency.
    */
   public static final CurrencyUnit EGP = add(new CurrencyUnit("EGP"));

   /**
    * The Bahraini Dinar currency.
    */
   public static final CurrencyUnit BHD = add(new CurrencyUnit("BHD"));

   /**
    * The United Arab Emirates Dirham currency.
    */
   public static final CurrencyUnit AED = add(new CurrencyUnit("AED"));

   /**
    * The New Turkish Lira currency.
    */
   public static final CurrencyUnit TRY = add(new CurrencyUnit("TRY"));

   /**
    * The Yemeni Rial currency.
    */
   public static final CurrencyUnit YER = add(new CurrencyUnit("YER"));

   /**
    * The Syrian Pound currency.
    */
   public static final CurrencyUnit SYP = add(new CurrencyUnit("SYP"));



   // Europe

   /**
    * The Bulgarian Lev currency.
    */
   public static final CurrencyUnit BGN = add(new CurrencyUnit("BGN"));

   /**
    * The Albanian Lek currency.
    */
   public static final CurrencyUnit ALL = add(new CurrencyUnit("ALL"));

   /**
    * The Swedish Krona currency.
    */
   public static final CurrencyUnit SEK = add(new CurrencyUnit("SEK"));

   /**
    * The Polish Zloty currency.
    */
   public static final CurrencyUnit PLN = add(new CurrencyUnit("PLN"));

   /**
    * The Norwegian Krone currency.
    */
   public static final CurrencyUnit NOK = add(new CurrencyUnit("NOK"));

   /**
    * The Iceland Krona currency.
    */
   public static final CurrencyUnit ISK = add(new CurrencyUnit("ISK"));

   /**
    * The Danish Krone currency.
    */
   public static final CurrencyUnit DKK = add(new CurrencyUnit("DKK"));

   /**
    * The Ukrainian Hryvnia currency.
    */
   public static final CurrencyUnit UAH = add(new CurrencyUnit("UAH"));

   /**
    * The Hungarian Forint currency.
    */
   public static final CurrencyUnit HUF = add(new CurrencyUnit("HUF"));

   /**
    * The Swiss Franc currency.
    */
   public static final CurrencyUnit CHF = add(new CurrencyUnit("CHF"));



   // Carribbean

   /**
    * The Cayman Islands Dollar currency.
    */
   public static final CurrencyUnit KYD = add(new CurrencyUnit("KYD"));

   /**
    * The Barbados Dollar currency.
    */
   public static final CurrencyUnit BBD = add(new CurrencyUnit("BBD"));

   /**
    * The Cuban Peso currency.
    */
   public static final CurrencyUnit CUP = add(new CurrencyUnit("CUP"));

   /**
    * The Bermuda Dollar currency.
    */
   public static final CurrencyUnit BMD = add(new CurrencyUnit("BMD"));

   /**
    * The Bahamian Dollar currency.
    */
   public static final CurrencyUnit BSD = add(new CurrencyUnit("BSD"));

   /**
    * The Aruban Guilder currency.
    */
   public static final CurrencyUnit AWG = add(new CurrencyUnit("AWG"));



   // South America

   /**
    * The Brazilian Real currency.
    */
   public static final CurrencyUnit BRL = add(new CurrencyUnit("BRL"));

   /**
    * The Argentine Peso currency.
    */
   public static final CurrencyUnit ARS = add(new CurrencyUnit("ARS"));

   /**
    * The Chilean Peso currency.
    */
   public static final CurrencyUnit CLP = add(new CurrencyUnit("CLP"));

   /**
    * The Colombian Peso currency.
    */
   public static final CurrencyUnit COP = add(new CurrencyUnit("COP"));

   /**
    * The Panamanian Balboa currency.
    */
   public static final CurrencyUnit PAB = add(new CurrencyUnit("PAB"));

   /**
    * The Peruvian Nuevo sol currency.
    */
   public static final CurrencyUnit PEN = add(new CurrencyUnit("PEN"));
   
   /**
    * The Venezuelan Bol�var currency.
    */
   public static final CurrencyUnit VEB = add(new CurrencyUnit("VEB"));

   /**
    * The Peso Uruguayo currency.
    */
   public static final CurrencyUnit UYU = add(new CurrencyUnit("UYU"));

   /**
    * The Paraguay Guarani currency.
    */
   public static final CurrencyUnit PYG = add(new CurrencyUnit("PYG"));

   /**
    * The Hondurian Lempira currency.
    */
   public static final CurrencyUnit HNL = add(new CurrencyUnit("HNL"));

   /**
    * The Nicaraguan Cordoba Oro currency.
    */
   public static final CurrencyUnit NIO = add(new CurrencyUnit("NIO"));

   /**
    * The Belize dollar currency.
    */
   public static final CurrencyUnit BZD = add(new CurrencyUnit("BZD"));

   /**
    * The Costa Rican Colon currency.
    */
   public static final CurrencyUnit CRC = add(new CurrencyUnit("CRC"));



   // Africa

   /**
    * The South African Rand currency.
    */
   public static final CurrencyUnit ZAR = add(new CurrencyUnit("ZAR"));
   
   /**
    * The Kenyan Shilling currency.
    */
   public static final CurrencyUnit KES = add(new CurrencyUnit("KES"));








   
   public Set<Unit<?>> getUnits()
   {
      return Collections.unmodifiableSet(new HashSet<Unit<?>>(UNITS.values()));
   }

   /**
    * Adds a new unit to the collection and registers it with the
    * UnitFormat.
    *
    * @param unit the unit being added.
    * @return <code>unit</code>.
    */
   public static CurrencyUnit add(CurrencyUnit unit)
   {
      UNITS.put(unit.getCode(), unit);
      UnitFormat.getInstance().label(unit, unit.getCode());
      return unit;
   }

   public static CurrencyUnit getUnit(String countryCode)
   {
      return UNITS.get(countryCode);
   }



   // http://en.wikipedia.org/wiki/Currency_sign <= Currency Symbols

   public static void main(String[] args)
   {
      Set<Unit<?>> currencies = getInstance().getUnits();
      for(Unit<?> currency : currencies) {
         System.out.println(currency.toString());
         System.out.println(Currency.getInstance(currency.toString()).getSymbol());
      }
   }

}


