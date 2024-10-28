package xpertss.measure.quantity;


import xpertss.measure.unit.ProductUnit;
import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the speed of data-transmission.
 * The system unit for this quantity is "bit/s" (bit per second).
 */
public interface DataRate extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<DataRate> UNIT = new ProductUnit<>(SI.BIT.divide(SI.SECOND));

}