package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the extent of something along its greatest
 * dimension or the extent of space between two objects or places.
 * The system unit for this quantity is "m" (meter).
 */
public interface Length extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Length> UNIT = SI.METRE;

}