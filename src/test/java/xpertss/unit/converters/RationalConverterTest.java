package xpertss.unit.converters;


import org.junit.Test;

import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;

import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static java.math.BigDecimal.*;

public class RationalConverterTest  {

   @Test
   public void testComponents() {
      RationalConverter converter = new RationalConverter(10, 1);
      assertEquals(TEN, converter.getDividend());
      assertEquals(ONE, converter.getDivisor());
      RationalConverter inverse = (RationalConverter) converter.inverse();
      assertEquals(converter.getDividend(), inverse.getDivisor());
      assertEquals(converter.getDivisor(), inverse.getDividend());
   }

   @Test
   public void testBasicConversion() {
      RationalConverter converter = new RationalConverter(10, 1);
      assertEquals(TEN, converter.convert(ONE));
      assertEquals(valueOf(100), converter.convert(TEN));
   }

   @Test(expected = IllegalArgumentException.class)
   public void testNegativeDivisorThrowsException() {
      new RationalConverter(10, -1);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testZeroDivisorThrowsException() {
      new RationalConverter(10, 0);
   }

   @Test(expected = NullPointerException.class)
   public void testNullDivisorThrowsException() {
      new RationalConverter(ONE, null);
   }

   @Test(expected = NullPointerException.class)
   public void testNullDiviidendThrowsException() {
      new RationalConverter(null, ONE);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDivisorDividendEqualThrowsException() {
      new RationalConverter(10, 10);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testZeroDividendThrowsException() {
      // multiplying by 0 is legal but its inverse (aka dividing by zero is not)
      new RationalConverter(0, 10);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDivisorDividendSimilarThrowsException() {
      new RationalConverter(TEN, new BigDecimal("10.0"));
   }

   @Test
   public void testIsLinear() {
      RationalConverter ten = new RationalConverter(TEN, ONE);
      assertTrue(ten.isLinear());
   }


   @Test
   public void testBasicInvert() {
      RationalConverter converter = new RationalConverter(10, 1);
      UnitConverter inverted = converter.inverse();
      assertEquals(ONE, inverted.convert(TEN));
   }

   @Test
   public void testInvertWithNegativeDividend() {
      RationalConverter converter = new RationalConverter(-10, 1);
      RationalConverter inverted = (RationalConverter) converter.inverse();
      assertEquals(ONE.negate(), inverted.convert(TEN));
      assertEquals(ONE.negate(), inverted.getDividend());
      assertEquals(TEN, inverted.getDivisor());
   }

   @Test
   public void testEquals() {
      RationalConverter first = new RationalConverter(10, 1);
      RationalConverter second = new RationalConverter(TEN, ONE);
      RationalConverter third = new RationalConverter(ONE, TEN);
      RationalConverter fourth = new RationalConverter(TEN.negate(), ONE);
      assertTrue(first.equals(second));
      assertFalse(first.equals(third));
      assertFalse(second.equals(third));
      assertTrue(second.equals(third.inverse()));
      assertFalse(second.equals(fourth));
   }

   @Test
   public void testHashcode() {
      RationalConverter first = new RationalConverter(10, 1);
      RationalConverter second = new RationalConverter(TEN, ONE);
      RationalConverter third = new RationalConverter(ONE, TEN);
      RationalConverter fourth = new RationalConverter(TEN.negate(), ONE);
      assertTrue(first.hashCode() == second.hashCode());
      assertFalse(first.hashCode() == third.hashCode());
      assertFalse(second.hashCode() == third.hashCode());
      assertTrue(second.hashCode() == third.inverse().hashCode());
      assertFalse(second.hashCode() == fourth.hashCode());
   }


   @Test
   public void testBasicConcatenate() {
      RationalConverter first = new RationalConverter(10, 1);
      RationalConverter second = new RationalConverter(TEN, ONE);
      UnitConverter conv = first.concatenate(second);
      assertEquals(BigDecimal.valueOf(100), conv.convert(ONE));
   }

   @Test
   public void testConcatenateToIdentity() {
      RationalConverter first = new RationalConverter(10, 1);
      RationalConverter second = new RationalConverter(ONE, TEN);
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
      assertEquals(ONE, conv.convert(ONE));
   }


   @Test
   public void testConcatenateToIdentityViaDivide() {
      RationalConverter first = new RationalConverter(10, 1);
      DivideConverter second = new DivideConverter(BigDecimal.TEN);
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
   }

   @Test
   public void testConcatenateToIdentityViaMultiply() {
      RationalConverter first = new RationalConverter(10, 1);
      MultiplyConverter second = new MultiplyConverter(new BigDecimal(".1"));
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
   }

}