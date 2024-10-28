package org.xpertss.measure;

import xpertss.unit.converters.AddConverter;
import xpertss.unit.converters.DivideConverter;
import xpertss.unit.converters.MultiplyConverter;
import xpertss.unit.converters.RationalConverter;
import xpertss.unit.types.AlternateUnit;
import xpertss.unit.types.BaseUnit;
import xpertss.unit.types.ProductUnit;
import xpertss.unit.types.TransformedUnit;

import org.xpertss.measure.units.SI;
import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

/**
 * Represents a determinate {@linkplain Quantity quantity} (as of
 * length, time, heat, or value) adopted as a standard of measurement.
 * <p/>
 * It is helpful to think of instances of this class as recording the history
 * by which they are created. Thus, for example, the string {@code "g/kg"} (which
 * is a dimensionless unit) would result from invoking the method {@link #toString()}
 * on a unit that was created by dividing a gram unit by a kilogram unit.
 * <p/>
 * This interface supports the multiplication of offset units. The result is
 * usually a unit not convertible to its {@linkplain #getSystemUnit() system unit}.
 * Such units may appear in derivative quantities. For example Celsius per meter is
 * an unit of gradient, which is common in atmospheric and oceanographic research.
 * <p/>
 * Units raised at non-integral powers are not supported. For example,
 * {@code LITRE.root(2)} raises an {@code ArithmeticException}, but
 * {@code HECTARE.root(2)} returns {@code HECTOMETRE} (100 metres).
 * <p/>
 * Unit instances shall be immutable.
 *
 * @param <Q> The type of the quantity measured by this unit.
 * @see <a href="http://en.wikipedia.org/wiki/Units_of_measurement">Wikipedia: Units
 * of measurement</a>
 */
public abstract class Unit<Q extends Quantity<Q>> {


   /**
    * Default constructor.
    */
   protected Unit() { }


   /*******************/
   /** Units Queries **/
   /*******************/

   /**
    * Returns the symbol (if any) of this unit. This method returns {@code null}
    * if this unit has no specific symbol associated with.
    *
    * @return this unit symbol, or {@code null} if this unit has no specific
    * symbol associated with (e.g. product of units).
    */
   public String getSymbol() { return null; }


   /**
    * Returns the dimension of this unit. Two units {@code u1} and {@code u2}
    * are {@linkplain #isCompatible(Unit) compatible} if and only if
    * {@code u1.getDimension().equals(u2.getDimension())}.
    *
    * @return the dimension of this unit.
    * @see #isCompatible(Unit)
    */
   public Dimension getDimension()
   {
      Unit<?> systemUnit = this.getSystemUnit();
      if(systemUnit instanceof BaseUnit) {
         return systemUnit.getDimension();
      }
      if(systemUnit instanceof AlternateUnit) {
         return ((AlternateUnit<?>) systemUnit).getParentUnit().getDimension();
      }
      // Product of units.
      ProductUnit<?> productUnit = (ProductUnit<?>) systemUnit;
      Dimension dimension = Dimension.NONE;
      for(int i = 0; i < productUnit.getUnitCount(); i++) {
         Unit<?> unit = productUnit.getUnit(i);
         Dimension d = unit.getDimension().pow(productUnit.getUnitPow(i)).root(productUnit.getUnitRoot(i));
         dimension = dimension.multiply(d);
      }
      return dimension;
   }


   /**
    * Indicates if this unit is a system unit (base units and alternate
    * units are system units). The system unit identifies the "type"
    * of {@link Quantity quantity} for which the unit is employed.
    *
    * @return <code>getSystemUnit().equals(this)</code>
    */
   public boolean isSystemUnit()
   {
      return getSystemUnit().equals(this);
   }

   /**
    * Returns the unscaled system unit from which this unit is derived.
    * System units are either base units, {@linkplain #alternate(String)
    * alternate} units or product of rational powers of system units.
    * <p/>
    * Because the system unit is unique by quantity type, it can be be
    * used to identify the quantity given the unit. For example:
    * <p/>
    * <pre>
    *     static boolean isAngularSpeed(Unit<?> unit) {
    *         return unit.getSystemUnit().equals(RADIAN.divide(SECOND));
    *     }
    *     assert isAngularSpeed(REVOLUTION.divide(MINUTE)); // Returns true.
    * </pre>
    * <p/>
    * <i> Note: Having the same system unit is not sufficient to ensure that
    * a converter exists between the two units (e.g. °C/m and K/m).</i>
    *
    * @return the system unit this unit is derived from, or {@code this} if
    * this unit is a system unit.
    */
   public abstract Unit<? super Q> getSystemUnit();


   /**
    * Returns the converter from this unit to its system unit.
    *
    * @return <code>this.getConverterTo(this.getSystemUnit())</code>
    */
   public abstract UnitConverter toSystemUnit();


