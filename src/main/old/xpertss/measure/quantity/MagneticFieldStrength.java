package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * Magnetic field strength. The system unit for this quantity is "A/m"
 * (ampere per meter).
 */
public interface MagneticFieldStrength extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<MagneticFieldStrength> UNIT = SI.AMPERES_PER_METRE;

}
