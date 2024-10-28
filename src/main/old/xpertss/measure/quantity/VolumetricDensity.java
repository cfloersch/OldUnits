package xpertss.measure.quantity;


import xpertss.measure.unit.ProductUnit;
import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a mass per unit volume of a substance under
 * specified conditions of pressure and temperature. The system unit for
 * this quantity is "kg/m³" (kilogram per cubic meter).
 */
public interface VolumetricDensity extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<VolumetricDensity> UNIT = new ProductUnit<>(SI.KILOGRAM.divide(SI.METRE.pow(3)));
}