   /**
    * Indicates if this unit is compatible with the unit specified.
    * Units don't need to be equals to be compatible. For example
    * (assuming {@code ONE} is a dimensionless unit):
    * <p/>
    * <pre>
    *     RADIAN.equals(ONE) == false
    *     RADIAN.isCompatible(ONE) == true
    * </pre>
    *
    * @param that the other unit to compare for compatibility.
    * @return {@code this.getDimension().equals(that.getDimension())}
    * @see #getDimension()
    */
   public final boolean isCompatible(Unit<?> that)
   {
      return (this == that) ||
         this.getSystemUnit().equals(that.getSystemUnit()) ||
         this.getDimension().equals(that.getDimension());
   }

   /**
    * Casts this unit to a parameterized unit of specified nature or throw a
    * {@code ClassCastException} if the dimension of the specified quantity
    * and this unit's dimension do not match. For example:
    * <p/>
    * <pre>
    *      Unit<Speed> C = METRE.times(299792458).divide(SECOND).asType(Speed.class);
    * </pre>
    *
    * @param <T>  The type of the quantity measured by the unit.
    * @param type the quantity class identifying the nature of the unit.
    * @return this unit parameterized with the specified type.
    * @throws ClassCastException if the dimension of this unit is different
    *                            from the specified quantity dimension.
    */
   @SuppressWarnings("unchecked")
   public final <T extends Quantity<T>> Unit<T> asType(Class<T> type)
      throws ClassCastException
   {
      Dimension dim1 = this.getDimension();
      Unit<T> u;
      try {
         u = (Unit<T>) type.getField("UNIT").get(null);
      } catch(Exception e) {
         throw new Error(e);
      }
      Dimension dim2 = u.getDimension();
      if(!dim1.equals(dim2)) {
         throw new ClassCastException();
      }
      return (Unit<T>) this;
   }

   /**
    * Returns a converter of numeric values from this unit to another unit of same type.
    *
    * @param that the unit of same type to which to convert the numeric values.
    * @return the converter from this unit to {@code that} unit.
    * @throws UnconvertibleException if a converter cannot be constructed.
    */
   public final UnitConverter getConverterTo(Unit<?> that)
      throws UnconvertibleException
   {

      if((this == that) || this.equals(that)) return UnitConverter.IDENTITY; // Shortcut.
      Unit<?> thisSystemUnit = this.getSystemUnit();
      Unit<?> thatSystemUnit = that.getSystemUnit();

      if(thisSystemUnit.equals(thatSystemUnit)) {
         UnitConverter thisToSI = this.toSystemUnit();
         UnitConverter thatToSI = that.getConverterTo(thatSystemUnit);
         return thatToSI.inverse().concatenate(thisToSI);
      }


      // Use dimensional transforms.
      if(!isCompatible(that)) {
         throw new UnconvertibleException(this + " is not compatible with " + that);
      }

      // TODO Must impl the dimensional transform.. Two different Impls between old/new code
      return null;

   }


   /**********************/
   /** Units Operations **/
   /**********************/

   /**
    * Returns a system unit equivalent to this unscaled standard unit but used
    * in expressions to distinguish between quantities of a different nature
    * but of the same dimensions.
    * <p/>
    * <p>Examples of alternate units:</p>
    * <p/>
    * <pre>
    *     Unit<Angle> RADIAN = ONE.alternate("rad").asType(Angle.class);
    *     Unit<Force> NEWTON = METRE.times(KILOGRAM).divide(SECOND.pow(2)).alternate("N").asType(Force.class);
    *     Unit<Pressure> PASCAL = NEWTON.divide(METRE.pow(2)).alternate("Pa").asType(Pressure.class);
    * </pre>
    *
    * @param symbol the new symbol for the alternate unit.
    * @return the alternate unit.
    * @throws UnsupportedOperationException if this unit is not an unscaled standard unit.
    * @throws IllegalArgumentException      if the specified symbol is already
    *                                       associated to a different unit.
    */
   public final Unit<Q> alternate(String symbol)
   {
      return new AlternateUnit<>(this, symbol);
   }


   // TODO Add CompoundUnit???


   // Transformations

   /**
    * Returns the result of setting the origin of the scale of measurement to the given value.
    * The returned unit is convertible with all units that are convertible with this unit.
    * For example the following code:
    * <p/>
    * <pre>
    *    CELSIUS = KELVIN.shift(273.15);
    * </pre>
    * <p/>
    * creates a new unit where 0°C (the origin of the new unit) is equals to 273.15 K.
    * Converting from the old unit to the new one is equivalent to <em>subtracting</em>
    * the offset to the value in the old unit.
    *
    * @param offset the offset added (expressed in this unit).
    * @return this unit offset by the specified value.
    */
   public final Unit<Q> shift(BigDecimal offset)
   {
      if(equals(offset, ZERO)) return this;
      return transform(new AddConverter(offset));
   }

