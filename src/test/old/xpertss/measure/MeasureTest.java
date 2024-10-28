package xpertss.measure;

import org.junit.Test;
import xpertss.measure.quantity.Length;
import xpertss.measure.unit.SI;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static xpertss.measure.unit.SI.*;
import static xpertss.measure.unit.SIPrefix.*;
import static xpertss.measure.unit.NonSI.*;



public class MeasureTest {

   @Test
   public void testSimple() {
      Measurable<Length> one = Measure.valueOf(new BigDecimal("120"), KILO(METER));
      Measurable<Length> two = Measure.valueOf(120, KILO(METER));
      assertEquals(one, two);
   }

   @Test
   public void testSimpleDecimal() {
      Measurable<Length> one = Measure.valueOf(new BigDecimal(".987654321"), KILO(METER));
      Measurable<Length> two = Measure.valueOf(".987654321", KILO(METER));
      assertEquals(one, two);
   }

   @Test
   public void testSimpleFloatingPoint() {
      Measurable<Length> one = Measure.valueOf(1.5D, KILO(METER));
      Measurable<Length> two = Measure.valueOf(1.5F, KILO(METER));
      assertEquals(one, two);
   }


   @Test(expected = NullPointerException.class)
   public void testNullUnitOnBigDecimal() {
      Measure.valueOf(BigDecimal.ONE, null);
   }

   @Test(expected = NullPointerException.class)
   public void testNullUnitOnDouble() {
      Measure.valueOf(100D, null);
   }

   @Test(expected = NullPointerException.class)
   public void testNullUnitOnFloat() {
      Measure.valueOf(100F, null);
   }

   @Test(expected = NullPointerException.class)
   public void testNullUnitOnLong() {
      Measure.valueOf(100L, null);
   }

   @Test(expected = NullPointerException.class)
   public void testNullUnitOnInt() {
      Measure.valueOf(100, null);
   }

   @Test(expected = NullPointerException.class)
   public void testNullUnitOnString() {
      Measure.valueOf("100", null);
   }


   @Test(expected = NullPointerException.class)
   public void testNullStringOnString() {
      Measure.valueOf((String)null, SI.METER);
   }

   @Test(expected = NullPointerException.class)
   public void testNullDecimalOnDecimal() {
      Measure.valueOf((BigDecimal)null, SI.METER);
   }


   @Test
   public void testDifferentScalesOfSameDimension() {
      Measurable<Length> kiloMeters = Measure.valueOf(new BigDecimal(".987654321"), KILO(METER));
      Measurable<Length> meters = kiloMeters.getAs(METER);
      Measurable<Length> miles = kiloMeters.getAs(MILE);
      Measurable<Length> feet = kiloMeters.getAs(FOOT);

      assertEquals(0, feet.compareTo(kiloMeters));
      assertEquals(0, feet.compareTo(meters));
      assertEquals(0, kiloMeters.compareTo(meters));
      assertEquals(0, feet.compareTo(miles));
      assertEquals(0, kiloMeters.compareTo(miles));
   }

}