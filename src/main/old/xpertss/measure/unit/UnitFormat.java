package xpertss.measure.unit;

import xpertss.measure.converter.AddConverter;
import xpertss.measure.converter.MultiplyConverter;
import xpertss.measure.converter.RationalConverter;
import xpertss.measure.quantity.Quantity;
import xpertss.measure.converter.UnitConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Locale;

import static xpertss.measure.unit.SI.*;
import static xpertss.measure.unit.SIPrefix.*;

/**
 * This class provides the interface for formatting and parsing {@link Unit units}.
 * <p/>
 * For all {@link SI} units, the 20 SI prefixes used to form decimal multiples and
 * sub-multiples of SI units are recognized. {@link NonSI} units are directly
 * recognized. For example:[code]
 * Unit.valueOf("m°C").equals(SI.MILLI(SI.CELSIUS))
 * Unit.valueOf("kW").equals(SI.KILO(SI.WATT))
 * Unit.valueOf("ft").equals(SI.METER.multiply(0.3048))[/code]
 * <p/>
 * TODO Need to localize this
 */
public abstract class UnitFormat extends Format {

   /**
    * Holds the standard unit format.
    */
   private static final DefaultFormat DEFAULT = new DefaultFormat();

   /**
    * Holds the ASCIIFormat unit format.
    */
   private static final ASCIIFormat ASCII = new ASCIIFormat();

   /**
    * Returns the unit format for the default locale (format used by
    * {@link Unit#valueOf(CharSequence) Unit.valueOf(CharSequence)} and
    * {@link Unit#toString() Unit.toString()}).
    *
    * @return the default unit format (locale sensitive).
    */
   public static UnitFormat getInstance()
   {
      return UnitFormat.getInstance(Locale.getDefault());
   }

   /**
    * Returns the unit format for the specified locale.
    *
    * @return the unit format for the specified locale.
    */
   public static UnitFormat getInstance(Locale inLocale)
   {
      return DEFAULT; // TBD: Implement Locale Format.
   }

   /**
    * Returns the <a href="http://aurora.regenstrief.org/UCUM/ucum.html">UCUM
    * </a> international unit format; this format uses characters range
    * <code>0000-007F</code> exclusively and <b>is not</b> locale-sensitive.
    * For example: <code>kg.m/s2</code>
    *
    * @return the UCUM international format.
    */
   public static UnitFormat getUCUMInstance()
   {
      return UnitFormat.ASCII; // TBD - Provide UCUM implementation.
   }

   /**
    * Base constructor.
    */
   protected UnitFormat()
   {
   }

   /**
    * Formats the specified unit.
    *
    * @param unit       the unit to format.
    * @param appendable the appendable destination.
    * @throws java.io.IOException if an error occurs.
    */
   public abstract Appendable format(Unit<?> unit, Appendable appendable)
      throws IOException;

   /**
    * Parses a sequence of character to produce a unit or a rational product
    * of unit.
    *
    * @param csq the <code>CharSequence</code> to parse.
    * @param pos an object holding the parsing index and error position.
    * @return an {@link Unit} parsed from the character sequence.
    * @throws IllegalArgumentException if the character sequence contains
    *                                  an illegal syntax.
    */
   public abstract Unit<? extends Quantity> parseProductUnit(CharSequence csq, ParsePosition pos)
      throws ParseException;

   /**
    * Parses a sequence of character to produce a single unit.
    *
    * @param csq the <code>CharSequence</code> to parse.
    * @param pos an object holding the parsing index and error position.
    * @return an {@link Unit} parsed from the character sequence.
    * @throws IllegalArgumentException if the character sequence does not contain
    *                                  a valid unit identifier.
    */
   public abstract Unit<? extends Quantity> parseSingleUnit(CharSequence csq, ParsePosition pos)
      throws ParseException;

   /**
    * Attaches a system-wide label to the specified unit. For example:
    * [code]
    * UnitFormat.getInstance().label(DAY.multiply(365), "year");
    * UnitFormat.getInstance().label(METER.multiply(0.3048), "ft");
    * [/code]
    * If the specified label is already associated to an unit the previous
    * association is discarded or ignored.
    *
    * @param unit  the unit being labelled.
    * @param label the new label for this unit.
    * @throws IllegalArgumentException if the label is not a
    *                                  {@link xpertss.measure.unit.UnitFormat#isValidIdentifier(String)} valid identifier.
    */
   public abstract void label(Unit<?> unit, String label);

