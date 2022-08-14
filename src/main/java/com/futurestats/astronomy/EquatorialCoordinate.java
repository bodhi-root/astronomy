package com.futurestats.astronomy;

import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Degrees;

import com.futurestats.astronomy.algos.meeus.Chapter13;
import com.futurestats.astronomy.algos.meeus.Chapter21;
import com.futurestats.astronomy.algos.meeus.Structs;

public class EquatorialCoordinate {

	Angle rightAscension;
	Angle declination;
	
	public EquatorialCoordinate(Angle ra, Angle dec) {
		this.rightAscension = ra;
		this.declination = dec;
	}
	
	public static EquatorialCoordinate fromDegrees(double ra, double dec) {
		return new EquatorialCoordinate(Degrees.of(ra), Degrees.of(dec));
	}
	
	public Angle getRightAscension() {
		return rightAscension;
	}
	public Angle getDeclination() {
		return declination;
	}
	
	/**
	 * Converts the coordinates to their Ecliptical representation.
	 * 'epsilon' provides the obliquity of the ecliptic.
	 */
	public EclipticalCoordinate toEclipticalCoordinate(Angle epsilon) {
		Structs.EquatorialCoordinate x = new Structs.EquatorialCoordinate(
				rightAscension.toDegrees().value(),
				declination.toDegrees().value());
		
		Structs.EclipticalCoordinate y = Chapter13.equatorialToEcliptic(
				x, epsilon.toDegrees().value());
		
		return EclipticalCoordinate.fromDegrees(y.longitude, y.latitude);
	}
	
	/**
	 * Adjusts the coordinates to account for precession.  jd0 indicates the
	 * JulianDate corresponding with the coordinates' current date and 'jd'
	 * indicates the target date.
	 */
	public EquatorialCoordinate adjustForPrecession(JulianDate jd0, JulianDate jd) {
		Structs.EquatorialCoordinate x = new Structs.EquatorialCoordinate(
				rightAscension.toDegrees().value(),
				declination.toDegrees().value());
		
		Structs.EquatorialCoordinate y = Chapter21.adjust(x, jd0.value(), jd.value());
		return fromDegrees(y.rightAscension, y.declination);
	}
	
	
	public String toString() {
		return "(ra, dec) = (" + rightAscension.toHmsString() + ", " + declination.toDmsString() + ")";
	}
	
}
