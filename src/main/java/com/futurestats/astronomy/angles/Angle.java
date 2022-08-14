package com.futurestats.astronomy.angles;

public abstract class Angle {
	
	/**
	 * Converts an Angle object to this class.  As long as you can convert
	 * from degrees, radians, or hours, you can use this conversion to do 
	 * the rest easily.
	 */
	public abstract Angle cast(Angle angle);
	
	public abstract Angle normalize();
	
	public abstract Angle add(Angle angle);
		
	public abstract Angle subtract(Angle angle);
	
	
	public double sin() {
		return Math.sin(toRadians().value());
	}
	public double cos() {
		return Math.cos(toRadians().value());
	}
	public double tan() {
		return Math.tan(toRadians().value());
	}
	
	public static Angle asin(double x) {
		return Radians.of(Math.asin(x));
	}
	public static Angle acos(double x) {
		return Radians.of(Math.acos(x));
	}
	public static Angle atan(double x) {
		return Radians.of(Math.atan(x));
	}
	
	public abstract Degrees toDegrees();
	public abstract Radians toRadians();
	public abstract Hours toHours();
	
	/**
	 * Returns a string representing the angle in degrees, minutes,
	 * and seconds. Format: (+/-)#d #' #".
	 */
	public String toDmsString() {
		StringBuilder s = new StringBuilder();
		double deg = toDegrees().value();
		if (deg > 0)
			s.append("+ ");
		else if (deg < 0)
			s.append("- ");
		
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
	
	/**
	 * Returns a string representing the angle in hours, minutes,
	 * and seconds. Format: (+/-)#h #m #s).
	 */
	public String toHmsString() {
		StringBuilder s = new StringBuilder();
		double deg = toDegrees().value();
		if (deg > 0)
			s.append("+ ");
		else if (deg < 0)
			s.append("- ");
		
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
	
}
