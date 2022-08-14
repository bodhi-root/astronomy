package com.futurestats.astronomy.algos.meeus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.futurestats.astronomy.algos.meeus.Chapter7;

public class Chapter7Test {

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
	}

	/**
	 * Tests the conversion of the given astronomical date (year, month, day)
	 * into a julian date value and back again.
	 */
	public void test(double jdValue, int year, int month, double day) {
		double jd = Chapter7.julianDateFor(year, month, day);
		assertEquals(jdValue, jd, 1e-6);
		
		Structs.AstronomicalDate date = Chapter7.julianDateToCalendar(jd);
		assertEquals(year, date.year);
		assertEquals(month, date.month);
		assertEquals(day, date.day, 1e-6);
	}
	
}
