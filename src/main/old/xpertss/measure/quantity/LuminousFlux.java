package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a luminous flux. The system unit for this quantity
 * is "lm" (lumen).
 */
public interface LuminousFlux extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<LuminousFlux> UNIT = SI.LUMEN;

}