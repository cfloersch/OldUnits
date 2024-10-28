package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the amount of energy deposited per unit of
 * mass. The system unit for this quantity is "Gy" (Gray).
 */
public interface RadiationDoseAbsorbed extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<RadiationDoseAbsorbed> UNIT = SI.GRAY;

}