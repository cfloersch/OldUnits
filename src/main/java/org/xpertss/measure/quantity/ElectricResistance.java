package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Electric resistance. The metric system unit for this quantity is "Ohm" (Ω).
 */
public interface ElectricResistance extends Quantity<ElectricResistance> {

   public static final Unit<ElectricResistance> UNIT = SI.OHM;

}
