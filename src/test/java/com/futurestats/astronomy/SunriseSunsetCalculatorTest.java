package com.futurestats.astronomy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SunriseSunsetCalculatorTest {

	@Test
	public void test() {
		SunriseSunsetCalculator calc = new SunriseSunsetCalculator(GeoContext.CINCINNATI);
		
		//LocalDate date = LocalDate.of(2022, 1, 1);
		LocalDate date = LocalDate.of(2022, 8, 5);
		
		System.out.println("Sunrise: " + calc.calculateSunrise(date));
		System.out.println("Sunset:  " + calc.calculateSunset(date));
		
		assertEquals("2022-08-05T06:41:51.227357958-04:00[America/New_York]", 
				     calc.calculateSunrise(date).toString()); 
		assertEquals("2022-08-05T20:45:38.687425671-04:00[America/New_York]",
				     calc.calculateSunset(date).toString());
	}
	
}
