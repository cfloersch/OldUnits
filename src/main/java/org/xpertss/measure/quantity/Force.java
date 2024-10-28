package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Quantity that tends to produce an acceleration of a body in the direction of its application.
 * The metric system unit for this quantity is "N" (Newton).
 *
 * @see Energy
 * @see Pressure
 * @see Torque
 */
public interface Force extends Quantity<Force> {

   public static final Unit<Force> UNIT = SI.NEWTON;

}
