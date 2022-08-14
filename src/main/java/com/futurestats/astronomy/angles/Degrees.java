package com.futurestats.astronomy.angles;

public class Degrees extends Angle {
	
	private final double value;
	
	private Degrees(double value) {
		this.value = value;
	}
	
	public double value() {
		return value;
	}
	
	public static Degrees of(double value) {
		return new Degrees(value);
	}
	
	public Degrees normalize() {
		if (this.value < 0)
			return new Degrees(this.value % 360 + 360);
		else if (this.value > 360)
			return new Degrees(this.value % 360);
		else
			return this;
	}
	
	public Degrees add(Angle angle) {
		return new Degrees(this.value + angle.toDegrees().value());
	}
		
	public Degrees subtract(Angle angle) {
		return new Degrees(this.value - angle.toDegrees().value());
	}
	
	public Degrees toDegrees() {
		return this;
	}
	public Radians toRadians() {
		return Radians.of(this.value / 360 * Radians.PI2);
	}
	public Hours toHours() {
		return Hours.of(this.value / 360 * 24);
	}
	public Degrees cast(Angle angle) {
		return angle.toDegrees();
	}
	
	public String toString() {
		return String.valueOf(value) + " (degrees)";
	}
	
}
