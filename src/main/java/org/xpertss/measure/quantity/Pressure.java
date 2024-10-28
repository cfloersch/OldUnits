package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Force applied uniformly over a surface. The metric system unit for this quantity is "Pa"
 * (Pascal).
 *
 * @see Force
 * @see Area
 */
public interface Pressure extends Quantity<Pressure> {

   public static final Unit<Pressure> UNIT = SI.PASCAL;

}
