package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Quantity of subatomic particles or electromagnetic waves that are energetic enough to detach
 * electrons from atoms or molecules, ionizing them. The system unit for this quantity is "C/kg
 * ("coulomb per kilogram).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Ionizing_radiation">Wikipedia: Ionizing Radiation</a>
 */
public interface IonizingRadiation extends Quantity<IonizingRadiation> {

   public static final Unit<IonizingRadiation> UNIT = SI.COULOMBS_PER_KILOGRAM;

}
