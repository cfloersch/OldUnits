package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the rate of change of velocity with respect to
 * time. The system unit for this quantity is "m/s²" (meter per square second).
 */
public interface Acceleration extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Acceleration> UNIT = SI.METRES_PER_SQUARE_SECOND;

}