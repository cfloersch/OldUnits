package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * Degree of magnetization of a material that responds linearly to an
 * applied magnetic field. The system unit for this quantity is "H/m"
 * (henry per meter).
 */
public interface MagneticPermeability extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<MagneticFieldStrength> UNIT = SI.AMPERES_PER_METRE;

}
