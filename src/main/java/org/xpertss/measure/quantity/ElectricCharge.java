package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Electric charge.  The metric system unit for this quantity is "C" (Coulomb).
 *
 * @see ElectricCurrent
 */
public interface ElectricCharge extends Quantity<ElectricCharge> {

   public static final Unit<ElectricCharge> UNIT = SI.COULOMB;

}
