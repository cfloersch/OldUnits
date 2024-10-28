/**
 * Created By: cfloersch
 * Date: 2/6/2015
 * Copyright 2013 XpertSoftware
 */
package xpertss.measure;

import org.junit.Test;
import xpertss.measure.quantity.Mass;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.MathContext.DECIMAL32;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import static xpertss.measure.unit.NonSI.*;
import static xpertss.measure.unit.SIPrefix.*;
import static xpertss.measure.unit.SI.*;


public class PreciseMathTest {

   private static final BigDecimal THOUSAND = new BigDecimal("1000");

   @Test
   public void testMathContextDecimal32() {
      BigDecimal meters = new BigDecimal("9876.54321");
      System.out.println(meters);
      BigDecimal kilometersOne = meters.divide(THOUSAND, RoundingMode.HALF_EVEN);
      System.out.println(kilometersOne);
      BigDecimal kilometersTwo = meters.divide(THOUSAND, MathContext.DECIMAL32);
      System.out.println(kilometersTwo);
   }

   @Test
   public void testMathContextDecimal64() {
      BigDecimal meters = new BigDecimal("9876.54321");
      System.out.println(meters);
      BigDecimal kilometersOne = meters.divide(THOUSAND, RoundingMode.HALF_EVEN);
      System.out.println(kilometersOne);
      BigDecimal kilometersTwo = meters.divide(THOUSAND, MathContext.DECIMAL64);
      System.out.println(kilometersTwo);
   }

   @Test
   public void testMathContextDecimal128() {
      BigDecimal meters = new BigDecimal("9876.54321");
      System.out.println(meters);
      BigDecimal kilometersOne = meters.divide(THOUSAND, RoundingMode.HALF_EVEN);
      System.out.println(kilometersOne);
      BigDecimal kilometersTwo = meters.divide(THOUSAND, MathContext.DECIMAL128);
      System.out.println(kilometersTwo);
   }


   @Test
   public void testBigDecimalsComparableWithDifferentScales() {
      BigDecimal one = new BigDecimal("1234.50000");
      BigDecimal two = new BigDecimal("1234.5");
      assertFalse(one.equals(two));                // Not equal do to different scale
      assertEquals(0, one.compareTo(two));         // comparably equal as they have the same magnitude
   }


   @Test
   public void testUnit() {
      BigDecimal value = new BigDecimal("64.79891"); // milligrams in Decimal32
      Measurable<Mass> grain = Measure.valueOf(BigDecimal.ONE, GRAIN);
      assertEquals(value, grain.getValue(MILLI(GRAM)).round(DECIMAL32));
   }
}
