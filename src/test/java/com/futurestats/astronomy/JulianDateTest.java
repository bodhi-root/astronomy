package com.futurestats.astronomy;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;


public class JulianDateTest {

	static ZoneId UTC = ZoneId.of("UTC");
	
	@Test
	public void test() {
		
		//suggested tests from Astronomical Algorithms:
		test(2451545.0,  2000,  1,  1.5);
		test(2451179.5,  1999,  1,  1.0);
		test(2446822.5,  1987,  1, 27.0);
		test(2446966.0,  1987,  6, 19.5);
		test(2447187.5,  1988,  1, 27.0);
		test(2447332.0,  1988,  6, 19.5);
		test(2415020.5,  1900,  1,  1.0);
		test(2305447.5,  1600,  1,  1.0);
		test(2305812.5,  1600, 12, 31.0);
		test(2026871.8,   837,  4, 10.3);
		test(1676496.5,  -123, 12, 31.0);
		test(1676497.5,  -122,  1,  1.0);
		test(1356001.0, -1000,  7, 12.5);
		test(1355866.5, -1000,  2, 29.0);
		test(1355671.4, -1001,  8, 17.9);
		test(      0.0, -4712,  1,  1.5);
		
		//using LocalDate in GMT/UTC (only after Gregorian cutover on 1852-10-15)
		test(2451545.0,  LocalDate.of(2000,  1,  1), LocalTime.NOON);
		test(2451179.5,  LocalDate.of(1999,  1,  1), LocalTime.MIDNIGHT);
		test(2446822.5,  LocalDate.of(1987,  1, 27), LocalTime.MIDNIGHT);
		test(2446966.0,  LocalDate.of(1987,  6, 19), LocalTime.NOON);
		test(2447187.5,  LocalDate.of(1988,  1, 27), LocalTime.MIDNIGHT);
		test(2447332.0,  LocalDate.of(1988,  6, 19), LocalTime.NOON);
		test(2415020.5,  LocalDate.of(1900,  1,  1), LocalTime.MIDNIGHT);
		test(2305447.5,  LocalDate.of(1600,  1,  1), LocalTime.MIDNIGHT);
		test(2305812.5,  LocalDate.of(1600, 12, 31), LocalTime.MIDNIGHT);
	}
	
	/**
	 * Tests the conversion of the given astronomical date (year, month, day)
	 * into a julian date value and back again.
	 */
	public void test(double jdValue, int year, int month, double day) {
		AstronomicalDate ad = AstronomicalDate.of(year, month, day);
		JulianDate jd = JulianDate.from(ad);
		assertEquals(jdValue, jd.value());
		
		AstronomicalDate ad2 = jd.toAstronomicalDate();
		assertEquals(ad.toString(), ad2.toString());
	}
	
	/**
	 * Tests the conversion of LocalDate and LocalTime in GMT/UTC to a 
	 * JuliandDate and back again.
	 */
	public void test(double jdValue, LocalDate date, LocalTime time) {
		ZonedDateTime zdate = ZonedDateTime.of(date, time, UTC);
		JulianDate jd = JulianDate.from(zdate);
		assertEquals(jdValue, jd.value(), 1e-6);
		
		ZonedDateTime zdate2 = jd.toDateTime(UTC);
		assertEquals(zdate, zdate2);
		assertEquals(date, zdate.toLocalDate());
	}
	
}
