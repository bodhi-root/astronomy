package com.futurestats.astronomy.algos.meeus;

public class Utils {

	public static final int SECONDS_PER_DAY = 24 * 60 * 60;

	public static final double JD_J2000 = 2451545.0;
	
	// --- Angles --------------------------------------------------------------
	
	public static double dmsToDegrees(int degrees, int arcminutes, double arcseconds) {
		return degrees + arcminutes/60.0 + arcseconds/3600.0;
	}
	
	public static double hmsToDegrees(int hours, int minutes, double seconds) {
		return hmsToDays(hours, minutes, seconds) * 360.0;
	}
	public static double hmsToDays(int hours, int minutes, double seconds) {
		return (seconds + (minutes*60) + (hours*3600)) / SECONDS_PER_DAY;
	}
	
	public static String degreesToDmsString(double deg) {
		StringBuilder s = new StringBuilder();
		if (deg > 0)
			s.append('+');
		else if (deg < 0)
			s.append('-');
		
		deg = Math.abs(deg);
		
		int deg_int = (int)deg;
		s.append(deg_int).append("d ");
		
		double arcminutes = (deg - deg_int) * 60;
		int arcminutes_int = (int)arcminutes;
		s.append(arcminutes_int).append("' ");
		
		double arcseconds = (arcminutes - arcminutes_int) * 60;
		s.append(arcseconds).append('"');
		return s.toString();
	}
	
	public static String degreesToHmsString(double deg) {
		StringBuilder s = new StringBuilder();
		if (deg > 0)
			s.append('+');
		else if (deg < 0)
			s.append('-');
		
		double hours = Math.abs(deg) / 15;
		int hours_int = (int)hours;
		s.append(hours_int).append("h ");
		
		double minutes = (hours - hours_int) * 60;
		int minutes_int = (int)minutes;
		s.append(minutes_int).append("m ");
		
		double seconds = (minutes - minutes_int) * 60;
		s.append(seconds).append('s');
		return s.toString();
	}
	
	public static double normalizeDegrees(double deg) {
		if (deg < 0)
			return deg % 360 + 360;
		else if (deg > 360)
			return deg % 360;
		else
			return deg;
	}
	
	// --- Functions ----------------------------------------------------------
	
	/**
	 * Implements the INT(x) function as defined in A.A.  This uses
	 * Math.floor() so that -7.8 rounds down to -8.
	 */
	public static int to_int(double value) {
		return (int)Math.floor(value);
	}
	public static long to_long(double value) {
		return (long)Math.floor(value);
	}
	
	public static interface UnivariateFunction {
		
		public double value(double x);
		
	}
	
	public static class PolynomialFunction implements UnivariateFunction {
		
		double [] coeffs;
		
		public PolynomialFunction(double [] coeffs) {
			this.coeffs = coeffs;
		}
		
		public double value(double x) {
			double sum = coeffs[0];
			if (coeffs.length > 1)
				sum += coeffs[1] * x;
			
			for (int i=2; i<coeffs.length; i++) {
				x = x * x;
				sum += coeffs[i] * x;
			}
			
			return sum;
		}
		
	}
	
}
