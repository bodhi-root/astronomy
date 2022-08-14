package com.futurestats.astronomy.algos.meeus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.futurestats.astronomy.algos.meeus.Structs.Nutation;

public class Chapter22Test {

	@Test
	public void test() {
		example22a();
	}
	
	public void example22a() {
		double jde = 2446895.5;
		double epsilon0 = Chapter22.meanObliquityOfEcliptic(jde);
		assertEquals(Utils.dmsToDegrees(23, 26, 27.407), epsilon0, 1e-6);
		
		Nutation nutation = Chapter22.nutationApprox(jde);
		System.out.println(nutation);
		//TODO: the rest of this example
	}
	
}
