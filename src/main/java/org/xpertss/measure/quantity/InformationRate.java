package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;

/**
 * Speed of data-transmission. The system unit for this quantity is "bit/s" (bit per second).
 *
 * @see Information
 * @see <a href="http://en.wikipedia.org/wiki/Information_rate">Wikipedia: Information Rate</a>
 */
public interface InformationRate extends Quantity<InformationRate> {

   public static final Unit<InformationRate> UNIT = SI.BITS_PER_SECOND;

}
