package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;

/**
 * Number of elementary entities (molecules, for example) of a substance. The metric
 * system unit for this quantity is "mol" (mole).
 *
 * @see <a href=" http://en.wikipedia.org/wiki/Amount_of_substance">Wikipedia: Amount
 *    of Substance</a>
 */
public interface AmountOfSubstance extends Quantity<AmountOfSubstance> {

   public static final Unit<AmountOfSubstance> UNIT = SI.MOLE;

}
