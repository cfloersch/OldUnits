package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;

/**
 * Measure of information. The metric system unit for this quantity is "bit".
 *
 * @see InformationRate
 * @see <a href="http://en.wikipedia.org/wiki/Units_of_information">Wikipedia:
 *    Units of Information</a>
 *
 * TODO Beyond spec
 */
public interface Information extends Quantity<Information> {

   public static final Unit<Information> UNIT = SI.BIT;

}
