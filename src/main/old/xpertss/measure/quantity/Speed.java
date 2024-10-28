package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * Distance traveled divided by the time of travel. The metric system unit for this
 * quantity is "m/s" (metre per second).
 * <p/>
 * <cite>Speed</cite> is a scalar value, while <cite>velocity</cite> is a vector.
 * Speed is the magnitude of the velocity vector, or the components of the velocity
 * vector.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Speed">Wikipedia: Speed</a>
 */
public interface Speed extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Speed> UNIT = SI.METRES_PER_SECOND;

}