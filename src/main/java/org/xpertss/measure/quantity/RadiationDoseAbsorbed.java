package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Amount of energy deposited per unit of mass. The system unit for this quantity
 * is "Gy" (Gray).
 *
 * @see Mass
 */
public interface RadiationDoseAbsorbed extends Quantity<RadiationDoseAbsorbed> {

   public static final Unit<RadiationDoseAbsorbed> UNIT = SI.GRAY;

}