   /**
    * Attaches a system-wide alias to this unit. Multiple aliases may
    * be attached to the same unit. Aliases are used during parsing to
    * recognize different variants of the same unit. For example:
    * [code]
    * UnitFormat.getLocaleInstance().alias(METER.multiply(0.3048), "foot");
    * UnitFormat.getLocaleInstance().alias(METER.multiply(0.3048), "feet");
    * UnitFormat.getLocaleInstance().alias(METER, "meter");
    * UnitFormat.getLocaleInstance().alias(METER, "metre");
    * [/code]
    * If the specified label is already associated to an unit the previous
    * association is discarded or ignored.
    *
    * @param unit  the unit being aliased.
    * @param alias the alias attached to this unit.
    * @throws IllegalArgumentException if the label is not a
    *                                  {@link xpertss.measure.unit.UnitFormat#isValidIdentifier(String)} valid identifier.
    */
   public abstract void alias(Unit<?> unit, String alias);

   /**
    * Indicates if the specified name can be used as unit identifier.
    *
    * @param name the identifier to be tested.
    * @return <code>true</code> if the name specified can be used as
    *         label or alias for this format;<code>false</code> otherwise.
    */
   public abstract boolean isValidIdentifier(String name);

   /**
    * Formats an unit and appends the resulting text to a given string
    * buffer (implements <code>java.text.Format</code>).
    *
    * @param unit       the unit to format.
    * @param toAppendTo where the text is to be appended
    * @param pos        the field position (not used).
    * @return <code>toAppendTo</code>
    */
   public final StringBuffer format(Object unit, final StringBuffer toAppendTo, FieldPosition pos)
   {
      try {
         format((Unit<?>) unit, toAppendTo);
         return toAppendTo;
      } catch(IOException e) {
         throw new Error(e); // Should never happen.
      }
   }

   /**
    * Parses the text from a string to produce an object
    * (implements <code>java.text.Format</code>).
    *
    * @param source the string source, part of which should be parsed.
    * @param pos    the cursor position.
    * @return the corresponding unit or <code>null</code> if the string
    *         cannot be parsed.
    */
   public final Unit<?> parseObject(String source, ParsePosition pos)
   {
      int start = pos.getIndex();
      try {
         return parseProductUnit(source, pos);
      } catch(ParseException e) {
         pos.setIndex(start);
         pos.setErrorIndex(e.getErrorOffset());
         return null;
      }
   }


   /**
    * This class represents an exponent with both a power (numerator)
    * and a root (denominator).
    */
   private static class Exponent {

      public final int pow;
      public final int root;

      public Exponent(int pow, int root)
      {
         this.pow = pow;
         this.root = root;
      }
   }

   /**
    * This class represents the standard format.
    */
   protected static class DefaultFormat extends UnitFormat {

      /**
       * Holds the name to unit mapping.
       */
      final HashMap<String, Unit<?>> _nameToUnit = new HashMap<String, Unit<?>>();

      /**
       * Holds the unit to name mapping.
       */
      final HashMap<Unit<?>, String> _unitToName = new HashMap<Unit<?>, String>();

      @Override
      public void label(Unit<?> unit, String label)
      {
         if(!isValidIdentifier(label)) {
            throw new IllegalArgumentException("Label: " + label + " is not a valid identifier.");
         }
         synchronized(this) {
            _nameToUnit.put(label, unit);
            _unitToName.put(unit, label);
         }
      }

      @Override
      public void alias(Unit<?> unit, String alias)
      {
         if(!isValidIdentifier(alias)) {
            throw new IllegalArgumentException("Alias: " + alias + " is not a valid identifier.");
         }
         synchronized(this) {
            _nameToUnit.put(alias, unit);
         }
      }

      @Override
      public boolean isValidIdentifier(String name)
      {
         if((name == null) || (name.length() == 0)) {
            return false;
         }
         for(int i = 0; i < name.length(); i++) {
            if(!isUnitIdentifierPart(name.charAt(i))) {
               return false;
            }
         }
         return true;
      }

      static boolean isUnitIdentifierPart(char ch)
      {
         return Character.isLetter(ch) || (!Character.isWhitespace(ch) && !Character.isDigit(ch) && (ch != '\u00B7') && (ch != '*') && (ch != '/') && (ch != '(') && (ch != ')') && (ch != '[') && (ch != ']') && (ch != '\u00B9') && (ch != '\u00B2') && (ch != '\u00B3') && (ch != '^') && (ch != '+') && (ch != '-'));
      }

