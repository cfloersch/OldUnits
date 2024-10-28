package xpertss.measure;

import org.junit.Test;
import xpertss.measure.quantity.Length;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL128;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import static xpertss.measure.unit.SI.*;
import static xpertss.measure.unit.SIPrefix.*;


public class DecimalMeasureTest {

   private final BigDecimal value = new BigDecimal("9876.54321");

   @Test
   public void testSimple() {
      Measurable<Length> length = new DecimalMeasure<>(value, METER);
      assertSame(value, length.getValue());
      assertSame(METER, length.getUnit());
   }

   @Test(expected = NullPointerException.class)
   public void testNullValueThrowsException() {
      Measurable<Length> length = new DecimalMeasure<>(null, METER);
   }


   @Test(expected = NullPointerException.class)
   public void testNullUnitThrowsException() {
      Measurable<Length> length = new DecimalMeasure<>(value, null);
   }

   @Test
   public void testGetValueSameUnit() {
      Measurable<Length> length = new DecimalMeasure<>(value, METER);
      assertSame(value, length.getValue(METER));
   }

   @Test
   public void testGetValueNullUnit() {
      Measurable<Length> length = new DecimalMeasure<>(value, METER);
      assertSame(value, length.getValue(null));
   }



   @Test
   public void testGetValueWithUnitConversion() {
      // In this case we are converting from a base unit (meter) to a sub unit of the same type (aka kilometer)
      Measurable<Length> length = new DecimalMeasure<>(value, METER);
      BigDecimal kiloMeterOne =  length.getValue().divide(new BigDecimal(1000), DECIMAL128);
      BigDecimal kiloMeterTwo = length.getValue(KILO(METER));
      assertEquals(kiloMeterOne, kiloMeterTwo);
   }

   @Test(expected = NullPointerException.class)
   public void testGetAsWithNullUnit() {
      Measurable<Length> length = new DecimalMeasure<>(value, METER);
      length.getAs(null);
   }

   @Test
   public void testGetAsConvertsToKilometerProperly() {
      Measurable<Length> length = new DecimalMeasure<>(value, METER);
      BigDecimal kiloMeterOne =  length.getValue().divide(new BigDecimal(1000), DECIMAL128);
      Measurable<Length> kiloMeterTwo = length.getAs(KILO(METER));
      assertEquals(kiloMeterOne, kiloMeterTwo.getValue());
   }

   @Test
   public void testGetAsConvertsBack() {
      Measurable<Length> length = new DecimalMeasure<>(value, METER);

      Measurable<Length> kiloMeterOne = length.getAs(KILO(METER));
      Measurable<Length> kiloMeterTwo = length.getAs(KILO(METER));

      assertEquals(length.getValue(), kiloMeterOne.getValue(METER));
      assertEquals(length.getValue(), kiloMeterTwo.getValue(METER));
   }

   @Test
   public void testUnitEquals() {
      Measurable<Length> length = new DecimalMeasure<>(value, METER);
      assertTrue(length.equals(length));
      assertTrue(length.equals(new DecimalMeasure<>(value, METER)));
      assertFalse(length.equals(length.getAs(KILO(METER))));
   }

   @Test
   public void testHashCode() {
      Measurable<Length> length = new DecimalMeasure<>(value, METER);
      assertEquals(length.hashCode(), length.hashCode());
      assertEquals(length.hashCode(), new DecimalMeasure<>(value, METER).hashCode());
      assertFalse(length.hashCode() == length.getAs(KILO(METER)).hashCode());
   }

   @Test
   public void testCompareToWithMeasuresOfDifferentScale() {
      Measurable<Length> meters = new DecimalMeasure<>(value, METER);
      Measurable<Length> kilos = new DecimalMeasure<>(new BigDecimal("9.87654321"), KILO(METER));
      assertFalse(meters.equals(kilos));
      assertEquals(0, meters.compareTo(kilos));
   }

   @Test
   public void testDifferentScalars() {
      Measurable<Length> one = new DecimalMeasure<>(new BigDecimal("1.0"), METER);
      Measurable<Length> two = new DecimalMeasure<>(new BigDecimal("1"), METER);
      assertTrue(one.equals(two));
   }
}