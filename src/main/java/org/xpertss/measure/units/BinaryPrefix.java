package org.xpertss.measure.units;


import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import java.math.BigDecimal;

/**
 * This class provides support for common binary prefixes to be used by
 * units. In computing, such a prefix is seen in combination with a unit
 * of information (bit, byte, etc.), to indicate a power of 1024.
 * <p/>
 * The computer industry has historically used the units kilobyte, megabyte,
 * and gigabyte, and the corresponding symbols KB, MB, and GB, in at least
 * two slightly different measurement systems. To distinguish between cases
 * where these prefixes actually mean units that are multiples of a power of
 * 2 such as 1024 a new set of prefixes was created and standardized in 2008.
 * <p/>
 * <pre>
 *    Unit&lt;Data&gt; availMemory = KIBI(BYTE);
 *    Unit&lt;DataRate&gt; mbps = MEBI(BITS_PER_SECOND);
 * </pre>
 * <p/>
 * A KILOBYTE should mean 1000 bytes. A KIBIBYTE should mean 1024 bytes. The
 * later is more common when it comes to main memory while the former is more
 * common in reference to disk space.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Binary_prefix">Wikipedia:
 * Binary prefix</a>
 */
public final class BinaryPrefix {


   private static final BigDecimal KIBI = new BigDecimal("1024");
   private static final BigDecimal MEBI = new BigDecimal("1048576");
   private static final BigDecimal GIBI = new BigDecimal("1073741824");
   private static final BigDecimal TEBI = new BigDecimal("1099511627776");
   private static final BigDecimal PEBI = new BigDecimal("1125899906842624");
   private static final BigDecimal EXBI = new BigDecimal("1152921504606846976");
   private static final BigDecimal ZEBI = new BigDecimal("1180591620717411303424");
   private static final BigDecimal YOBI = new BigDecimal("1208925819614629174706176");


   /**
    * Default constructor (private).
    */
   private BinaryPrefix()
   {
      // Utility class no visible constructor.
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>2<sup>10</sup></code> (binary prefix).
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1024)</code>.
    */
   public static <Q extends Quantity<Q>> Unit<Q> KIBI(Unit<Q> unit)
   {
      return unit.multiply(KIBI);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>2<sup>20</sup></code> (binary prefix).
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1048576)</code>.
    */
   public static <Q extends Quantity<Q>> Unit<Q> MEBI(Unit<Q> unit)
   {
      return unit.multiply(MEBI);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>2<sup>30</sup></code> (binary prefix).
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1073741824)</code>.
    */
   public static <Q extends Quantity<Q>> Unit<Q> GIBI(Unit<Q> unit)
   {
      return unit.multiply(GIBI);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>2<sup>40</sup></code> (binary prefix).
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1099511627776)</code>.
    */
   public static <Q extends Quantity<Q>> Unit<Q> TEBI(Unit<Q> unit)
   {
      return unit.multiply(TEBI);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>2<sup>50</sup></code> (binary prefix).
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1125899906842624)</code>.
    */
   public static <Q extends Quantity<Q>> Unit<Q> PEBI(Unit<Q> unit)
   {
      return unit.multiply(PEBI);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>2<sup>60</sup></code> (binary prefix).
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1152921504606846976)</code>.
    */
   public static <Q extends Quantity<Q>> Unit<Q> EXBI(Unit<Q> unit)
   {
      return unit.multiply(EXBI);
   }

   /**
    * Returns the specified unit multiplied by the factor
    * <code>2<sup>70</sup></code> (binary prefix).
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1180591620717411303424)</code>.
    */
   public static <Q extends Quantity<Q>> Unit<Q> ZEBI(Unit<Q> unit) { return unit.multiply(ZEBI); }


   /**
    * Returns the specified unit multiplied by the factor
    * <code>2<sup>80</sup></code> (binary prefix).
    *
    * @param unit any unit.
    * @return <code>unit.multiply(1208925819614629174706176)</code>.
    */
   public static <Q extends Quantity<Q>> Unit<Q> YOBI(Unit<Q> unit) { return unit.multiply(YOBI); }

}
