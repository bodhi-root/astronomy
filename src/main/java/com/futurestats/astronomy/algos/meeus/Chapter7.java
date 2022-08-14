package com.futurestats.astronomy.algos.meeus;

import com.futurestats.astronomy.algos.meeus.Structs.AstronomicalDate;

/**
 * Calculates Julian Days (JD).  Note that day can be given as a real number.
 * When the fractional component of the day is measured in Dynamical Time
 * it is a Julian Ephemeris Day (JDE).
 */
public class Chapter7 {
	
	/**
	 * Returns TRUE if the date occurred on or before 1852-10-04.
	 */
	public static boolean isJulianCalendar(int year, int month, double day) {
		if (year == 1582) {
			if (month == 10) {
				return day <= 4;
			}
			
			return month < 10;
		}
		
		return year < 1582;
	}

	public static double julianDateFor(AstronomicalDate date) {
		return julianDateFor(date.year, date.month, date.day);
	}
	
	/**
	 * Converts the given calendar date to a Julian Date value.
	 * Formula 7.1.
	 */
	public static double julianDateFor(int year, int month, double day) {
		
		if (month <= 2) {
			year--;
			month += 12;
		}
		
		int a = (int)Math.floor(year / 100.0);
		int b = isJulianCalendar(year, month, day) ? 0 : 2 - a + (int)Math.floor(a / 4.0);
		
		double jd = Math.floor(365.25*(year+4716)) + 
				    Math.floor(30.6001*(month+1)) +
				    day + b - 1524.5;
		
		return jd;
	}
	
	/**
	 * Converts the JulianDate to an astronomical one.
	 */
	public static AstronomicalDate julianDateToCalendar(double jd) {
		jd += 0.5;
		double z = Math.floor(jd);
		double f = jd - z;
		
		int a;
		if (z < 2299161) {
			a = (int)z;
		}
		else {
			double alpha = Math.floor((z - 1867216.25) / 36524.25);
			a = (int)(z + 1 + alpha - Math.floor(alpha/4));
		}
		
		int b = a + 1524;
		int c = (int)Math.floor((b - 122.1) / 365.25);
		int d = (int)Math.floor(365.25 * c);
		int e = (int)Math.floor((b - d) / 30.6001);
		
		double day = b - d - Math.floor(30.6001 * e) + f;
		int month = (e < 14) ? e - 1 : e - 13;
		int year = (month > 2) ? c - 4716 : c - 4715;
		
		return new AstronomicalDate(year, month, day);
	}
	
}
