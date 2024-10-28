/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2010 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.physics.unit;

import java.math.BigDecimal;
import java.math.MathContext;

import org.xpertss.unit.PhysicsDimension;
import org.jscience.physics.dimension.DimensionalModel;
import org.xpertss.unit.PhysicsConverter;
import org.xpertss.unit.converters.AddConverter;
import org.xpertss.unit.converters.MultiplyConverter;
import org.xpertss.unit.converters.RationalConverter;
import org.xpertss.unit.types.AlternateUnit;
import org.xpertss.unit.types.ProductUnit;
import org.xpertss.unit.types.TransformedUnit;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.UnitConverter;

/**
 * <p> The class represents units founded on the seven
 *     {@link SI SI} base units for
 *     seven base quantities assumed to be mutually independent.</p>
 *
 * <p> For all physics units, units conversions are symmetrical:
 *     <code>u1.getConverterTo(u2).equals(u2.getConverterTo(u1).inverse())</code>.
 *     Non-physical units (e.g. currency units) for which conversion is
 *     not symmetrical should have their own separate class hierarchy and
 *     are considered distinct (e.g. financial units), although
 *     they can always be combined with physics units (e.g. "€/Kg", "$/h").</p>
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, July 1, 2011
 */
public abstract class PhysicsUnit<Q extends Quantity<Q>> implements Unit<Q> {

    /**
     * Default constructor.
     */
    protected PhysicsUnit() {
    }





   /**
     * Indicates if this unit belongs to the set of coherent SI units 
     * (unscaled SI units).
     * 
     * The base and coherent derived units of the SI form a coherent set, 
     * designated the set of coherent SI units. The word coherent is used here 
     * in the following sense: when coherent units are used, equations between 
     * the numerical values of quantities take exactly the same form as the 
     * equations between the quantities themselves. Thus if only units from 
     * a coherent set are used, conversion factors between units are never 
     * required.
     *
     * TODO Equivalent to isStandardUnit
     *
     * @return <code>equals(toSI())</code>
     */
    public boolean isSI() {
        PhysicsUnit<Q> si = this.toSI();
        return (this == si) || this.equals(si);
    }

    /**
     * Returns the unscaled {@link SI} unit  from which this unit is derived.
     * 
     * They SI unit can be be used to identify a quantity given the unit.
     * For example:[code]
     *    static boolean isAngularVelocity(PhysicsUnit<?> unit) {
     *        return unit.toSI().equals(RADIAN.divide(SECOND));
     *    }
     *    assert(REVOLUTION.divide(MINUTE).isAngularVelocity()); // Returns true.
     * [/code]
     *
     * TODO Equivalent to getStandardUnit and getSystemUnit
     *
     * @return the unscaled metric unit from which this unit is derived.
     */
    public abstract PhysicsUnit<Q> toSI();

    /**
     * Returns the converter from this unit to its unscaled {@link #toSI SI} 
     * unit.
     *
     * TODO Equivalent to toStandardUnit
     *
     * @return <code>getConverterTo(this.toSI())</code>
     * @see #toSI
     */
    public abstract UnitConverter getConverterToSI();












   /////////////////////////////////////////////////////////
    // Implements org.unitsofmeasurement.Unit<Q> interface //
    /////////////////////////////////////////////////////////



   @Override
   public String getSymbol() {
      return null;
   }


   @Override
   public abstract PhysicsDimension getDimension();





   /**
    * Indicates if this unit is compatible with the unit specified.
    * To be compatible both units must be physics units having
    * the same fundamental dimension.
    *
    * @param that the other unit.
    * @return <code>true</code> if this unit and that unit have equals
    *         fundamental dimension according to the current physics model;
    *         <code>false</code> otherwise.
    */
   @Override
   public final boolean isCompatible(Unit<?> that) {
      if ((this == that) || this.equals(that)) return true;
      if (!(that instanceof PhysicsUnit)) return false;
      PhysicsDimension thisDimension = this.getDimension();
      PhysicsDimension thatDimension = ((PhysicsUnit)that).getDimension();
      if (thisDimension.equals(thatDimension)) return true;

      // TODO Do I really want to use models? I mean am I ever going to use anything other than the standard model?
      DimensionalModel model = DimensionalModel.getCurrent(); // Use dimensional analysis model.
      return model.getFundamentalDimension(thisDimension).equals(model.getFundamentalDimension(thatDimension));
   }



