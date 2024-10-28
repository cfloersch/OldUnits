package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the rate at which work is done. The system unit
 * for this quantity is "W" (Watt).
 */
public interface Power extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Power> UNIT = SI.WATT;

}