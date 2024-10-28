package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Electric capacitance. The metric system unit for this quantity is "F" (Farad).
 */
public interface ElectricCapacitance extends Quantity<ElectricCapacitance> {

   public static final Unit<ElectricCapacitance> UNIT = SI.FARAD;

}
