package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Effective (or "equivalent") dose of radiation received by a human or some other
 * living organism. The metric system unit for this quantity is "Sv" (Sievert).
 */
public interface RadiationDoseEffective extends Quantity<RadiationDoseEffective> {

   public static final Unit<RadiationDoseEffective> UNIT = SI.SIEVERT;

}
