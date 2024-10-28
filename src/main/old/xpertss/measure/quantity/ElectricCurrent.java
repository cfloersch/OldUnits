package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the amount of electric charge flowing past
 * a specified circuit point per unit time. The system unit for
 * this quantity is "A" (Ampere).
 */
public interface ElectricCurrent extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<ElectricCurrent> UNIT = SI.AMPERE;

}