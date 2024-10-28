package xpertss.measure.quantity;


import xpertss.measure.unit.ProductUnit;
import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the rate of change of angular velocity with respect
 * to time. The system unit for this quantity is "rad/s²" (radian per
 * square second).
 */
public interface AngularAcceleration extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<AngularAcceleration> UNIT = new ProductUnit<>(SI.RADIAN.divide(SI.SECOND.pow(2)));

}