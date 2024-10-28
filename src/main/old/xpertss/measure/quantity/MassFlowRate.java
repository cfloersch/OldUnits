package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the movement of mass per time.
 * The system unit for this quantity is "kg/s" (kilogram per second).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Mass_flow_rate">
 *      Wikipedia: Mass Flow Rate</a>
 */
public interface MassFlowRate extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   @SuppressWarnings("unchecked")
   public final static Unit<MassFlowRate> UNIT = (Unit<MassFlowRate>) SI.KILOGRAM.divide(SI.SECOND);
}