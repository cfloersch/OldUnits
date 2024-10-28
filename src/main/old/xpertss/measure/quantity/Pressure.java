package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a force applied uniformly over a surface.
 * The system unit for this quantity is "Pa" (Pascal).
 */
public interface Pressure extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Pressure> UNIT = SI.PASCAL;

}