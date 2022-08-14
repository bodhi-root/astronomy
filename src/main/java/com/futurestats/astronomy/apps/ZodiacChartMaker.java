package com.futurestats.astronomy.apps;

import java.time.LocalDate;

import com.futurestats.astronomy.Ecliptic;
import com.futurestats.astronomy.EclipticalCoordinate;
import com.futurestats.astronomy.EquatorialCoordinate;
import com.futurestats.astronomy.JulianDate;
import com.futurestats.astronomy.angles.Angle;

public class ZodiacChartMaker {

	/**
	 * The page below calculates the RA for each zodiac house by converting
	 * ecliptical coordinates (where each house is just 30 degrees and begins
	 * with Aries) to equatorial coordinates.  If we set year to 2000 these 
	 * match the results below exactly.
	 * 
	 * https://astronomy.stackexchange.com/questions/27481/what-are-the-ras-of-the-boundaries-between-traditional-zodiacal-signs
	 * 
	 * However, these values don't take into account precession.  For that
	 * we make the adjustment after converting to equatorial coordinates.
	 * The flag 'adjustForPrecession' controls this below.
	 */
	public static void printZodiac(int year, boolean adjustForPrecession) {
		
		String [] HOUSES = new String [] {
				"Aries",
				"Taurus",
				"Gemini",
				"Cancer",
				"Leo",
				"Virgo",
				"Libra",
				"Scorpius",
				"Sagittarius",
				"Capicornus",
				"Aquarius",
				"Pisces"
		};
		
		LocalDate start = JulianDate.forMarchEquinox(year).toDateTime().toLocalDate();
		System.out.println("March Equinox: " + start);
		
		JulianDate jd0 = JulianDate.from(-130, 1, 1);
		JulianDate jd = JulianDate.from(year, 1, 1);
		Angle epsilon0 = Ecliptic.getObliquity(jd0);
		//Angle epsilon = Ecliptic.getObliquity(jd);

		for (int i=0; i<12; i++) {
			EclipticalCoordinate coord = EclipticalCoordinate.fromDegrees(i*30, 0);
			EquatorialCoordinate coord2 = coord.toEquatorialCoordinate(epsilon0);

			if (adjustForPrecession)
				coord2 = coord2.adjustForPrecession(jd0, jd);

			Angle ra = coord2.getRightAscension().normalize();
			
			int days = (int)(ra.toDegrees().value() / 360 * 365);
			LocalDate date = start.plusDays(days);
			
			System.out.println(HOUSES[i] + " (" + (i*30) + " long): " + ra + ", " + ra.toHmsString() + ", " + date.toString());
		}
	}

}
