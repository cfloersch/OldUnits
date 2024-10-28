package xpertss.measure;

import xpertss.measure.quantity.Quantity;
import xpertss.measure.unit.Unit;

import java.math.BigDecimal;

/**
 * This class represents the result of a measurement stated in a known unit.
 * <p/>
 * There is no constraint upon the measurement value itself: scalars, vectors,
 * or even data sets are valid values as long as an aggregate magnitude can be
 * determined (see {@link Measurable}).
 */
public final class Measure {

   /**
    * Default constructor.
    */
   private Measure()
   {
   }



   /**
    * Returns the scalar measure for the specified <code>BigDecimal</code> stated
    * in the specified unit.
    *
    * @param value the measurement value.
    * @param unit  the measurement unit.
    * @return a measurable
    */
   public static <Q extends Quantity> Measurable<Q> valueOf(BigDecimal value, Unit<Q> unit)
   {
      if(value == null) throw new NullPointerException("value can not be null");
      if(unit == null) throw new NullPointerException("unit can not be null");
      return new DecimalMeasure<>(value, unit);
   }

   /**
    * Returns the scalar measure for the specified <code>double</code> stated in
    * the specified unit.
    *
    * @param value the measurement value.
    * @param unit  the measurement unit.
    * @return a measurable
    */
   public static <Q extends Quantity> Measurable<Q> valueOf(double value, Unit<Q> unit)
   {
      if(unit == null) throw new NullPointerException("unit can not be null");
      return new DecimalMeasure<>(new BigDecimal(value), unit);
   }

   /**
    * Returns the scalar measure for the specified <code>float</code> stated in
    * the specified unit.
    *
    * @param value the measurement value.
    * @param unit  the measurement unit.
    * @return a measurable
    */
   public static <Q extends Quantity> Measurable<Q> valueOf(float value, Unit<Q> unit)
   {
      if(unit == null) throw new NullPointerException("unit can not be null");
      return new DecimalMeasure<>(new BigDecimal(value), unit);
   }

   /**
    * Returns the scalar measure for the specified <code>long</code> stated in
    * the specified unit.
    *
    * @param value the measurement value.
    * @param unit  the measurement unit.
    * @return a measurable
    */
   public static <Q extends Quantity> Measurable<Q> valueOf(long value, Unit<Q> unit)
   {
      if(unit == null) throw new NullPointerException("unit can not be null");
      return new DecimalMeasure<>(new BigDecimal(value), unit);
   }

   /**
    * Returns the scalar measure for the specified <code>int</code> stated in
    * the specified unit.
    *
    * @param value the measurement value.
    * @param unit  the measurement unit.
    * @return a measurable
    */
   public static <Q extends Quantity> Measurable<Q> valueOf(int value, Unit<Q> unit)
   {
      if(unit == null) throw new NullPointerException("unit can not be null");
      return new DecimalMeasure<>(new BigDecimal(value), unit);
   }

   /**
    * Returns the scalar measure for the specified <code>String</code> stated in
    * the specified unit.
    *
    * @param value the measurement value.
    * @param unit  the measurement unit.
    * @return a measurable
    * @throws NumberFormatException if value is not a valid decimal.
    */
   public static <Q extends Quantity> Measurable<Q> valueOf(String value, Unit<Q> unit)
   {
      if(value == null) throw new NullPointerException("value can not be null");
      if(unit == null) throw new NullPointerException("unit can not be null");
      return new DecimalMeasure<>(new BigDecimal(value), unit);
   }


}