      // Returns the name for the specified unit or null if product unit.
      public String nameFor(Unit<?> unit)
      {
         // Searches label database.
         String label = _unitToName.get(unit);
         if(label != null) {
            return label;
         }
         if(unit instanceof BaseUnit) {
            return ((BaseUnit<?>) unit).getSymbol();
         }
         if(unit instanceof AlternateUnit) {
            return ((AlternateUnit<?>) unit).getSymbol();
         }
         if(unit instanceof TransformedUnit) {
            TransformedUnit<?> tfmUnit = (TransformedUnit<?>) unit;
            Unit<?> baseUnits = tfmUnit.getStandardUnit();
            UnitConverter cvtr = tfmUnit.toStandardUnit();
            StringBuffer result = new StringBuffer();
            String baseUnitName = baseUnits.toString();
            if((baseUnitName.indexOf('\u00B7') >= 0) || (baseUnitName.indexOf('*') >= 0) || (baseUnitName.indexOf('/') >= 0)) {
               // We could use parentheses whenever baseUnits is an
               // instanceof ProductUnit, but most ProductUnits have aliases,
               // so we'd end up with a lot of unnecessary parentheses.
               result.append('(');
               result.append(baseUnitName);
               result.append(')');
            } else {
               result.append(baseUnitName);
            }
            if(cvtr instanceof AddConverter) {
               result.append('+');
               result.append(((AddConverter) cvtr).getOffset());
            } else if(cvtr instanceof RationalConverter) {
               BigDecimal dividend = ((RationalConverter) cvtr).getDividend();
               if(!dividend.equals(BigDecimal.ONE)) {
                  result.append('*');
                  result.append(dividend);
               }
               BigDecimal divisor = ((RationalConverter) cvtr).getDivisor();
               if(!divisor.equals(BigDecimal.ONE)) {
                  result.append('/');
                  result.append(divisor);
               }
            } else if(cvtr instanceof MultiplyConverter) {
               BigDecimal factor = ((MultiplyConverter)cvtr).getFactor();
               result.append('*');
               result.append(factor);

               // TODO Do I need to add a DivideConverter

            } else { // Other converters.
               return "[" + baseUnits + "?]";
            }
            return result.toString();
         }
         // Compound unit.
         if(unit instanceof CompoundUnit) {
            CompoundUnit<?> cpdUnit = (CompoundUnit<?>) unit;
            return nameFor(cpdUnit.getHigher()).toString() + ":" + nameFor(cpdUnit.getLower());
         }
         return null; // Product unit.
      }

      // Returns the unit for the specified name.
      public Unit<?> unitFor(String name)
      {
         Unit<?> unit = _nameToUnit.get(name);
         if(unit != null) return unit;
         //unit = Unit.SYMBOL_TO_UNIT.get(name);   // TODO Fix
         return unit;
      }

      ////////////////////////////
      // Parsing.

      @SuppressWarnings("unchecked")
      public Unit<? extends Quantity> parseSingleUnit(CharSequence csq, ParsePosition pos)
         throws ParseException
      {
         int startIndex = pos.getIndex();
         String name = readIdentifier(csq, pos);
         Unit unit = unitFor(name);
         check(unit != null, name + " not recognized", csq, startIndex);
         return unit;
      }

