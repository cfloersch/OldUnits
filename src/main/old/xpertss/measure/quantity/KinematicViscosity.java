package xpertss.measure.quantity;


import xpertss.measure.unit.ProductUnit;
import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the diffusion of momentum.
 * The system unit for this quantity is "m²/s".
 *
 * @see <a href="http://en.wikipedia.org/wiki/Viscosity">
 *      Wikipedia: Viscosity</a>
 */
public interface KinematicViscosity extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<KinematicViscosity> UNIT = new ProductUnit<>(SI.METRE.pow(2).divide(SI.SECOND));

}