package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Electric inductance. The metric system unit for this quantity is "H" (Henry).
 */
public interface ElectricInductance extends Quantity<ElectricInductance> {

   public static final Unit<ElectricInductance> UNIT = SI.HENRY;

}
