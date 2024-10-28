package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * Quantity of subatomic particles or electromagnetic waves that are energetic
 * enough to detach electrons from atoms or molecules, ionizing them. The system
 * unit for this quantity is "C/kg ("coulomb per kilogram).
 */
public interface IonizingRadiation extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<IonizingRadiation> UNIT = SI.COULOMBS_PER_KILOGRAM;

}
