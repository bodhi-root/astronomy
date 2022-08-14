package com.futurestats.astronomy;

import java.time.Month;

import com.futurestats.astronomy.algos.meeus.Chapter7;

/**
 * Implements the "Astronomical Date" concept from the book "Astronomical
 * Algorithms".  This specifies a date as a year, month, and day with two
 * differences with respect to Java's built-in LocalDate class:
 * 
 * 1. dayOfMonth can be specified as a double (allowing fractions instead
 *    of just integers)
 * 2. The Julian calendar is used on dates prior to and including 1852-10-04
 *
 * The class definition and internals are intended to be as compatible with
 * Java's LocalDate object as possible.  We do not include a time zone in the
 * astronomical date.  Instead, we assume that GMT (+0:00) is used.
 *
 * NOTE: The date -1000 Feb 29 is a problematic one for LocalDate.  Trying
 * to instantiate a LocalDate with this value throws an error saying that the
 * year -1000 is not a leap year.  This is because LocalDate implements the
 * ISO-8601 standard where our modern rules for leap years are applied to all
 * dates.  The modern rules do not allow all years divisible by 4 to be leap
 * years.  Some (such as -1000) are intentionally excluded.  "Astronomical
 * Algorithms" likes to use this date as a test to see how it is handled.
 * The AstronomicalDate class below can handle it without problems.
 * 
 * We intentionally do not provide a conversion between AstronomicalDate and
 * LocalDate.  The best way to do this is to convert the date first to
 * a JulianDate and then convert to the target type.  If you want to do this
 * you can, but in general we'd like to see calculations support both
 * LocalDate or AstronomicalDate and not require a user to convert between
 * these two directly.
 */
public class AstronomicalDate {

	private final int year;
	private final short month;
	private final double day;
	
	private AstronomicalDate(int year, int month, double day) {
		this.year = year;
		this.month = (short)month;
		this.day = day;
	}
	
	public int getYear() {
		return year;
	}
	public Month getMonth() {
		return Month.of(month);
	}
	public int getMonthValue() {
		return month;
	}
	public double getDayOfMonth() {
		return day;
	}
	
	/**
	 * The Julian calendar is used for all dates up to and including
	 * Thursday, 4 October 1582.
	 */
	public boolean isJulian() {
		return Chapter7.isJulianCalendar(year, month, day);
	}
	
	public static AstronomicalDate of(int year, int month, double dayOfMonth) {
		return new AstronomicalDate(year, month, dayOfMonth);
	}
	
	public String toString() {
		return String.format("%04d-%02d-%02f", year, month, day);
	}
	
}