      @SuppressWarnings("unchecked")
      @Override
      public Unit<? extends Quantity> parseProductUnit(CharSequence csq, ParsePosition pos)
         throws ParseException
      {
         Unit result = Unit.ONE;
         int token = nextToken(csq, pos);
         switch(token) {
            case IDENTIFIER:
               result = parseSingleUnit(csq, pos);
               break;
            case OPEN_PAREN:
               pos.setIndex(pos.getIndex() + 1);
               result = parseProductUnit(csq, pos);
               token = nextToken(csq, pos);
               check(token == CLOSE_PAREN, "')' expected", csq, pos.getIndex());
               pos.setIndex(pos.getIndex() + 1);
               break;
         }
         token = nextToken(csq, pos);
         while(true) {
            switch(token) {
               case EXPONENT:
                  Exponent e = readExponent(csq, pos);
                  if(e.pow != 1) {
                     result = result.pow(e.pow);
                  }
                  if(e.root != 1) {
                     result = result.root(e.root);
                  }
                  break;
               case MULTIPLY:
                  pos.setIndex(pos.getIndex() + 1);
                  token = nextToken(csq, pos);
                  if(token == INTEGER) {
                     long n = readLong(csq, pos);
                     if(n != 1) {
                        result = result.multiply(n);
                     }
                  } else if(token == FLOAT) {
                     BigDecimal d = readDecimal(csq, pos);
                     if(!d.equals(BigDecimal.ONE)) {
                        result = result.multiply(d);
                     }
                  } else {
                     result = result.multiply(parseProductUnit(csq, pos));
                  }
                  break;
               case DIVIDE:
                  pos.setIndex(pos.getIndex() + 1);
                  token = nextToken(csq, pos);
                  if(token == INTEGER) {
                     long n = readLong(csq, pos);
                     if(n != 1) {
                        result = result.divide(n);
                     }
                  } else if(token == FLOAT) {
                     BigDecimal d = readDecimal(csq, pos);
                     if(!d.equals(BigDecimal.ONE)) {
                        result = result.divide(d);
                     }
                  } else {
                     result = result.divide(parseProductUnit(csq, pos));
                  }
                  break;
               case PLUS:
                  pos.setIndex(pos.getIndex() + 1);
                  token = nextToken(csq, pos);
                  if(token == INTEGER) {
                     long n = readLong(csq, pos);
                     if(n != 1) {
                        result = result.add(BigDecimal.valueOf(n));
                     }
                  } else if(token == FLOAT) {
                     BigDecimal d = readDecimal(csq, pos);
                     if(!d.equals(BigDecimal.ONE)) {
                        result = result.add(d);
                     }
                  } else {
                     throw new ParseException("not a number", pos.getIndex());
                  }
                  break;
               case EOF:
               case CLOSE_PAREN:
                  return result;
               default:
                  throw new ParseException("unexpected token " + token, pos.getIndex());
            }
            token = nextToken(csq, pos);
         }
      }

      private static final int EOF = 0;
      private static final int IDENTIFIER = 1;
      private static final int OPEN_PAREN = 2;
      private static final int CLOSE_PAREN = 3;
      private static final int EXPONENT = 4;
      private static final int MULTIPLY = 5;
      private static final int DIVIDE = 6;
      private static final int PLUS = 7;
      private static final int INTEGER = 8;
      private static final int FLOAT = 9;

      private int nextToken(CharSequence csq, ParsePosition pos)
      {
         final int length = csq.length();
         while(pos.getIndex() < length) {
            char c = csq.charAt(pos.getIndex());
            if(isUnitIdentifierPart(c)) {
               return IDENTIFIER;
            } else if(c == '(') {
               return OPEN_PAREN;
            } else if(c == ')') {
               return CLOSE_PAREN;
            } else if((c == '^') || (c == '\u00B9') || (c == '\u00B2') || (c == '\u00B3')) {
               return EXPONENT;
            } else if(c == '*') {
               char c2 = csq.charAt(pos.getIndex() + 1);
               if(c2 == '*') {
                  return EXPONENT;
               } else {
                  return MULTIPLY;
               }
            } else if(c == '\u00B7') {
               return MULTIPLY;
            } else if(c == '/') {
               return DIVIDE;
            } else if(c == '+') {
               return PLUS;
            } else if((c == '-') || Character.isDigit(c)) {
               int index = pos.getIndex() + 1;
               while((index < length) && (Character.isDigit(c) || (c == '-') || (c == '.') || (c == 'E'))) {
                  c = csq.charAt(index++);
                  if(c == '.') {
                     return FLOAT;
                  }
               }
               return INTEGER;
            }
            pos.setIndex(pos.getIndex() + 1);
         }
         return EOF;
      }

      private void check(boolean expr, String message, CharSequence csq, int index)
         throws ParseException
      {
         if(!expr) {
            throw new ParseException(message + " (in " + csq + " at index " + index + ")", index);
         }
      }

