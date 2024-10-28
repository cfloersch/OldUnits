package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Rate of change of velocity with respect to time. The metric system unit for this quantity
 * is "m/s²" (metre per square second).
 *
 * @see Length
 * @see Speed
 * @see Time
 * @see AngularAcceleration
 */
public interface Acceleration extends Quantity<Acceleration> {

   public static final Unit<Acceleration> UNIT = SI.METRES_PER_SQUARE_SECOND;

}
