package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Extent of a planar region or of the surface of a solid measured in square units.
 * The metric system unit for this quantity is "m²" (square metre).
 *
 * @see Length
 * @see Volume
 * @see Pressure
 */
public interface Area extends Quantity<Area> {

   public static final Unit<Area> UNIT = SI.SQUARE_METRE;

}
