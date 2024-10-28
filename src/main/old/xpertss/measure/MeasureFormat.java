package xpertss.measure;

import xpertss.measure.quantity.Quantity;
import xpertss.measure.unit.CompoundUnit;
import xpertss.measure.unit.Unit;
import xpertss.measure.unit.UnitFormat;

import java.math.BigDecimal;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

/**
 * This class provides the interface for formatting and parsing {@link Measure measures}.
 * <p/>
 * As a minimum, instances of this class should be able to parse/format measure using
 * {@link xpertss.measure.unit.CompoundUnit}.
 *
 * TODO Need to enable a means of localization
 * TODO Maybe don't use NumberFormat or allow min/max fraction digits to be specified
 */
public abstract class MeasureFormat extends Format {

   /**
    * Returns the measure format for the default locale.
    *
    * @return <code>getInstance(Number.getInstance(), Unit.getInstance())</code>
    */
   public static MeasureFormat getInstance()
   {
      return new NumberUnit(NumberFormat.getInstance(), UnitFormat.getInstance());
   }



   public Measurable<? extends Quantity> parse(String source)
      throws ParseException
   {
      return (Measurable<?>) parseObject(source);
   }


   // Holds default implementation.
   static final class NumberUnit extends MeasureFormat {

      private final NumberFormat _numberFormat;

      private final UnitFormat _unitFormat;

      private NumberUnit(NumberFormat numberFormat, UnitFormat unitFormat)
      {
         _numberFormat = numberFormat;
         _unitFormat = unitFormat;
      }


      @Override
      public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
      {
         Measurable<?> measure = (Measurable<?>) obj;
         BigDecimal value = measure.getValue();
         Unit<?> unit = measure.getUnit();
         if(unit instanceof CompoundUnit) {
            return formatCompound(value, unit, toAppendTo, pos);
         }
         _numberFormat.format(value, toAppendTo, pos);
         if(!measure.getUnit().equals(Unit.ONE)) {
            toAppendTo.append(' ');
            _unitFormat.format(unit, toAppendTo, pos);
         }
         return toAppendTo;
      }

      // Measure using Compound unit have no separators in their representation.
      StringBuffer formatCompound(BigDecimal value, Unit<?> unit, StringBuffer toAppendTo, FieldPosition pos)
      {
         if(!(unit instanceof CompoundUnit)) {
            if(toAppendTo.length() > 0) toAppendTo.append(" ");
            _numberFormat.format(value, toAppendTo, pos);
            return _unitFormat.format(unit, toAppendTo, pos);
         }
         Unit<?> high = ((CompoundUnit<?>) unit).getHigher();
         Unit<?> low = ((CompoundUnit<?>) unit).getLower(); // The unit in which the value is stated.
         // Next function intentionally drops decimal precission
         BigDecimal highValue = new BigDecimal(low.getConverterTo(high).convert(value).toBigInteger());
         BigDecimal lowValue = value.subtract(high.getConverterTo(low).convert(highValue));
         formatCompound(highValue, high, toAppendTo, pos);
         formatCompound(lowValue, low, toAppendTo, pos);
         return toAppendTo;
      }



      @Override
      @SuppressWarnings("unchecked")
      public Object parseObject(String source, ParsePosition pos)
      {
         int start = pos.getIndex();
         try {
            int i = start;
            Number value = _numberFormat.parse(source, pos);
            BigDecimal v;
            if(value instanceof BigDecimal) {
               v = (BigDecimal) value;
            } else {
               v = new BigDecimal(value.doubleValue());
            }
            if(i == pos.getIndex()) {
               return null; // Cannot parse.
            }
            i = pos.getIndex();
            if(i >= source.length()) {
               return new DecimalMeasure(v, Unit.ONE); // No unit.
            }
            boolean isCompound = !Character.isWhitespace(source.charAt(i));
            if(isCompound) {
               return parseCompound(v, source, pos);
            }
            if(++i >= source.length()) {
               return new DecimalMeasure(v, Unit.ONE); // No unit.
            }
            pos.setIndex(i); // Skips separator.
            Unit<?> unit = _unitFormat.parseProductUnit(source, pos);
            return new DecimalMeasure(v, unit);
         } catch(ParseException e) {
            pos.setIndex(start);
            pos.setErrorIndex(e.getErrorOffset());
            return null;
         }
      }

      @SuppressWarnings("unchecked")
      private Object parseCompound(BigDecimal highValue, String source, ParsePosition pos)
         throws ParseException
      {
         Unit high = _unitFormat.parseSingleUnit(source, pos);
         int i = pos.getIndex();
         if(i >= source.length()) {
            return new DecimalMeasure(highValue, high);
         }
         pos.setIndex(pos.getIndex() + 1);
         Measurable lowMeasure = (Measurable) parseObject(source, pos);
         Unit unit = lowMeasure.getUnit();
         BigDecimal l = lowMeasure.getValue(unit).add(high.getConverterTo(unit).convert(highValue));
         return Measure.valueOf(l, high.compound(unit));
      }


      private static final long serialVersionUID = 1L;
   }

}
