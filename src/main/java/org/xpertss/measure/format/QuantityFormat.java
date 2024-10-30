package org.xpertss.measure.format;

import org.xpertss.measure.Quantity;

import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;

/**
 * FormatService formatService = ServiceProvider.current().getFormatService();
 *
 * QuantityFormat format = formatService.getQuantityFormat();
 * Quantity<?> tenMin = format.parse("10 min");
 * Quantity<Mass> fiveKgTyped = format.parse("5 kg").asType(Mass.class);
 */
public interface QuantityFormat {


    Appendable format(Quantity<?> unit, Appendable appendable)
            throws IOException;

    String format(Quantity<?> unit)
            throws IOException;


    Quantity<?> parse(CharSequence csq)
            throws ParseException;

    Quantity<?> parse(CharSequence csq, ParsePosition cursor)
            throws ParseException;
}
