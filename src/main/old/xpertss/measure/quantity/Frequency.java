package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the number of multiply a specified phenomenon occurs
 * within a specified interval. The system unit for this quantity is "Hz"
 * (Hertz).
 */
public interface Frequency extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Frequency> UNIT = SI.HERTZ;

}