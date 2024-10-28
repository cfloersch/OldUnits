package xpertss.measure.quantity;


import xpertss.measure.unit.ProductUnit;
import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the dynamic viscosity.
 * The system unit for this quantity is "Pa·s" (Pascal-Second).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Viscosity">
 *      Wikipedia: Viscosity</a>
 */
public interface DynamicViscosity extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<DynamicViscosity> UNIT = new ProductUnit<>(SI.PASCAL.multiply(SI.SECOND));

}