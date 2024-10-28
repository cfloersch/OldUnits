package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a magnetic flux. The system unit for this quantity
 * is "Wb" (Weber).
 */
public interface MagneticFlux extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<MagneticFlux> UNIT = SI.WEBER;

}