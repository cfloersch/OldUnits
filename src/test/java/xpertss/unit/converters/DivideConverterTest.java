package xpertss.unit.converters;


import org.junit.Test;

import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;

import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DivideConverterTest {


   @Test
   public void testBasicConversion() {
      DivideConverter converter = new DivideConverter(BigDecimal.TEN);
      assertEquals(BigDecimal.ONE, converter.convert(BigDecimal.TEN));
      assertEquals(BigDecimal.TEN, converter.convert(new BigDecimal(100)));
   }

   @Test
   public void testInverseConversion() {
      DivideConverter normal = new DivideConverter(BigDecimal.TEN);
      UnitConverter inverse = normal.inverse();
      assertEquals(BigDecimal.TEN, inverse.convert(BigDecimal.ONE));
      assertEquals(normal, inverse.inverse());
   }

   @Test
   public void testConcatenation() {
      DivideConverter ten = new DivideConverter(BigDecimal.TEN);
      assertTrue(ten.concatenate(ten.inverse()).isIdentity());

      UnitConverter conv = ten.concatenate(ten);
      assertEquals(BigDecimal.ONE, conv.convert(BigDecimal.valueOf(100)));
   }

   @Test
   public void testIsLinear() {
      DivideConverter ten = new DivideConverter(BigDecimal.TEN);
      assertTrue(ten.isLinear());
   }

   @Test
   public void testEquals() {
      DivideConverter first = new DivideConverter(BigDecimal.TEN);
      DivideConverter second = new DivideConverter(BigDecimal.valueOf(10));
      DivideConverter third = new DivideConverter(new BigDecimal("10.0"));
      assertTrue(first.equals(second));
      assertTrue(first.equals(third));
      assertFalse(first.equals(first.inverse()));
   }

   @Test
   public void testGetFactor() {
      DivideConverter first = new DivideConverter(BigDecimal.TEN);
      assertEquals(BigDecimal.TEN, first.getFactor());
   }

   @Test
   public void testHashcode() {
      DivideConverter first = new DivideConverter(BigDecimal.TEN);
      DivideConverter second = new DivideConverter(BigDecimal.valueOf(10));
      DivideConverter third = new DivideConverter(new BigDecimal(".1"));
      assertTrue(first.hashCode() == second.hashCode());
      assertFalse(first.hashCode() == third.hashCode());
   }

   @Test(expected = NullPointerException.class)
   public void testConstructionNullArgument() {
      new DivideConverter(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructionOneValueArgument() {
      new DivideConverter(BigDecimal.ONE);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructionZeroValueArgument() {
      new DivideConverter(BigDecimal.ZERO);
   }




   @Test
   public void testConcatenateToIdentity() {
      DivideConverter first = new DivideConverter(new BigDecimal(".5"));
      DivideConverter second = new DivideConverter(BigDecimal.valueOf(2));
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
   }

   @Test
   public void testConcatenateToIdentityViaMultiply() {
      DivideConverter first = new DivideConverter(BigDecimal.valueOf(2));
      MultiplyConverter second = new MultiplyConverter(BigDecimal.valueOf(2));
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
   }

   @Test
   public void testConcatenateToIdentityViaRational() {
      DivideConverter first = new DivideConverter(BigDecimal.valueOf(2));
      RationalConverter second = new RationalConverter(BigDecimal.valueOf(2), BigDecimal.ONE);
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
   }


   @Test
   public void testConcatenateWithMultiply() {
      DivideConverter first = new DivideConverter(BigDecimal.valueOf(2));
      MultiplyConverter second = new MultiplyConverter(BigDecimal.TEN);
      UnitConverter conv = first.concatenate(second);
      assertEquals(BigDecimal.valueOf(25), conv.convert(BigDecimal.valueOf(5)));
      assertEquals(BigDecimal.TEN, conv.convert(BigDecimal.valueOf(2)));
      assertEquals(new BigDecimal(".5"), conv.convert(new BigDecimal(".1")));
   }
}