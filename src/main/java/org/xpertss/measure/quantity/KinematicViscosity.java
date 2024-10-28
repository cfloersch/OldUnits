package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Diffusion of momentum. The system unit for this quantity is "m²/s".
 *
 * @see DynamicViscosity
 * @see <a href="http://en.wikipedia.org/wiki/Viscosity">Wikipedia: Viscosity</a>
 */
public interface KinematicViscosity extends Quantity<KinematicViscosity> {

   public static final Unit<KinematicViscosity> UNIT = SI.SQUARE_METRES_PER_SECOND;

}
