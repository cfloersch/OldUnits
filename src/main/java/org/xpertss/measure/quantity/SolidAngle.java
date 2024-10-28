package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Angle formed by three or more planes intersecting at a common point.
 * The metric system unit for this quantity is "sr" (steradian).
 *
 * @see Angle
 */
public interface SolidAngle extends Quantity<SolidAngle> {

   public static final Unit<SolidAngle> UNIT = SI.STERADIAN;

}
