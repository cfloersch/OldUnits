package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents an electric potential or electromotive force.
 * The system unit for this quantity is "V" (Volt).
 */
public interface ElectricPotential extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<ElectricPotential> UNIT = SI.VOLT;

}