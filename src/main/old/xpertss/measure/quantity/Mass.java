package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the measure of the quantity of matter that a body
 * or an object contains. The mass of the body is not dependent on gravity
 * and therefore is different from but proportional to its weight.
 * The system unit for this quantity is "kg" (kilogram).
 */
public interface Mass extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Mass> UNIT = SI.KILOGRAM;

}