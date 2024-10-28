package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents an electric charge. The system unit for this
 * quantity is "C" (Coulomb).
 */
public interface ElectricCharge extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<ElectricCharge> UNIT = SI.COULOMB;

}