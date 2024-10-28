package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Luminous intensity per unit area of light traveling in a given direction.
 * The system unit for this quantity is "cd/m²" (candela per square meter).
 *
 * @see LuminousIntensity
 * @see Area
 */
public interface Luminance extends Quantity<Luminance> {

   public static final Unit<Luminance> UNIT = SI.CANDELAS_PER_SQUARE_METRE;

}
