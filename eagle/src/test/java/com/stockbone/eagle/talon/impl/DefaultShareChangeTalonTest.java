package com.stockbone.eagle.talon.impl;

import java.util.List;

import org.junit.Test;

import com.stockbone.components.stock.Exchange;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.talon.ShareChangeTalon;

public class DefaultShareChangeTalonTest {

	ShareChangeTalon shareChangeTalon = new DefaultShareChangeTalon();
	
	@Test
	public void testFetchShareChange() throws Exception {
		Stock stock = new Stock();
		stock.setCode("600009");
		stock.setExchange(Exchange.SH);
		List<ShareChange> changes = shareChangeTalon.fetchShareChange(stock); 
		for (ShareChange shareChange : changes) {
			System.out.println(shareChange);
		}
	}

}
