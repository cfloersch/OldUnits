package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the amount of space occupied by a three-dimensional
 * object or region of space, expressed in cubic units. The system unit for
 * this quantity is "m³" (cubic meter).
 */
public interface Volume extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Volume> UNIT = SI.CUBIC_METRE;

}