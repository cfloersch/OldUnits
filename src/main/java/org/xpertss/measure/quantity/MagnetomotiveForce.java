package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Force that produces magnetic flux. The metric system unit for this quantity is "At" (ampere-turn).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Magnetomotive_force">Wikipedia: Magnetomotive Force</a>
 */
public interface MagnetomotiveForce extends Quantity<MagnetomotiveForce> {

   public static final Unit<MagnetomotiveForce> UNIT = SI.AMPERE_TURN;

}
