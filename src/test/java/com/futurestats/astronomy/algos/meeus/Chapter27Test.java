package com.futurestats.astronomy.algos.meeus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Chapter27Test {

	@Test
	public void test() {
		double jde = Chapter27.jdeForEvent(1962, Chapter27.Target.JUNE_SOLSTICE);
		assertEquals(2437837.3924527783, jde, 1e-6);
		
		//jde = Chapter27.jdeForEvent(1962, Chapter27.Target.MARCH_EQUINOX);
		//System.out.println(Chapter7.julianDateToCalendar(jde));
	}
	
}
