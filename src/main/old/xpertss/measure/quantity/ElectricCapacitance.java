package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents an electric capacitance. The system unit for this
 * quantity is "F" (Farad).
 */
public interface ElectricCapacitance extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<ElectricCapacitance> UNIT = SI.FARAD;

}