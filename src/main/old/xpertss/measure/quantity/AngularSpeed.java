package xpertss.measure.quantity;


import xpertss.measure.unit.ProductUnit;
import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the rate of change of angular displacement with respect
 * to time. The system unit for this quantity is "rad/s" (radian per second).
 * <p/>
 * <cite>Angular speed</cite> is a scalar value, while <cite>angular velocity</cite>
 * is a pseudo-vector. The angular speed is the magnitude of the angular velocity
 * pseudo-vector, or the components of the angular velocity pseudo-vector.
 */
public interface AngularSpeed extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<AngularSpeed> UNIT = new ProductUnit<>(SI.RADIAN.divide(SI.SECOND));

}