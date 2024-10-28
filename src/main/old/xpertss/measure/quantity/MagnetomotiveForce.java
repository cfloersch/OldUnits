package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * Force that produces magnetic flux. The metric system unit for this
 * quantity is "At" (ampere-turn).
 */
public interface MagnetomotiveForce extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<MagnetomotiveForce> UNIT = SI.AMPERE_TURN;

}
