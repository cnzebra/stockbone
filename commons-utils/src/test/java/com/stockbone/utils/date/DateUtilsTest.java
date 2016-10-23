package com.stockbone.utils.date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testParse() {
		System.out.println(DateUtils.parse("1998-02-18 00:00:00").getTime());
	}

	@Test
	public void testGetLong() {
		System.out.println(DateUtils.getLong("1998-02-18 00:00:00"));
	}
	
	@Test
	public void testFormatLong(){
		System.out.println(DateUtils.format(1424966400000L));
	}

}
