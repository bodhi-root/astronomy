package com.futurestats.astronomy;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.futurestats.astronomy.angles.Angle;

public class EclipticTest {

	@Test
	public void test() {
		Angle j2000 = Ecliptic.getMeanObliquity(JulianDate.J2000);
		assertEquals(Ecliptic.EPSILON_J2000.toDegrees().value(), j2000.toDegrees().value(), 1e-6);
	}
	
}
