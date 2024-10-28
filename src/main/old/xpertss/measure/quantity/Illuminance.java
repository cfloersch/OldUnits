package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents an illuminance. The system unit for this quantity
 * is "lx" (lux).
 */
public interface Illuminance extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Illuminance> UNIT = SI.LUX;

}