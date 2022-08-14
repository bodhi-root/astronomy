package com.futurestats.astronomy.algos.meeus;

public class Chapter12 {

	/**
	 * Calculates Greenwich Mean Sidereal Time for the given Julian
	 * Date.  The result is returned in degrees.
	 * 
	 * Formula 12.4
	 */
	public static double gmst(double jd) {
		double jd_adjust = jd - 2451545.0;
		double t = jd_adjust / 36525;
		double t2 = t * t;
		double deg = 280.46061837 + 
				(360.98564736629 * jd_adjust) +
				(.000387933 * t2) - (t2 / 38710000 * t);
		
		return Utils.normalizeDegrees(deg);
	}
	
}
