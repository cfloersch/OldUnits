package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a wave property inversely related to wavelength.
 * The system unit for this quantity is "1/m" (reciprocal meters).
 */
public interface WaveNumber extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<WaveNumber> UNIT = SI.RECIPROCAL_METRE;

}
