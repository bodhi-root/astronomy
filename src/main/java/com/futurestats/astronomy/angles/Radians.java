package com.futurestats.astronomy.angles;

public class Radians extends Angle {

	static final double PI2 = Math.PI * 2;
	
	private final double value;
	
	private Radians(double value) {
		this.value = value;
	}
	
	public double value() {
		return value;
	}
	
	public static Radians of(double value) {
		return new Radians(value);
	}
	
	public Radians normalize() {
		if (this.value < 0) {
			double v2 = value;
			while (v2 < 0)
				v2 += PI2;
			return new Radians(v2);
		}
		else if (this.value > PI2) {
			double v2 = value;
			while (v2 > PI2)
				v2 -= PI2;
			return new Radians(v2);
		}
		else {
			return this;
		}
		
	}
	
	public Radians add(Angle angle) {
		return new Radians(this.value + angle.toRadians().value());
	}
		
	public Radians subtract(Angle angle) {
		return new Radians(this.value - angle.toRadians().value());
	}
	
	public Radians toRadians() {
		return this;
	}
	public Degrees toDegrees() {
		return Degrees.of(this.value / PI2 * 360);
	}
	public Hours toHours() {
		return Hours.of(this.value / PI2 * 24);
	}
	public Radians cast(Angle angle) {
		return angle.toRadians();
	}
	
	public String toString() {
		return String.valueOf(value) + " (radians)";
	}
	
}
