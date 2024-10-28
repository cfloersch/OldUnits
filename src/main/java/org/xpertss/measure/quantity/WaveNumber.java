package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * This interface represents a wave property inversely related to wavelength.
 * The system unit for this quantity is "1/m" (reciprocal meters).
 *
 * @see Length
 * @see <a href="http://en.wikipedia.org/wiki/Wavenumber">Wikipedia: Wavenumber</a>
 */
public interface WaveNumber extends Quantity<WaveNumber> {

   public static final Unit<WaveNumber> UNIT = SI.RECIPROCAL_METRE;

}
