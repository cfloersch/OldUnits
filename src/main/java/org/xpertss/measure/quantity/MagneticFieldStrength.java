package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Magnetic field strength. The system unit for this quantity is "A/m"
 * (ampere per meter).
 */
public interface MagneticFieldStrength extends Quantity<MagneticFieldStrength> {

   public static final Unit<MagneticFieldStrength> UNIT = SI.AMPERES_PER_METRE;

}
