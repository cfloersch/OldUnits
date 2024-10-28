package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Illuminance. The metric system unit for this quantity is "lx" (lux).
 */
public interface Illuminance extends Quantity<Illuminance> {

   public static final Unit<Illuminance> UNIT = SI.LUX;

}