      private Exponent readExponent(CharSequence csq, ParsePosition pos)
      {
         char c = csq.charAt(pos.getIndex());
         if(c == '^') {
            pos.setIndex(pos.getIndex() + 1);
         } else if(c == '*') {
            pos.setIndex(pos.getIndex() + 2);
         }
         final int length = csq.length();
         int pow = 0;
         boolean isPowNegative = false;
         int root = 0;
         boolean isRootNegative = false;
         boolean isRoot = false;
         while(pos.getIndex() < length) {
            c = csq.charAt(pos.getIndex());
            if(c == '\u00B9') {
               if(isRoot) {
                  root = root * 10 + 1;
               } else {
                  pow = pow * 10 + 1;
               }
            } else if(c == '\u00B2') {
               if(isRoot) {
                  root = root * 10 + 2;
               } else {
                  pow = pow * 10 + 2;
               }
            } else if(c == '\u00B3') {
               if(isRoot) {
                  root = root * 10 + 3;
               } else {
                  pow = pow * 10 + 3;
               }
            } else if(c == '-') {
               if(isRoot) {
                  isRootNegative = true;
               } else {
                  isPowNegative = true;
               }
            } else if((c >= '0') && (c <= '9')) {
               if(isRoot) {
                  root = root * 10 + (c - '0');
               } else {
                  pow = pow * 10 + (c - '0');
               }
            } else if(c == ':') {
               isRoot = true;
            } else {
               break;
            }
            pos.setIndex(pos.getIndex() + 1);
         }
         if(pow == 0) {
            pow = 1;
         }
         if(root == 0) {
            root = 1;
         }
         return new Exponent(isPowNegative ? -pow : pow, isRootNegative ? -root : root);
      }

      private long readLong(CharSequence csq, ParsePosition pos)
      {
         final int length = csq.length();
         int result = 0;
         boolean isNegative = false;
         while(pos.getIndex() < length) {
            char c = csq.charAt(pos.getIndex());
            if(c == '-') {
               isNegative = true;
            } else if((c >= '0') && (c <= '9')) {
               result = result * 10 + (c - '0');
            } else {
               break;
            }
            pos.setIndex(pos.getIndex() + 1);
         }
         return isNegative ? -result : result;
      }

      private BigDecimal readDecimal(CharSequence csq, ParsePosition pos)
      {
         final int length = csq.length();
         int start = pos.getIndex();
         int end = start + 1;
         while(end < length) {
            if("012356789+-.E".indexOf(csq.charAt(end)) < 0) {
               break;
            }
            end += 1;
         }
         pos.setIndex(end + 1);
         return new BigDecimal(csq.subSequence(start, end).toString());
      }

      private String readIdentifier(CharSequence csq, ParsePosition pos)
      {
         final int length = csq.length();
         int start = pos.getIndex();
         int i = start;
         while((++i < length) && isUnitIdentifierPart(csq.charAt(i))) {
         }
         pos.setIndex(i);
         return csq.subSequence(start, i).toString();
      }

      ////////////////////////////
      // Formatting.

      @Override
      public Appendable format(Unit<?> unit, Appendable appendable)
         throws IOException
      {
         String name = nameFor(unit);
         if(name != null) {
            return appendable.append(name);
         }
         if(!(unit instanceof ProductUnit)) {
            throw new IllegalArgumentException("Cannot format given Object as a Unit");
         }

         // Product unit.
         ProductUnit<?> productUnit = (ProductUnit<?>) unit;
         int invNbr = 0;

         // Write positive exponents first.
         boolean start = true;
         for(int i = 0; i < productUnit.getUnitCount(); i++) {
            int pow = productUnit.getUnitPow(i);
            if(pow >= 0) {
               if(!start) {
                  appendable.append('\u00B7'); // Separator.
               }
               name = nameFor(productUnit.getUnit(i));
               int root = productUnit.getUnitRoot(i);
               append(appendable, name, pow, root);
               start = false;
            } else {
               invNbr++;
            }
         }

         // Write negative exponents.
         if(invNbr != 0) {
            if(start) {
               appendable.append('1'); // e.g. 1/s
            }
            appendable.append('/');
            if(invNbr > 1) {
               appendable.append('(');
            }
            start = true;
            for(int i = 0; i < productUnit.getUnitCount(); i++) {
               int pow = productUnit.getUnitPow(i);
               if(pow < 0) {
                  name = nameFor(productUnit.getUnit(i));
                  int root = productUnit.getUnitRoot(i);
                  if(!start) {
                     appendable.append('\u00B7'); // Separator.
                  }
                  append(appendable, name, -pow, root);
                  start = false;
               }
            }
            if(invNbr > 1) {
               appendable.append(')');
            }
         }
         return appendable;
      }

