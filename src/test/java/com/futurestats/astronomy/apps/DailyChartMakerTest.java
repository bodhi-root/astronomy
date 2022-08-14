package com.futurestats.astronomy.apps;

import com.futurestats.astronomy.GeoContext;

public class DailyChartMakerTest {

	public static void main(String [] args) {
		DailyChartMaker maker = new DailyChartMaker(GeoContext.CINCINNATI);
		maker.printChartForYear(2022, System.out);
	}
	
}
