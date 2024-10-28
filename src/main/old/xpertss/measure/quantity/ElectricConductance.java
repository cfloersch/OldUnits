package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents an electric conductance. The system unit for this
 * quantity "S" (Siemens).
 */
public interface ElectricConductance extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<ElectricConductance> UNIT = SI.SIEMENS;

}