package com.futurestats.astronomy.angles;

import java.time.LocalTime;

public class Hours extends Angle {

	public static final long NANOS_PER_HOUR = 3600000000000L;
	
	private final double value;
	
	private Hours(double value) {
		this.value = value;
	}
	
	public double value() {
		return value;
	}
	
	public static Hours of(double value) {
		return new Hours(value);
	}
	public static Hours of(int hours, int minutes, int seconds, double nanos) {
		return of(hours + 
				  minutes / 60.0 + 
				  (seconds + nanos) / 3600.0);
	}
	public static Hours of(int hours, int minutes, int seconds) {
		return of(hours, minutes, seconds, 0.0);
	}
	
	public Hours normalize() {
		if (this.value < 0)
			return new Hours(this.value % 24 + 24);
		else if (this.value > 24)
			return new Hours(this.value % 24);
		else
			return this;
	}
	
	public Hours add(Angle angle) {
		return new Hours(this.value + angle.toHours().value());
	}
		
	public Hours subtract(Angle angle) {
		return new Hours(this.value - angle.toHours().value());
	}
	
	public Hours toHours() {
		return this;
	}
	public Degrees toDegrees() {
		return Degrees.of(this.value / 24 * 360);
	}
	public Radians toRadians() {
		return Radians.of(this.value / 24 * Radians.PI2);
	}
	public Hours cast(Angle angle) {
		return angle.toHours();
	}
	
	public double toMinutes() {
		return this.value * 60;
	}
	public double toSeconds() {
		return this.value * 3600;
	}
	public double toNanos() {
		return this.value * 3600000000000.0;
	}
	
	public LocalTime toLocalTime() {
		return LocalTime.ofNanoOfDay((long)toNanos());
	}
	public static Hours from(LocalTime time) {
		return new Hours(time.toNanoOfDay() / 3600000000000.0);
	}
	
	public String toString() {
		//return String.valueOf(value) + " (hours)";
		int ihour = (int)Math.floor(value);
		double minutes = (this.value - ihour) * 60;
		int iminutes = (int)Math.floor(minutes);
		double seconds = (minutes - iminutes) * 60;
		
		StringBuilder s = new StringBuilder();
		s.append(ihour).append(" h ")
		 .append(iminutes).append(" m ")
		 .append(seconds).append(" s");
		return s.toString();
	}
	
}
