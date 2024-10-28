package org.xpertss.measure.quantity;

import org.junit.Test;

import org.xpertss.measure.units.SI;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TemperatureTest {

   @Test
   public void testTemperature() {
      assertEquals(new BigDecimal("273.15"), SI.CELSIUS.getConverterTo(SI.KELVIN).convert(BigDecimal.ZERO));
      assertEquals(new BigDecimal("-273.15"), SI.KELVIN.getConverterTo(SI.CELSIUS).convert(BigDecimal.ZERO));
   }

}