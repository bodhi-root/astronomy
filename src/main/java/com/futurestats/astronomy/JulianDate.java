package com.futurestats.astronomy;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.futurestats.astronomy.algos.meeus.Chapter27;
import com.futurestats.astronomy.algos.meeus.Chapter7;
import com.futurestats.astronomy.algos.meeus.Structs;

import java.time.ZoneOffset;

/**
 * A Julian Day (JD) is "a continuous count of days and fractions thereof from
 * the beginning of the year -4712." (Astronomical Algorithms). It is actually
 * defined such that 0 corresponds to -4712 Jan 1.5 (noon on January 1st of
 * the year -4712) in GMT/UTC time. Whereas a Julian Day is an integer value,
 * a JulianDate allows a fractional value indicating the ellapsed portion of
 * the day.
 * 
 * Converting from Instants (or ZonedDateTime) objects to JulianDates is 
 * pretty straight-forward since we can just calculate the time between two 
 * Instants (one where the JD is known) and apply that difference to the JD
 * of our reference point.  When doing this we assume that days have 86,400
 * seconds.  This may cause small errors (less than a minute) due to leap
 * seconds.  As long as the dates in question occur after 1852-10-15 we do
 * not need to worry about the conversion from the Julian to Gregorian calendar
 * that occurred prior to this.  The reference date we use is in fact 1852-10-15
 * with JD 2397776.5.
 * 
 * The book "Astronomical Algorithms" specifies the algorithm using the concept
 * of an "astronomical date" where the day 1852-10-04 is immediately followed
 * by 1852-10-15 and the Julian calendar is used for all days prior to this
 * correction.  We have implemented this algorithm faithfully when using the
 * methods that take an AstronomicalDate object or that pass in year, month,
 * and day as separate values.  These dates will be identical to current dates
 * in the GregorianCalendar (after 1852-10-15) but not before.  Java's concept
 * of ZonedDate and LocalDate use the ISO-8601 standard which does not use 
 * the same conventions as "astronomical dates."
 * 
 * Good resources:
 * 
 * * Julian Date calculator: http://www.stevegs.com/utils/jd_calc/
 * * Julian Date blog (with code): https://squarewidget.com/julian-day/
 * 
 */
public class JulianDate {
	
	public static final JulianDate J2000 = JulianDate.of(2451545.0);
	
	private static final Instant GREGORIAN_START_INSTANT = ZonedDateTime.of(
			    LocalDateTime.of(1852, 10, 15, 0, 0, 0), ZoneOffset.UTC
			).toInstant();
	
	private static final double GREGORIAN_START_JD = 2397776.5;
	
	private final double value;
	
	private JulianDate(double value) {
		this.value = value;
	}
	
	public double value() {
		return this.value;
	}
	
	/**
	 * Creates a JulianDate from its numerical value (the number of days since
	 * noon (GMT/UTC) on Jan 1. -4712.
	 */
	public static JulianDate of(double value) {
		return new JulianDate(value);
	}
	
	/**
	 * Returns the JulianDate for the given AstronomicalDate.
	 */
	public static JulianDate from(AstronomicalDate date) {
		double jd = Chapter7.julianDateFor(
				date.getYear(), 
				date.getMonthValue(), 
				date.getDayOfMonth());
		
		return new JulianDate(jd);
	}
	
	/**
	 * Returns the JulianDate for the given date.
	 * NOTE: dates are interpreted according to the rules of AstronomicalDate.
	 */
	public static JulianDate from(int year, int month, double day) {
		return from(AstronomicalDate.of(year, month, day));
	}
	
	/**
	 * Returns the JulianDate for the given date.
	 * NOTE: dates are interpreted according to the rules of AstronomicalDate.
	 */
	public static JulianDate from(int year, int month, int day) {
		return from(AstronomicalDate.of(year, month, day));
	}
	
	/**
	 * Converts the java.time.Instant to a JulianDate.  This is done by 
	 * finding the number of days and seconds elapsed between the given 
	 * instant and the start of the Gregorian calendar (1852-10-15,
	 * corresponding with JD 2397776.5).  This calculation assumes that 1
	 * Julian Day is a constant 86,400 seconds.  
	 */
	public static JulianDate from(Instant instant) {
		long seconds = Duration.between(GREGORIAN_START_INSTANT, instant).getSeconds();
		long days = seconds / 86400;
		double fraction = (seconds - (days * 86400)) / 86400.0;
		return new JulianDate(days + GREGORIAN_START_JD + fraction);
	}
	
	/**
	 * Converts the ZonedLocalDate to a JulianDate.  
	 */
	public static JulianDate from(ZonedDateTime date) {
		return from(date.toInstant());
	}
	
	/**
	 * Returns the JulianDate for the current local date.
	 */
	public static JulianDate now() {
		return from(Instant.now());
	}
	
	/**
	 * Converts the JulianDate to an astronomical one.
	 */
	public AstronomicalDate toAstronomicalDate() {
		Structs.AstronomicalDate date = Chapter7.julianDateToCalendar(this.value);
		return AstronomicalDate.of(date.year, date.month, date.day);
	}
	
	/**
	 * Converts the JulianDate to a LocalDate.
	 */
	public Instant toInstant() {
		double seconds = (this.value - GREGORIAN_START_JD) * 86400;
		return GREGORIAN_START_INSTANT.plusSeconds((long)seconds);
	}
	
	public ZonedDateTime toDateTime(ZoneId zone) {
		return ZonedDateTime.ofInstant(toInstant(), zone);
	}
	
	public ZonedDateTime toDateTime() {
		return toDateTime(ZoneId.systemDefault());
	}
	
	public static JulianDate forMarchEquinox(int year) {
		double jd = Chapter27.jdeForEvent(year, Chapter27.Target.MARCH_EQUINOX);
		return JulianDate.of(jd);
	}
	public static JulianDate forJuneSolstice(int year) {
		double jd = Chapter27.jdeForEvent(year, Chapter27.Target.JUNE_SOLSTICE);
		return JulianDate.of(jd);
	}
	public static JulianDate forSeptemberEquinox(int year) {
		double jd = Chapter27.jdeForEvent(year, Chapter27.Target.SEPT_EQUINOX);
		return JulianDate.of(jd);
	}
	public static JulianDate forDecemberSolstice(int year) {
		double jd = Chapter27.jdeForEvent(year, Chapter27.Target.DEC_SOLSTICE);
		return JulianDate.of(jd);
	}
	
	public String toString() {
		return "JD " + String.valueOf(this.value);
	}
	
}
