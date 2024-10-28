package xpertss.unit.converters;


import org.junit.Test;

import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AddConverterTest {

   @Test
   public void testBasicConversion() {
      AddConverter converter = new AddConverter(BigDecimal.TEN);
      assertEquals(BigDecimal.ZERO, converter.convert(BigDecimal.TEN.negate()));
      assertEquals(BigDecimal.TEN, converter.convert(BigDecimal.ZERO));
   }

   @Test
   public void testInverseConversion() {
      UnitConverter converter = new AddConverter(BigDecimal.TEN).inverse();
      assertEquals(BigDecimal.ZERO, converter.convert(BigDecimal.TEN));
      assertEquals(BigDecimal.TEN.negate(), converter.convert(BigDecimal.ZERO));
   }

   @Test
   public void testConcatenation() {
      AddConverter ten = new AddConverter(BigDecimal.TEN);
      assertTrue(ten.concatenate(ten.inverse()).isIdentity());
      UnitConverter conv = ten.concatenate(ten);
      assertEquals(BigDecimal.valueOf(20), conv.convert(BigDecimal.ZERO));
      conv = ten.concatenate(new AddConverter(BigDecimal.valueOf(11).negate()));
      assertEquals(BigDecimal.ZERO, conv.convert(BigDecimal.ONE));
   }

   @Test
   public void testIsLinear() {
      AddConverter ten = new AddConverter(BigDecimal.TEN);
      assertFalse(ten.isLinear());
   }

   @Test
   public void testEquals() {
      AddConverter first = new AddConverter(BigDecimal.TEN);
      AddConverter second = new AddConverter(BigDecimal.valueOf(10));
      AddConverter third = new AddConverter(BigDecimal.TEN.negate());
      AddConverter fourth = new AddConverter(new BigDecimal("10.0"));
      assertTrue(first.equals(second));
      assertFalse(first.equals(third));
      assertFalse(first.equals(first.inverse()));
      assertTrue(first.equals(third.inverse()));
      assertTrue(first.equals(fourth));
   }

   @Test
   public void testGetOffset() {
      AddConverter first = new AddConverter(BigDecimal.TEN);
      assertEquals(BigDecimal.TEN, first.getOffset());
   }

   @Test
   public void testHashcode() {
      AddConverter first = new AddConverter(BigDecimal.TEN);
      AddConverter second = new AddConverter(BigDecimal.valueOf(10));
      AddConverter third = new AddConverter(BigDecimal.TEN.negate());
      assertTrue(first.hashCode() == second.hashCode());
      assertFalse(first.hashCode() == third.hashCode());
      assertTrue(first.hashCode() == third.inverse().hashCode());
   }

   @Test(expected = NullPointerException.class)
   public void testConstructionNullArgument() {
      new AddConverter(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructionZeroValueArgument() {
      new AddConverter(BigDecimal.ZERO);
   }

}