   /**
    * Casts this unit to a parameterized unit of specified nature or throw a
    * ClassCastException if the dimension of the specified quantity and
    * this unit's dimension do not match (regardless whether or not
    * the dimensions are independent or not).
    *
    * @param type the quantity class identifying the nature of the unit.
    * @throws ClassCastException if the dimension of this unit is different
    *         from the {@link SI} dimension of the specified type.
    * @see    SI#getUnit(Class)
    */
   @Override
   public final <T extends Quantity<T>> PhysicsUnit<T> asType(Class<T> type) {
      PhysicsDimension typeDimension = PhysicsDimension.getDimension(type);
      if ((typeDimension != null) && (!this.getDimension().equals(typeDimension)))
         throw new ClassCastException("The unit: " + this + " is not compatible with quantities of type " + type);
      return (PhysicsUnit<T>) this;
   }



    /**
     * Returns the system unit (unscaled SI unit) from which this unit is derived.
     * They can be be used to identify a quantity given the unit. For example:[code]
     *    static boolean isAngularVelocity(PhysicsUnit<?> unit) {
     *        return unit.getSystemUnit().equals(RADIAN.divide(SECOND));
     *    }
     *    assert(REVOLUTION.divide(MINUTE).isAngularVelocity()); // Returns true.
     * [/code]
     *
     * @return the unscaled metric unit from which this unit is derived.
     */
    @Override
    public final PhysicsUnit<Q> getSystemUnit() {
        return toSI();
    }





    @Override
    public final UnitConverter getConverterTo(Unit<Q> that) throws UnconvertibleException {
        if ((this == that) || this.equals(that)) return PhysicsConverter.IDENTITY; // Shortcut.
        Unit<Q> thisSystemUnit = this.getSystemUnit();
        Unit<Q> thatSystemUnit = that.getSystemUnit();
        if (!thisSystemUnit.equals(thatSystemUnit)) return getConverterToAny(that); 
        UnitConverter thisToSI= this.getConverterToSI();
        UnitConverter thatToSI= that.getConverterTo(thatSystemUnit);
        return thatToSI.inverse().concatenate(thisToSI);    
    }

    @Override
    public final UnitConverter getConverterToAny(Unit<?> that) throws IncommensurableException,
            UnconvertibleException {
        if (!isCompatible(that))
            throw new IncommensurableException(this + " is not compatible with " + that);
        PhysicsUnit thatPhysics = (PhysicsUnit)that; // Since both units are compatible they must be both physics units.
        DimensionalModel model = DimensionalModel.getCurrent();
        PhysicsUnit thisSystemUnit = this.getSystemUnit();
        UnitConverter thisToDimension = model.getDimensionalTransform(thisSystemUnit.getDimension()).concatenate(this.getConverterToSI());
        PhysicsUnit thatSystemUnit = thatPhysics.getSystemUnit();
        UnitConverter thatToDimension = model.getDimensionalTransform(thatSystemUnit.getDimension()).concatenate(thatPhysics.getConverterToSI());
        return thatToDimension.inverse().concatenate(thisToDimension);
    }





























    @Override
    public final Unit<Q> alternate(String symbol) {
        return new AlternateUnit<>(this, symbol);
    }

    @Override
    public final Unit<Q> transform(UnitConverter operation) {
        PhysicsUnit<Q> systemUnit = this.getSystemUnit();
        UnitConverter cvtr = this.getConverterToSI().concatenate(operation);
        if (cvtr.equals(PhysicsConverter.IDENTITY))
            return systemUnit;
        return new TransformedUnit<>(systemUnit, cvtr);
    }




    @Override
    public final Unit<Q> shift(BigDecimal offset) {
       if (offset.equals(BigDecimal.ZERO))
            return this;
        return transform(new AddConverter(offset));
    }

