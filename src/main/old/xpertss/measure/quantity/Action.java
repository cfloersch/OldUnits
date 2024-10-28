package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * In physics, action is an attribute of the dynamics of a physical system. It is a
 * mathematical functional which takes the trajectory, also called path or history,
 * of the system as its argument and has a real number as its result. Generally, the
 * action takes different values for different paths. Action has the dimensions of
 * [energy]·[time], and its SI unit is joule-second. This is the same unit as that of
 * angular momentum.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Action_(physics)">Wikipedia: Action</a>
 */
public interface Action extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Action> UNIT = SI.JOULE_SECOND;

}
