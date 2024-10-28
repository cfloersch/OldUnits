package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the extent of a planar region or of the surface of
 * a solid measured in square units. The system unit for this quantity
 * is "m²" (square meter).
 */
public interface Area extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Area> UNIT = SI.SQUARE_METRE;

}