    @Override
    public final Unit<Q> multiply(BigDecimal factor) {
       if (factor.equals(BigDecimal.ONE))
          return this;
       if(factor.scale() == 0)
          return transform(new RationalConverter(factor, BigDecimal.ONE));
        return transform(new MultiplyConverter(factor));
    }

   /**
    * Returns the result of dividing this unit by the specifified divisor.
    * If the factor is an integer value, the division is exact.
    * For example:<pre><code>
    *    QUART = GALLON_LIQUID_US.divide(4); // Exact definition.
    * </code></pre>
    * @param divisor the divisor value.
    * @return this unit divided by the specified divisor.
    */
   @Override
   public final Unit<Q> divide(BigDecimal divisor) {
      if (divisor.equals(BigDecimal.ONE))
         return this;
      if(divisor.scale() == 0)
         return transform(new RationalConverter(BigDecimal.ONE, divisor));
      return transform(new MultiplyConverter(BigDecimal.ONE.divide(divisor, MathContext.DECIMAL128)));
   }





    /**
     * Returns the product of this unit with the one specified.
     *
     * <p> Note: If the specified unit (that) is not a physical unit, then
     * <code>that.multiply(this)</code> is returned.</p>
     *
     * @param that the unit multiplicand.
     * @return <code>this * that</code>
     */
    @Override
    public final Unit<?> multiply(Unit<?> that) {
        if (that instanceof PhysicsUnit)
            return multiply((PhysicsUnit<?>) that);
        return that.multiply(this); // Commutatif.
    }

    /**
     * Returns the product of this physical unit with the one specified.
     *
     * @param that the physical unit multiplicand.
     * @return <code>this * that</code>
     */
    public final PhysicsUnit<?> multiply(PhysicsUnit<?> that) {
        if (this.equals(SI.ONE))
            return that;
        if (that.equals(SI.ONE))
            return this;
        return ProductUnit.getProductInstance(this, that);
    }

    /**
     * Returns the inverse of this physical unit.
     *
     * @return <code>1 / this</code>
     */
    @Override
    public final PhysicsUnit<?> inverse() {
        if (this.equals(SI.ONE))
            return this;
        return ProductUnit.getQuotientInstance(SI.ONE, this);
    }


    /**
     * Returns the quotient of this unit with the one specified.
     *
     * @param that the unit divisor.
     * @return <code>this.multiply(that.inverse())</code>
     */
    @Override
    public final Unit<?> divide(Unit<?> that) {
        return this.multiply(that.inverse());
    }

    /**
     * Returns the quotient of this physical unit with the one specified.
     *
     * @param that the physical unit divisor.
     * @return <code>this.multiply(that.inverse())</code>
     */
    public final PhysicsUnit<?> divide(PhysicsUnit<?> that) {
        return this.multiply(that.inverse());
    }

    /**
     * Returns a unit equals to the given root of this unit.
     *
     * @param n the root's order.
     * @return the result of taking the given root of this unit.
     * @throws ArithmeticException if <code>n == 0</code> or if this operation
     *         would result in an unit with a fractional exponent.
     */
    @Override
    public final PhysicsUnit<?> root(int n) {
        if (n > 0)
            return ProductUnit.getRootInstance(this, n);
        else if (n == 0)
            throw new ArithmeticException("Root's order of zero");
        else // n < 0
            return SI.ONE.divide(this.root(-n));
    }

    /**
     * Returns a unit equals to this unit raised to an exponent.
     *
     * @param n the exponent.
     * @return the result of raising this unit to the exponent.
     */
    @Override
    public final PhysicsUnit<?> pow(int n) {
        if (n > 0)
            return this.multiply(this.pow(n - 1));
        else if (n == 0)
            return SI.ONE;
        else // n < 0
            return SI.ONE.divide(this.pow(-n));
    }






    ////////////////////////////////////////////////////////////////
    // Ensures that sub-classes implements hashCode/equals method.
    ////////////////////////////////////////////////////////////////

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object that);

}