   /**
    * @param offset the offset added (expressed in this unit).
    * @return this unit offset by the specified value.
    * @see #shift(java.math.BigDecimal)
    */
   public final Unit<Q> shift(long offset)
   {
      if(offset == 0) return this;
      return transform(new AddConverter(BigDecimal.valueOf(offset)));
   }


   /**
    * Returns the result of multiplying this unit by the specified factor.
    * If the factor is an integer value, the multiplication is exact
    * (recommended). For example:
    * <p/>
    * <pre>
    *    FOOT = METRE.multiply(3048).divide(10000); // Exact definition.
    *    ELECTRON_MASS = KILOGRAM.multiply(9.10938188e-31); // Approximation.
    * </pre>
    *
    * @param factor the multiplier
    * @return this unit scaled by the specified factor.
    */
   public final Unit<Q> multiply(BigDecimal factor)
   {
      if(equals(factor, ONE)) return this;
      if(factor.scale() == 0) return transform(new RationalConverter(factor, ONE));
      return transform(new MultiplyConverter(factor));
   }

   public final Unit<Q> multiply(long factor)
   {
      if(factor == 1) return this;
      return transform(new RationalConverter(factor, 1));
   }


   /**
    * Returns the result of dividing this unit by an approximate divisor.
    * If the factor is an integer value, the division is exact.
    * For example:
    * <p/>
    * <pre>
    *    QUART = GALLON_LIQUID_US.divide(4); // Exact definition.
    * </pre>
    *
    * @param divisor the divisor value.
    * @return this unit divided by the specified divisor.
    */
   public final Unit<Q> divide(BigDecimal divisor)
   {
      if(equals(divisor, ONE)) return this;
      if(divisor.scale() == 0) return transform(new RationalConverter(ONE, divisor));
      return transform(new DivideConverter(divisor));
   }

   public final Unit<Q> divide(long divisor)
   {
      if(divisor == 1) return this;
      return transform(new RationalConverter(1, divisor));
   }


   /**
    * Returns the unit derived from this unit using the specified converter.
    * The converter does not need to be linear. For example:
    * <pre>
    *   Unit<Dimensionless> DECIBEL = Unit.ONE.transform(
    *      new LogConverter(10).inverse().concatenate(
    *      new RationalConverter(1, 10)));
    * </pre>
    *
    * @param converter the converter from the transformed unit to this unit.
    * @return the unit after the specified transformation.
    */
   @SuppressWarnings("unchecked")
   public final Unit<Q> transform(UnitConverter converter)
   {
      Unit<?> systemUnit = this.getSystemUnit();
      UnitConverter cvtr = this.toSystemUnit().concatenate(converter);
      if(cvtr.equals(UnitConverter.IDENTITY)) return (Unit<Q>) systemUnit;
      return (Unit<Q>) new TransformedUnit<>(systemUnit, cvtr);
   }


   // Products

   /**
    * Returns the inverse of this unit.
    *
    * @return {@code 1 / this}
    */
   public final Unit<?> inverse()
   {
      if(this.equals(SI.ONE)) return this;
      return ProductUnit.getQuotientInstance(SI.ONE, this);
   }


   /**
    * Returns the product of this unit with the one specified.
    *
    * @param factor the unit multiplier.
    * @return {@code this * multiplier}
    */
   public final Unit<?> multiply(Unit<?> factor)
   {
      if(this.equals(SI.ONE)) return factor;
      if(factor.equals(SI.ONE)) return this;
      return ProductUnit.getProductInstance(this, factor);
   }


   /**
    * Returns the quotient of this unit with the one specified.
    *
    * @param divisor the unit divisor.
    * @return {@code this / divisor}
    */
   public final Unit<?> divide(Unit<?> divisor)
   {
      return this.multiply(divisor.inverse());
   }


   /**
    * Returns a unit equals to the given root of this unit.
    *
    * @param n the root's order.
    * @return the result of taking the given root of this unit.
    * @throws ArithmeticException if {@code n == 0} or if this operation
    *                             would result in an unit with a fractional exponent.
    */
   public final Unit<?> root(int n)
   {
      if(n > 0) return ProductUnit.getRootInstance(this, n);
      else if(n == 0) throw new ArithmeticException("Root's order of zero");
      return SI.ONE.divide(this.root(-n));
   }

   /**
    * Returns a unit equals to this unit raised to an exponent.
    *
    * @param n the exponent.
    * @return the result of raising this unit to the exponent.
    */
   public final Unit<?> pow(int n)
   {
      if(n > 0) return this.multiply(this.pow(n - 1));   // TODO Is this correct? Not ProductUnit.getPowInstance()???
      else if(n == 0) return SI.ONE;
      return SI.ONE.divide(this.pow(-n));
   }



   private boolean equals(BigDecimal one, BigDecimal two)
   {
      return one.compareTo(two) == 0;
   }


}
