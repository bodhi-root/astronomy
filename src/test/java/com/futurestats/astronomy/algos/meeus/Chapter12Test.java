package com.futurestats.astronomy.algos.meeus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.futurestats.astronomy.algos.meeus.Chapter12;
import com.futurestats.astronomy.algos.meeus.Chapter7;
import com.futurestats.astronomy.algos.meeus.Utils;

public class Chapter12Test {

	@Test
	public void test() {
		double jd = Chapter7.julianDateFor(1987, 4, 10);
		jd += Utils.hmsToDays(19, 21, 0);
		double gmst = Chapter12.gmst(jd);
		//System.out.println(gmst);
		assertEquals(128.7378734, gmst, 1e-6);
	}
	
}
