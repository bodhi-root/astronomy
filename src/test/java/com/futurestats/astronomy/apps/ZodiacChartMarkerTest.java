package com.futurestats.astronomy.apps;

import com.futurestats.astronomy.EquatorialCoordinate;
import com.futurestats.astronomy.JulianDate;

public class ZodiacChartMarkerTest {

	public static void main(String [] args) {
		//starting point of zodiac:
		JulianDate jd0 = JulianDate.from(-130, 1, 1);
		JulianDate jd = JulianDate.from(2022, 1, 1);
		EquatorialCoordinate zero = EquatorialCoordinate.fromDegrees(0, 0);
		EquatorialCoordinate zero_now = zero.adjustForPrecession(jd0,  jd);
		System.out.println("Aries/Pisces: " + zero_now.getRightAscension().toDegrees().value() + " (degrees)");
		System.out.println();
		
		System.out.println("Unadjusted:");
		ZodiacChartMaker.printZodiac(2022, false);
		System.out.println();
		System.out.println("Adjusted:");
		ZodiacChartMaker.printZodiac(2022, true);
	}
	
}
