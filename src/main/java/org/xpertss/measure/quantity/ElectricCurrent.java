package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Amount of electric charge flowing past a specified circuit point per unit time.
 * The metric system unit for this quantity is "A" (Ampere).
 *
 * @see ElectricCharge
 * @see Time
 */
public interface ElectricCurrent extends Quantity<ElectricCurrent> {

   public static final Unit<ElectricCurrent> UNIT = SI.AMPERE;

}
