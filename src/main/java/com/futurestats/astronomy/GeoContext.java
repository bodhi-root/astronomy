package com.futurestats.astronomy;

import java.time.ZoneId;

import com.futurestats.astronomy.angles.Angle;

/**
 * Provides the context for calculations that depend on one's location
 * on the Earth.  This includes the location (latitude, longitude) as 
 * well as the time zone for that location.
 */
public class GeoContext {

	public static final GeoContext GREENWICH_GMT = new GeoContext(
			GeoLocation.GREENWICH, ZoneId.of("GMT"));
	
	public static final GeoContext CINCINNATI = new GeoContext(
			GeoLocation.CINCINNATI, ZoneId.of("America/New_York"));
	
	GeoLocation location;
	ZoneId zoneId;
	
	public GeoContext(GeoLocation location, ZoneId zoneId) {
		this.location = location;
		this.zoneId = zoneId;
	}
	public GeoContext(Angle latitude, Angle longitude, ZoneId zoneId) {
		this(new GeoLocation(latitude, longitude), zoneId);
	}
	
	public GeoLocation getLocation() {
		return location;
	}
	public ZoneId getZoneId() {
		return zoneId;
	}
	
	public Angle getLatitude() {
		return location.getLatitude();
	}
	public Angle getLongitude() {
		return location.getLongitude();
	}
	
	public String toString() {
		return location.toString() + ", " + zoneId.toString();
	}
	
}
