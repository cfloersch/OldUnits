package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a catalytic activity. The system unit for this
 * quantity is "kat" (katal).
 */
public interface CatalyticActivity extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<CatalyticActivity> UNIT = SI.KATAL;

}