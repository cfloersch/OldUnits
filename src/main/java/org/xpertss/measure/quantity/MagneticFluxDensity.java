package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Magnetic flux density. The metric system unit for this quantity is "T" (Tesla).
 */
public interface MagneticFluxDensity extends Quantity<MagneticFluxDensity> {

   public static final Unit<MagneticFluxDensity> UNIT = SI.TESLA;

}
