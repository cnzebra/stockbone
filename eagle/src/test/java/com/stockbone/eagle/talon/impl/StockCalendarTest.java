package com.stockbone.eagle.talon.impl;

import java.util.Date;

import org.junit.Test;

import com.stockbone.eagle.StockCalendar;
import com.stockbone.utils.date.DateUtils;

public class StockCalendarTest {
	
	@Test
	public void testGetLastTradeDate(){
		StockCalendar c = new StockCalendar();
		Date date = c.getLastTradeDate();
		System.out.println(DateUtils.format(date));
	}

}
