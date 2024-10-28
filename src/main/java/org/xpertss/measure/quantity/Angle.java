package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Figure formed by two lines diverging from a common point. The metric system unit
 * for this quantity is "rad" (radian).
 *
 * @see SolidAngle
 * @see Length
 * @see AngularSpeed
 */
public interface Angle extends Quantity<Angle> {

   public static final Unit<Angle> UNIT = SI.RADIAN;

}
