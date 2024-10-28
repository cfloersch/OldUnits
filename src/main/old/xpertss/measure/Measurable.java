package xpertss.measure;

import xpertss.measure.quantity.Quantity;
import xpertss.measure.unit.Unit;

import java.math.BigDecimal;

/**
 * This interface represents the measurable, countable, or comparable property
 * or aspect of a thing.
 * <p/>
 * Implementing instances are typically the result of a measurement:
 * {@code
 *   Measurable<Mass> weight = Measure.valueOf(180, POUND);
 * }
 */
public interface Measurable<Q extends Quantity> extends Comparable<Measurable<Q>> {

   /**
    * Returns the unit of this measurable.
    *
    * @return the measurement unit.
    */
   Unit<Q> getUnit();

   /**
    * Returns the value of this measurable as a <code>BigDecimal</code>.
    *
    * @return the measurement value.
    */
   BigDecimal getValue();

   /**
    * Returns the value of this measurable stated in the specified unit as
    * a <code>BigDecimal</code>.
    *
    * @param unit the unit in which this measurable value is stated.
    * @return the numeric value after conversion.
    */
   BigDecimal getValue(Unit<Q> unit);

   /**
    * Returns the measure equivalent to this measure but stated in the
    * specified unit.
    *
    * @param unit the new measurement unit.
    * @return the measure stated in the specified unit.
    */
   Measurable<Q> getAs(Unit<Q> unit);

}
