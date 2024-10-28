package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Electric conductance. The metric system unit for this quantity "S" (Siemens).
 */
public interface ElectricConductance extends Quantity<ElectricConductance> {

   public static final Unit<ElectricConductance> UNIT = SI.SIEMENS;

}
