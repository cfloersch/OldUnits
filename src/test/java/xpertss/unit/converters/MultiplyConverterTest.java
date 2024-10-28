package xpertss.unit.converters;


import org.junit.Test;

import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;

import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MultiplyConverterTest {

   @Test
   public void testBasicConversion() {
      MultiplyConverter converter = new MultiplyConverter(BigDecimal.TEN);
      assertEquals(BigDecimal.ZERO, converter.convert(BigDecimal.ZERO));
      assertEquals(BigDecimal.TEN, converter.convert(BigDecimal.ONE));
   }

   @Test
   public void testInverseConversion() {
      MultiplyConverter normal = new MultiplyConverter(BigDecimal.TEN);
      UnitConverter inverse = normal.inverse();
      assertEquals(BigDecimal.TEN, inverse.convert(BigDecimal.valueOf(100)));
      assertEquals(normal, inverse.inverse());
   }

   @Test
   public void testConcatenation() {
      MultiplyConverter ten = new MultiplyConverter(BigDecimal.TEN);
      assertTrue(ten.concatenate(ten.inverse()).isIdentity());

      UnitConverter conv = ten.concatenate(ten);
      assertEquals(BigDecimal.valueOf(100), conv.convert(BigDecimal.ONE));
   }

   @Test
   public void testIsLinear() {
      MultiplyConverter ten = new MultiplyConverter(BigDecimal.TEN);
      assertTrue(ten.isLinear());
   }

   @Test
   public void testEquals() {
      MultiplyConverter first = new MultiplyConverter(BigDecimal.TEN);
      MultiplyConverter second = new MultiplyConverter(BigDecimal.valueOf(10));
      MultiplyConverter third = new MultiplyConverter(new BigDecimal("10.0"));
      assertTrue(first.equals(second));
      assertTrue(first.equals(third));
      assertFalse(first.equals(first.inverse()));
   }

   @Test
   public void testGetFactor() {
      MultiplyConverter first = new MultiplyConverter(BigDecimal.TEN);
      assertEquals(BigDecimal.TEN, first.getFactor());
   }

   @Test
   public void testHashcode() {
      MultiplyConverter first = new MultiplyConverter(BigDecimal.TEN);
      MultiplyConverter second = new MultiplyConverter(BigDecimal.valueOf(10));
      MultiplyConverter third = new MultiplyConverter(new BigDecimal(".1"));
      assertTrue(first.hashCode() == second.hashCode());
      assertFalse(first.hashCode() == third.hashCode());
   }

   @Test(expected = NullPointerException.class)
   public void testConstructionNullArgument() {
      new MultiplyConverter(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructionOneValueArgument() {
      new MultiplyConverter(BigDecimal.ONE);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructionZeroValueArgument() {
      new MultiplyConverter(BigDecimal.ZERO);
   }



   @Test
   public void testConcatenateToIdentity() {
      MultiplyConverter first = new MultiplyConverter(new BigDecimal(".5"));
      MultiplyConverter second = new MultiplyConverter(BigDecimal.valueOf(2));
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
   }

   @Test
   public void testConcatenateToIdentityViaDivide() {
      MultiplyConverter first = new MultiplyConverter(BigDecimal.valueOf(2));
      DivideConverter second = new DivideConverter(BigDecimal.valueOf(2));
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
   }

   @Test
   public void testConcatenateToIdentityViaRational() {
      MultiplyConverter first = new MultiplyConverter(BigDecimal.valueOf(2));
      RationalConverter second = new RationalConverter(BigDecimal.ONE, BigDecimal.valueOf(2));
      UnitConverter conv = first.concatenate(second);
      assertSame(UnitConverter.IDENTITY, conv);
   }

   @Test
   public void testConcatenateWithDivide() {
      MultiplyConverter first = new MultiplyConverter(BigDecimal.valueOf(2));
      DivideConverter second = new DivideConverter(BigDecimal.TEN);
      UnitConverter conv = first.concatenate(second);

      assertEquals(BigDecimal.ONE, conv.convert(BigDecimal.valueOf(5)));
      assertEquals(new BigDecimal(".4"), conv.convert(BigDecimal.valueOf(2)));
   }


}