      private void append(Appendable appendable, CharSequence symbol, int pow, int root)
         throws IOException
      {
         appendable.append(symbol);
         if((pow != 1) || (root != 1)) {
            // Write exponent.
            if((pow == 2) && (root == 1)) {
               appendable.append('\u00B2'); // Square
            } else if((pow == 3) && (root == 1)) {
               appendable.append('\u00B3'); // Cubic
            } else {
               // Use general exponent form.
               appendable.append('^');
               appendable.append(String.valueOf(pow));
               if(root != 1) {
                  appendable.append(':');
                  appendable.append(String.valueOf(root));
               }
            }
         }
      }

      private static final long serialVersionUID = 1L;
   }

   /**
    * This class represents the ASCIIFormat format.
    */
   protected static class ASCIIFormat extends DefaultFormat {

      @Override
      public String nameFor(Unit<?> unit)
      {
         // First search if specific ASCII name should be used.
         String name = _unitToName.get(unit);
         if(name != null) {
            return name;
         }
         // Else returns default name.
         return DEFAULT.nameFor(unit);
      }

      @Override
      public Unit<?> unitFor(String name)
      {
         // First search if specific ASCII name.
         Unit<?> unit = _nameToUnit.get(name);
         if(unit != null) {
            return unit;
         }
         // Else returns default mapping.
         return DEFAULT.unitFor(name);
      }

      @Override
      public Appendable format(Unit<?> unit, Appendable appendable)
         throws IOException
      {
         String name = nameFor(unit);
         if(name != null) {
            return appendable.append(name);
         }
         if(!(unit instanceof ProductUnit)) {
            throw new IllegalArgumentException("Cannot format given Object as a Unit");
         }

         ProductUnit<?> productUnit = (ProductUnit<?>) unit;
         for(int i = 0; i < productUnit.getUnitCount(); i++) {
            if(i != 0) {
               appendable.append('*'); // Separator.
            }
            name = nameFor(productUnit.getUnit(i));
            int pow = productUnit.getUnitPow(i);
            int root = productUnit.getUnitRoot(i);
            appendable.append(name);
            if((pow != 1) || (root != 1)) {
               // Use general exponent form.
               appendable.append('^');
               appendable.append(String.valueOf(pow));
               if(root != 1) {
                  appendable.append(':');
                  appendable.append(String.valueOf(root));
               }
            }
         }
         return appendable;
      }

      private static final long serialVersionUID = 1L;
   }

   ////////////////////////////////////////////////////////////////////////////
   // Initializes the standard unit database for SI units.

   private static final Unit<?>[] SI_UNITS = {
      SI.AMPERE, SI.BECQUEREL, SI.CANDELA, SI.COULOMB, SI.FARAD, SI.GRAY, SI.HENRY, SI.HERTZ, SI.JOULE, SI.KATAL, SI.KELVIN, SI.LUMEN, SI.LUX, SI.METRE, SI.MOLE, SI.NEWTON, SI.OHM, SI.PASCAL, SI.RADIAN, SI.SECOND, SI.SIEMENS, SI.SIEVERT, SI.STERADIAN, SI.TESLA, SI.VOLT, SI.WATT, SI.WEBER
   };

   private static final String[] PREFIXES = {
      "Y", "Z", "E", "P", "T", "G", "M", "k", "h", "da", "d", "c", "m", "µ", "n", "p", "f", "a", "z", "y"
   };

   private static final UnitConverter[] CONVERTERS = {
      E24, E21, E18, E15, E12, E9, E6, E3, E2, E1, Em1, Em2, Em3, Em6, Em9, Em12, Em15, Em18, Em21, Em24
   };

   private static String asciiPrefix(String prefix)
   {
      return prefix.equals("µ") ? "micro" : prefix;
   }

