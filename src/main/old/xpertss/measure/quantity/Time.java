package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a period of existence or persistence. The system
 * unit for this quantity is "s" (second).
 */
public interface Time extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Time> UNIT = SI.SECOND;

}
