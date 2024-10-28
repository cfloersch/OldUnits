package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the luminous flux density per solid angle as
 * measured in a given direction relative to the emitting source.
 * The system unit for this quantity is "cd" (candela).
 */
public interface LuminousIntensity extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<LuminousIntensity> UNIT = SI.CANDELA;

}