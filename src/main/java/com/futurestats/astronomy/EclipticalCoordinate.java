package com.futurestats.astronomy;

import com.futurestats.astronomy.algos.meeus.Chapter13;
import com.futurestats.astronomy.algos.meeus.Structs;
import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Degrees;

/**
 * Celestial coordinates given with respect to the ecliptic (the plane in
 * which the Earth moves around the sun).   
 * 
 * https://en.wikipedia.org/wiki/Equatorial_coordinate_system
 */
public class EclipticalCoordinate {

	Angle longitude;
	Angle latitude;
	
	public EclipticalCoordinate(Angle longitude, Angle latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Angle getLongitude() {
		return longitude;
	}
	public Angle getLatitude() {
		return latitude;
	}
	
	public static EclipticalCoordinate fromDegrees(double longitude, double latitude) {
		return new EclipticalCoordinate(Degrees.of(longitude), Degrees.of(latitude));
	}
	
	/**
	 * Converts the coordinates to their Equatorial representation.
	 * 'epsilon' provides the obliquity of the ecliptic.
	 */
	public EquatorialCoordinate toEquatorialCoordinate(Angle epsilon) {
		Structs.EclipticalCoordinate x = new Structs.EclipticalCoordinate(
				longitude.toDegrees().value(),
				latitude.toDegrees().value());
		
		Structs.EquatorialCoordinate y = Chapter13.eclipticToEquatorial(
				x, epsilon.toDegrees().value());
		
		return EquatorialCoordinate.fromDegrees(y.rightAscension, y.declination);
	}
	
	
	public String toString() {
		return "(lon, lat) = (" + longitude.toDmsString() + ", " + latitude.toDmsString() + ")";
	}
	
}