   static {
      for(int i = 0; i < SI_UNITS.length; i++) {
         for(int j = 0; j < PREFIXES.length; j++) {
            Unit<?> si = SI_UNITS[i];
            Unit<?> u = si.transform(CONVERTERS[j]);
            String symbol = (si instanceof BaseUnit) ? ((BaseUnit<?>) si)
               .getSymbol() : ((AlternateUnit<?>) si).getSymbol();
            DEFAULT.label(u, PREFIXES[j] + symbol);
            if(PREFIXES[j].equals("µ")) {
               ASCII.label(u, "micro" + symbol);
            }
         }
      }
      // Special case for KILOGRAM.
      DEFAULT.label(SI.GRAM, "g");
      for(int i = 0; i < PREFIXES.length; i++) {
         if(CONVERTERS[i] == E3) {
            continue;  // kg is already defined.
         }
         DEFAULT.label(SI.KILOGRAM.transform(CONVERTERS[i].concat(Em3)), PREFIXES[i] + "g");
         if(PREFIXES[i].equals("µ")) {
            ASCII.label(SI.KILOGRAM.transform(CONVERTERS[i].concat(Em3)), "microg");
         }
      }

      // Alias and ASCIIFormat for Ohm
      DEFAULT.alias(SI.OHM, "Ohm");
      ASCII.label(SI.OHM, "Ohm");
      for(int i = 0; i < PREFIXES.length; i++) {
         DEFAULT.alias(SI.OHM.transform(CONVERTERS[i]), PREFIXES[i] + "Ohm");
         ASCII.label(SI.OHM.transform(CONVERTERS[i]), asciiPrefix(PREFIXES[i]) + "Ohm");
      }

      // Special case for DEGREE_CElSIUS.
      DEFAULT.label(SI.CELSIUS, "℃");
      DEFAULT.alias(SI.CELSIUS, "°C");
      ASCII.label(SI.CELSIUS, "Celsius");
      for(int i = 0; i < PREFIXES.length; i++) {
         DEFAULT.label(SI.CELSIUS.transform(CONVERTERS[i]), PREFIXES[i] + "℃");
         DEFAULT.alias(SI.CELSIUS.transform(CONVERTERS[i]), PREFIXES[i] + "°C");
         ASCII.label(SI.CELSIUS.transform(CONVERTERS[i]), asciiPrefix(PREFIXES[i]) + "Celsius");
      }
   }

