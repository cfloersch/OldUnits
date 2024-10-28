package org.xpertss.measure;

import java.io.IOException;
import java.text.ParseException;


/**
 * Formats instances of {@link Unit} to a {@link String} or an {@link Appendable}
 * and parses a {@link CharSequence} to a {@link Unit}.
 * <p/>
 * TODO How do I get a Format instance?
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
}
