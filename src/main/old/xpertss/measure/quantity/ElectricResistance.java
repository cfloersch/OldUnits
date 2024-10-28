package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents an electric resistance.
 * The system unit for this quantity is "Ω" (Ohm).
 */
public interface ElectricResistance extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<ElectricResistance> UNIT = SI.OHM;

}