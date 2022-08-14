package com.futurestats.astronomy;

import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Degrees;

/**
 * Expresses a location on the Earth using latitude and longitude from
 * the Geographic Coordinate System.  East longitude is positive and
 * West is negative.
 * 
 * https://en.wikipedia.org/wiki/Geographic_coordinate_system
 * 
 * NOTE: The convention of East longitude being positive was adopted by
 * the International Astronomical Union in 1982.  Meeus notes (p. 93) that he
 * disagrees with this decision and will maintain the convention of West
 * longitude being positive.  Make sure to negate the angle before doing
 * any of Meeus's calculations.
 */
public class GeoLocation {
	
	//locations from: https://www.latlong.net/
	public static final GeoLocation GREENWICH = GeoLocation.fromDegrees(51.477928, -0.001545);
	public static final GeoLocation CINCINNATI = GeoLocation.fromDegrees(39.103700, -84.513610);
	
	Angle latitude;
	Angle longitude;
	
	public GeoLocation(Angle latitude, Angle longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public static GeoLocation fromDegrees(double latitude, double longitude) {
		return new GeoLocation(Degrees.of(latitude), Degrees.of(longitude));
	}
	
	public Angle getLatitude() {
		return latitude;
	}
	public Angle getLongitude() {
		return longitude;
	}
	
	public String toString() {
		return "(lat, long) = (" + latitude.toString() + ", " + longitude.toString() + ")";
	}
	
}
