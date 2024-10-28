package xpertss.measure.quantity;


import org.junit.Test;
import xpertss.measure.unit.Unit;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static xpertss.measure.unit.SI.*;
import static xpertss.measure.unit.NonSI.*;

public class TimeTest {


   @Test
   public void testTimeUnitIsStandardUnit()
   {
      Unit<Time> soCalledStdUnit = Time.UNIT;
      assertSame(soCalledStdUnit, soCalledStdUnit.getStandardUnit());
   }


   // SI

   @Test
   public void testSecondsConvertToSeconds()
   {
      Unit<Time> seconds = Time.UNIT;
      // 1 seconds should convert to one SECOND
      assertEquals(BigDecimal.ONE, seconds.getConverterTo(SECOND).convert(BigDecimal.ONE));
   }

   @Test
   public void testSecondsConvertToMinute()
   {
      Unit<Time> seconds = Time.UNIT;
      // 60 seconds should convert to one MINUTE
      assertEquals(BigDecimal.ONE, seconds.getConverterTo(MINUTE).convert(new BigDecimal("60")));
   }

   @Test
   public void testSecondsConvertToHour()
   {
      Unit<Time> seconds = Time.UNIT;
      // 3600 seconds should convert to one HOUR
      assertEquals(BigDecimal.ONE, seconds.getConverterTo(HOUR).convert(new BigDecimal("3600")));
   }


   // NonSI

   @Test
   public void testSecondsConvertToDay()
   {
      Unit<Time> seconds = Time.UNIT;
      // 86400 seconds should convert to one DAY
      assertEquals(BigDecimal.ONE, seconds.getConverterTo(DAY).convert(new BigDecimal("86400")));
   }

   @Test
   public void testSecondsConvertToWeek()
   {
      Unit<Time> seconds = Time.UNIT;
      // 604800 seconds should convert to one WEEK
      assertEquals(BigDecimal.ONE, seconds.getConverterTo(WEEK).convert(new BigDecimal("604800")));
   }

   @Test
   public void testSecondsConvertToCalendarYear()
   {
      Unit<Time> seconds = Time.UNIT;
      // 31536000 seconds should convert to one YEAR_CALENDAR
      assertEquals(BigDecimal.ONE, seconds.getConverterTo(YEAR_CALENDAR).convert(new BigDecimal("31536000")));
   }



   @Test
   public void testSecondsConvertToISOYear()
   {
      Unit<Time> seconds = Time.UNIT;
      // 31556952 seconds should convert to one YEAR
      assertEquals(BigDecimal.ONE, seconds.getConverterTo(YEAR).convert(new BigDecimal("31556952")));
   }

   @Test
   public void testISOYearConvertToSecond()
   {
      Unit<Time> year = YEAR;
      // 31556952 seconds should convert to one YEAR
      assertEquals(new BigDecimal("31556952"), year.getConverterTo(SECOND).convert(BigDecimal.ONE));
   }



   @Test
   public void testSecondsConvertToISOMonth()
   {
      Unit<Time> seconds = Time.UNIT;
      // 2629746 seconds should convert to one MONTH
      assertEquals(BigDecimal.ONE, seconds.getConverterTo(MONTH).convert(new BigDecimal("2629746")));
   }

   @Test
   public void testISOMonthConvertToSeconds()
   {
      Unit<Time> month = MONTH;
      // one month should convert to 2629746 SECONDS
      assertEquals(new BigDecimal("2629746"), month.getConverterTo(SECOND).convert(BigDecimal.ONE));
   }



   @Test
   public void testMonthConvertsToYear()
   {
      Unit<Time> month = MONTH;
      // 12 months should convert to one YEAR
      assertEquals(BigDecimal.ONE, month.getConverterTo(YEAR).convert(new BigDecimal("12")));
   }

   @Test
   public void testYearConvertsToMonth()
   {
      Unit<Time> year = YEAR;
      // 1 year should convert to 12 MONTHS
      assertEquals(new BigDecimal("12"), year.getConverterTo(MONTH).convert(BigDecimal.ONE));
   }


   @Test
   public void testCalendarYearConvertsToDays()
   {
      Unit<Time> year = YEAR_CALENDAR;
      // 1 calendar year should convert to 365 days
      assertEquals(new BigDecimal("365"), year.getConverterTo(DAY).convert(BigDecimal.ONE));
   }



}