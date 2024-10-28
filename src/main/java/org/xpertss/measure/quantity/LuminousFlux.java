package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Luminous flux. The metric system unit for this quantity is "lm" (lumen).
 */
public interface LuminousFlux extends Quantity<LuminousFlux> {

   public static final Unit<LuminousFlux> UNIT = SI.LUMEN;

}
