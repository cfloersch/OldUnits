package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a magnetic flux density. The system unit for this
 * quantity is "T" (Tesla).
 */
public interface MagneticFluxDensity extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<MagneticFluxDensity> UNIT = SI.TESLA;

}