package com.futurestats.astronomy.algos.meeus;

/**
 * Trigonometric functions defined in degrees.
 */
public class TrigDegrees {

	public static final double DEG_TO_RAD = Math.PI/180;
	
	public static double normalize(double deg) {
		if (deg < 0)
			return deg % 360 + 360;
		else if (deg > 360)
			return deg % 360;
		else
			return deg;
	}
	
	public static double sin(double deg) {
		return Math.sin(deg*DEG_TO_RAD);
	}
	public static double cos(double deg) {
		return Math.cos(deg*DEG_TO_RAD);
	}
	public static double tan(double deg) {
		return Math.tan(deg*DEG_TO_RAD);
	}
	
	public static double asin(double x) {
		return Math.asin(x)/DEG_TO_RAD;
	}
	public static double acos(double x) {
		return Math.acos(x)/DEG_TO_RAD;
	}
	public static double atan(double x) {
		return Math.atan(x)/DEG_TO_RAD;
	}
	
	public static double atan2(double y, double x) {
		return Math.atan2(y, x)/DEG_TO_RAD;
	}
	
}
