package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Rate at which work is done. The metric system unit for this quantity is "W" (Watt).
 *
 * @see Energy
 * @see Time
 */
public interface Power extends Quantity<Power> {

   public static final Unit<Power> UNIT = SI.WATT;

}
