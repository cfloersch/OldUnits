package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Energy multiplied by a duration (quantity associated to the
 * <a href="http://en.wikipedia.org/wiki/Planck%27s_constant">Planck Constant</a>).
 * The system unit for this quantity is "J.s" (joules second).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Action_(physics)">Wikipedia: Action</a>
 */
public interface Action extends Quantity<Action> {

   public static final Unit<Action> UNIT = SI.JOULE_SECOND;

}
