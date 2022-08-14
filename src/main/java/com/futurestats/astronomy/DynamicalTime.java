package com.futurestats.astronomy;

import com.futurestats.astronomy.algos.meeus.Chapter10;

public class DynamicalTime {

	/**
	 * Estimates the difference between Dynamic Time (TD) and Universal
	 * Time (UT) based on the year.  The result is in seconds.  You can convert
	 * between these using: TD = UT + offset
	 */
	public static double offsetForYear(int year) {
		return Chapter10.estimateDynamicTimeOffsetForYear(year);
	}
		
	
}
