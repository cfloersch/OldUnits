package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Magnetic flux. The metric system unit for this quantity is "Wb" (Weber).
 */
public interface MagneticFlux extends Quantity<MagneticFlux> {

   public static final Unit<MagneticFlux> UNIT = SI.WEBER;

}