   ////////////////////////////////////////////////////////////////////////////
   // To be moved in resource bundle in future release (locale dependent).
   static {
      DEFAULT.label(NonSI.PERCENT, "%");
      //DEFAULT.label(NonSI.DECIBEL, "dB");           // TODO Undo this
      DEFAULT.label(NonSI.G, "grav");
      DEFAULT.label(NonSI.ATOM, "atom");
      DEFAULT.label(NonSI.REVOLUTION, "rev");
      DEFAULT.label(NonSI.DEGREE_ANGLE, "\u00B0");
      ASCII.label(NonSI.DEGREE_ANGLE, "degree_angle");
      DEFAULT.label(NonSI.MINUTE_ANGLE, "'");
      DEFAULT.label(NonSI.SECOND_ANGLE, "\"");
      DEFAULT.label(NonSI.CENTIRADIAN, "centiradian");
      DEFAULT.label(NonSI.GRADE, "grade");
      DEFAULT.label(NonSI.ARE, "a");
      DEFAULT.label(NonSI.HECTARE, "ha");
      DEFAULT.label(NonSI.BYTE, "byte");
      DEFAULT.label(MINUTE, "min");
      DEFAULT.label(HOUR, "h");
      DEFAULT.label(NonSI.DAY, "day");
      DEFAULT.label(NonSI.WEEK, "week");
      DEFAULT.label(NonSI.YEAR, "year");
      DEFAULT.label(NonSI.MONTH, "month");
      DEFAULT.label(NonSI.DAY_SIDEREAL, "day_sidereal");
      DEFAULT.label(NonSI.YEAR_SIDEREAL, "year_sidereal");
      DEFAULT.label(NonSI.YEAR_CALENDAR, "year_calendar");
      DEFAULT.label(NonSI.E, "e");
      DEFAULT.label(NonSI.FARADAY, "Fd");
      DEFAULT.label(NonSI.FRANKLIN, "Fr");
      DEFAULT.label(NonSI.GILBERT, "Gi");
      DEFAULT.label(NonSI.ERG, "erg");
      DEFAULT.label(NonSI.ELECTRON_VOLT, "eV");
      DEFAULT.label(KILO(NonSI.ELECTRON_VOLT), "keV");
      DEFAULT.label(MEGA(NonSI.ELECTRON_VOLT), "MeV");
      DEFAULT.label(GIGA(NonSI.ELECTRON_VOLT), "GeV");
      DEFAULT.label(NonSI.LAMBERT, "La");
      DEFAULT.label(NonSI.FOOT, "ft");
      DEFAULT.label(NonSI.SURVEY_FOOT, "foot_survey");
      DEFAULT.label(NonSI.YARD, "yd");
      DEFAULT.label(NonSI.INCH, "in");
      DEFAULT.label(NonSI.MILE, "mi");
      DEFAULT.label(NonSI.NAUTICAL_MILE, "nmi");
      DEFAULT.label(NonSI.MILES_PER_HOUR, "mph");
      DEFAULT.label(NonSI.ANGSTROM, "Å");
      ASCII.label(NonSI.ANGSTROM, "Angstrom");
      DEFAULT.label(NonSI.ASTRONOMICAL_UNIT, "ua");
      DEFAULT.label(NonSI.LIGHT_YEAR, "ly");
      DEFAULT.label(NonSI.PARSEC, "pc");
      DEFAULT.label(NonSI.MAXWELL, "Mx");
      DEFAULT.label(NonSI.GAUSS, "G");
      DEFAULT.label(NonSI.ATOMIC_MASS, "u");
      DEFAULT.label(NonSI.ELECTRON_MASS, "me");
      DEFAULT.label(NonSI.POUND, "lb");
      DEFAULT.label(NonSI.OUNCE, "oz");
      DEFAULT.label(NonSI.TON, "ton");
      DEFAULT.label(NonSI.METRIC_TON, "t");
      DEFAULT.label(NonSI.DYNE, "dyn");
      DEFAULT.label(NonSI.KILOGRAM_FORCE, "kgf");
      DEFAULT.label(NonSI.POUND_FORCE, "lbf");
      DEFAULT.label(NonSI.HORSEPOWER, "hp");
      DEFAULT.label(NonSI.ATMOSPHERE, "atm");
      DEFAULT.label(NonSI.BAR, "bar");
      DEFAULT.label(NonSI.MILLIMETER_OF_MERCURY, "mmHg");
      DEFAULT.label(NonSI.INCH_OF_MERCURY, "inHg");
      DEFAULT.label(NonSI.RAD, "rd");
      DEFAULT.label(NonSI.REM, "rem");
      DEFAULT.label(NonSI.CURIE, "Ci");
      DEFAULT.label(NonSI.RUTHERFORD, "Rd");
      DEFAULT.label(NonSI.SPHERE, "sphere");
      DEFAULT.label(NonSI.RANKINE, "°R");
      ASCII.label(NonSI.RANKINE, "degree_rankine");
      DEFAULT.label(NonSI.FAHRENHEIT, "°F");
      ASCII.label(NonSI.FAHRENHEIT, "degree_fahrenheit");
      DEFAULT.label(NonSI.KNOT, "kn");
      DEFAULT.label(NonSI.MACH, "Mach");
      DEFAULT.label(NonSI.C, "c");
      DEFAULT.label(LITRE, "L");
      DEFAULT.label(MICRO(LITRE), "µL");
      ASCII.label(MICRO(LITRE), "microL");
      DEFAULT.label(MILLI(LITRE), "mL");
      DEFAULT.label(CENTI(LITRE), "cL");
      DEFAULT.label(DECI(LITRE), "dL");
      DEFAULT.label(NonSI.GALLON, "gal");
      DEFAULT.label(NonSI.FLUID_OUNCE, "fl_oz");
      DEFAULT.label(NonSI.ROENTGEN, "Roentgen");
      DEFAULT.label(NonSI.GRAIN, "gr");
      DEFAULT.label(NonSI.QUART, "qt");
      DEFAULT.label(NonSI.PINT, "pt");
      DEFAULT.label(NonSI.CORD, "cord");
      DEFAULT.label(NonSI.CUP, "cup");
      DEFAULT.label(NonSI.TABLESPOON, "Tbsp");
      DEFAULT.label(NonSI.TEASPOON, "tsp");
      DEFAULT.label(NonSI.BUSHEL, "bu");  // bsh as an alternative
      DEFAULT.label(NonSI.ACRE, "ac");


      DEFAULT.label(Imperial.GALLON, "gallon_uk");
      DEFAULT.label(Imperial.FLUID_OUNCE, "oz_uk");
      DEFAULT.label(Imperial.TON, "ton_uk");
      if(Locale.getDefault().getCountry().equals("GB")) {
         DEFAULT.label(Imperial.TON, "ton");
         DEFAULT.label(Imperial.GALLON, "gal");
         DEFAULT.label(Imperial.FLUID_OUNCE, "oz");
      }
   }
}