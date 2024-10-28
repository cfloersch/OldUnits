package xpertss.measure.unit;

import xpertss.measure.converter.MultiplyConverter;
import xpertss.measure.converter.RationalConverter;
import xpertss.measure.quantity.Quantity;

import java.math.BigDecimal;


/**
 * This class provides support for common SI (Système International d'Unités) prefixes
 * to be used by units.
 * <p/>
 * <pre>
 *    Unit&lt;Length&gt; distance = KILO(METER);
 *    Unit&lt;Mass&gt; mass = MICRO(GRAM);
 * </pre>
 *
 * @see <a href="http://en.wikipedia.org/wiki/SI_prefix">Wikipedia: SI prefix</a>
 */
public final class SIPrefix {
   private SIPrefix() { }


   /////////////////
   // SI PREFIXES //
   /////////////////

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>24</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e24)</code>.
    */
   public static <Q extends Quantity> Unit<Q> YOTTA(Unit<Q> unit)
   {
      return unit.transform(E24);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>21</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e21)</code>.
    */
   public static <Q extends Quantity> Unit<Q> ZETTA(Unit<Q> unit)
   {
      return unit.transform(E21);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>18</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e18)</code>.
    */
   public static <Q extends Quantity> Unit<Q> EXA(Unit<Q> unit)
   {
      return unit.transform(E18);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>15</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e15)</code>.
    */
   public static <Q extends Quantity> Unit<Q> PETA(Unit<Q> unit)
   {
      return unit.transform(E15);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>12</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e12)</code>.
    */
   public static <Q extends Quantity> Unit<Q> TERA(Unit<Q> unit)
   {
      return unit.transform(E12);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>9</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e9)</code>.
    */
   public static <Q extends Quantity> Unit<Q> GIGA(Unit<Q> unit)
   {
      return unit.transform(E9);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>6</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e6)</code>.
    */
   public static <Q extends Quantity> Unit<Q> MEGA(Unit<Q> unit)
   {
      return unit.transform(E6);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>3</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e3)</code>.
    */
   public static <Q extends Quantity> Unit<Q> KILO(Unit<Q> unit)
   {
      return unit.transform(E3);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>2</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e2)</code>.
    */
   public static <Q extends Quantity> Unit<Q> HECTO(Unit<Q> unit)
   {
      return unit.transform(E2);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>1</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e1)</code>.
    */
   public static <Q extends Quantity> Unit<Q> DEKA(Unit<Q> unit)
   {
      return unit.transform(E1);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-1</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-1)</code>.
    */
   public static <Q extends Quantity> Unit<Q> DECI(Unit<Q> unit)
   {
      return unit.transform(Em1);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-2</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-2)</code>.
    */
   public static <Q extends Quantity> Unit<Q> CENTI(Unit<Q> unit)
   {
      return unit.transform(Em2);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-3</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-3)</code>.
    */
   public static <Q extends Quantity> Unit<Q> MILLI(Unit<Q> unit)
   {
      return unit.transform(Em3);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-6</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-6)</code>.
    */
   public static <Q extends Quantity> Unit<Q> MICRO(Unit<Q> unit)
   {
      return unit.transform(Em6);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-9</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-9)</code>.
    */
   public static <Q extends Quantity> Unit<Q> NANO(Unit<Q> unit)
   {
      return unit.transform(Em9);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-12</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-12)</code>.
    */
   public static <Q extends Quantity> Unit<Q> PICO(Unit<Q> unit)
   {
      return unit.transform(Em12);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-15</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-15)</code>.
    */
   public static <Q extends Quantity> Unit<Q> FEMTO(Unit<Q> unit)
   {
      return unit.transform(Em15);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-18</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-18)</code>.
    */
   public static <Q extends Quantity> Unit<Q> ATTO(Unit<Q> unit)
   {
      return unit.transform(Em18);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-21</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-21)</code>.
    */
   public static <Q extends Quantity> Unit<Q> ZEPTO(Unit<Q> unit)
   {
      return unit.transform(Em21);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>10<sup>-24</sup></code>
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1e-24)</code>.
    */
   public static <Q extends Quantity> Unit<Q> YOCTO(Unit<Q> unit)
   {
      return unit.transform(Em24);
   }


   // Holds prefix converters (optimization).

   static final MultiplyConverter E24 = new MultiplyConverter(new BigDecimal(1E24));

   static final MultiplyConverter E21 = new MultiplyConverter(new BigDecimal(1E21));

   static final RationalConverter E18 = new RationalConverter(1000000000000000000L, 1);

   static final RationalConverter E15 = new RationalConverter(1000000000000000L, 1);

   static final RationalConverter E12 = new RationalConverter(1000000000000L, 1);

   static final RationalConverter E9 = new RationalConverter(1000000000L, 1);

   static final RationalConverter E6 = new RationalConverter(1000000L, 1);

   static final RationalConverter E3 = new RationalConverter(1000L, 1);

   static final RationalConverter E2 = new RationalConverter(100L, 1);

   static final RationalConverter E1 = new RationalConverter(10L, 1);

   static final RationalConverter Em1 = new RationalConverter(1, 10L);

   static final RationalConverter Em2 = new RationalConverter(1, 100L);

   static final RationalConverter Em3 = new RationalConverter(1, 1000L);

   static final RationalConverter Em6 = new RationalConverter(1, 1000000L);

   static final RationalConverter Em9 = new RationalConverter(1, 1000000000L);

   static final RationalConverter Em12 = new RationalConverter(1, 1000000000000L);

   static final RationalConverter Em15 = new RationalConverter(1, 1000000000000000L);

   static final RationalConverter Em18 = new RationalConverter(1, 1000000000000000000L);

   static final MultiplyConverter Em21 = new MultiplyConverter(new BigDecimal(1E-21));

   static final MultiplyConverter Em24 = new MultiplyConverter(new BigDecimal(1E-24));

}
