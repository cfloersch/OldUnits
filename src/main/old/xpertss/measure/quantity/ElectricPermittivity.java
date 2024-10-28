package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * How an electric field affects, and is affected, by a dielectric medium.
 * The system unit for this quantity is "F/m" (farads per meter).
 */
public interface ElectricPermittivity extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<ElectricPermittivity> UNIT = SI.FARADS_PER_METRE;

}
