package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a quantity that tends to produce an acceleration
 * of a body in the direction of its application. The system unit for
 * this quantity is "N" (Newton).
 */
public interface Force extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Force> UNIT = SI.NEWTON;

}