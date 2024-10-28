/**
 * Unit-API - Units of Measurement API for Java
 * Copyright (c) 2014 Jean-Marie Dautelle, Werner Keil, V2COM
 * All rights reserved.
 *
 * See LICENSE.txt for details.
 */
package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Luminous flux density per solid angle as measured in a given direction
 * relative to the emitting source. The metric system unit for this quantity
 * is "cd" (candela).
 *
 * @see Luminance
 */
public interface LuminousIntensity extends Quantity<LuminousIntensity> {

   public static final Unit<LuminousIntensity> UNIT = SI.CANDELA;

}
