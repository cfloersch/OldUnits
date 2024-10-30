package org.xpertss.measure.format;

import org.xpertss.measure.Unit;

import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;


/**
 * Formats instances of {@link Unit} to a {@link String} or an {@link Appendable}
 * and parses a {@link CharSequence} to a {@link Unit}.
 * <p/>
 * TODO How do I get a Format instance?
 * <p/>
 * TODO Add additional methods from spec
 *
 * @see Unit
 */
public interface UnitFormat {
   /**
    * Formats the specified unit.
    *
    * @param unit       the unit to format.
    * @param appendable the appendable destination.
    * @return the appendable destination passed in with formatted text appended.
    * @throws IOException if an error occurs while writing to the destination.
    */
   Appendable format(Unit<?> unit, Appendable appendable)
      throws IOException;

   String format(Unit<?> unit)
           throws IOException;

   /**
    * Parses a portion of the specified {@code CharSequence} from the
    * specified position to produce a unit. If there is no unit to parse
    * the unitary unit (dimensionless) is returned.
    *
    * @param csq the {@code CharSequence} to parse.
    * @return the unit parsed from the specified character sub-sequence.
    * @throws ParseException if any problem occurs while parsing the
    *                        specified character sequence (e.g. illegal syntax).
    */
   Unit<?> parse(CharSequence csq)
      throws ParseException;

   Unit<?> parse(CharSequence csq, ParsePosition cursor)
           throws ParseException;


   void label(Unit<?> unit, String label);
}
