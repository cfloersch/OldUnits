package xpertss.measure.quantity;


import xpertss.measure.unit.ProductUnit;
import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the moment of a force. The system unit for this
 * quantity is "N·m" (Newton-Meter).
 * <p/>
 * <p> Note: The Newton-metre ("N·m") is also a way of exressing a Joule (unit
 * of energy). However, torque is not energy. So, to avoid confusion, we
 * will use the units "N·m" for torque and not "J". This distinction occurs
 * due to the scalar nature of energy and the vector nature of torque.</p>
 */
public interface Torque extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Torque> UNIT = new ProductUnit<>(SI.NEWTON.multiply(SI.METRE));

}
