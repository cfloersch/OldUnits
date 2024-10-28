package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents an electric inductance. The system unit for this
 * quantity is "H" (Henry).
 */
public interface ElectricInductance extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<ElectricInductance> UNIT = SI.HENRY;

}