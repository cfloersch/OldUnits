package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * Luminous intensity per unit area of light traveling in a given direction.
 * The system unit for this quantity is "cd/mÂ²" (candela per square meter).
 */
public interface Luminance extends Quantity {
   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Luminance> UNIT = SI.CANDELAS_PER_SQUARE_METRE;

}
