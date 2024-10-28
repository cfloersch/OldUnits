package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a radioactive activity. The system unit for
 * this quantity is "Bq" (Becquerel).
 */
public interface Radioactivity extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Radioactivity> UNIT = SI.BECQUEREL;

}