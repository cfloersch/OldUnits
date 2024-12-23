package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Dynamic viscosity. The system unit for this quantity is "Pa.s" (Pascal-Second).
 *
 * @see KinematicViscosity
 * @see <a href="http://en.wikipedia.org/wiki/Viscosity">Wikipedia: Viscosity</a>
 *
 * TODO Beyond spec
 */
public interface DynamicViscosity extends Quantity<DynamicViscosity> {

   public static final Unit<DynamicViscosity> UNIT = SI.PASCAL_SECOND;

}
