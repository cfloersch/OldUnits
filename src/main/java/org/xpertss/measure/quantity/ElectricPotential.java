package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Electric potential or electromotive force. The metric system unit for this quantity
 * is "V" (Volt).
 */
public interface ElectricPotential extends Quantity<ElectricPotential> {

   public static final Unit<ElectricPotential> UNIT = SI.VOLT;

}
