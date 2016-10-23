package com.stockbone.eagle.talon.impl;

import java.util.Date;

import org.junit.Test;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Exchange;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.StockCalendar;
import com.stockbone.eagle.talon.CandleDataTalon;

public class CandleDataTalonTest {
	
	private Date lastTradeDate = new StockCalendar().getLastTradeDate();

	@Test
	public void testFetchFromHexun() {
		CandleDataTalon talon = new CandleDataTalonFromHexun();
		Stock stock = new Stock();
		stock.setCode("0009");
		stock.setExchange(Exchange.HK);
		CandleData cd = talon.fetch(stock, lastTradeDate);
		System.out.println(cd);
		
		Stock stock1 = new Stock();
		stock1.setCode("000629");
		stock1.setExchange(Exchange.SZ);
		CandleData cd1 = talon.fetch(stock1, lastTradeDate);
		System.out.println(cd1);
		
		Stock stock2 = new Stock();
		stock2.setCode("600629");
		stock2.setExchange(Exchange.SH);
		CandleData cd2 = talon.fetch(stock2, lastTradeDate);
		System.out.println(cd2);
	}

	@Test
	public void testFetchFromSina() {
		CandleDataTalon talon = new CandleDataTalonFromSina();
		Stock stock = new Stock();
		stock.setCode("0009");
		stock.setExchange(Exchange.HK);
		CandleData cd = talon.fetch(stock, lastTradeDate);
		System.out.println(cd);
		
		Stock stock1 = new Stock();
		stock1.setCode("000629");
		stock1.setExchange(Exchange.SZ);
		CandleData cd1 = talon.fetch(stock1, lastTradeDate);
		System.out.println(cd1);
		
		Stock stock2 = new Stock();
		stock2.setCode("600629");
		stock2.setExchange(Exchange.SH);
		CandleData cd2 = talon.fetch(stock2, lastTradeDate);
		System.out.println(cd2);
	}

	@Test
	public void testFetchFromTencent() {
		CandleDataTalon talon = new CandleDataTalonFromTencent();
		Stock stock = new Stock();
		stock.setCode("0009");
		stock.setExchange(Exchange.HK);
		CandleData cd = talon.fetch(stock, lastTradeDate);
		System.out.println(cd);
		
		Stock stock1 = new Stock();
		stock1.setCode("000629");
		stock1.setExchange(Exchange.SZ);
		CandleData cd1 = talon.fetch(stock1, lastTradeDate);
		System.out.println(cd1);
		
		Stock stock2 = new Stock();
		stock2.setCode("600629");
		stock2.setExchange(Exchange.SH);
		CandleData cd2 = talon.fetch(stock2, lastTradeDate);
		System.out.println(cd2);
	}
	
	@Test
	public void testFetchFromGW() {
		CandleDataTalon talon = new CandleDataTalonFromGW();
		
		Stock stock1 = new Stock();
		stock1.setCode("000629");
		stock1.setExchange(Exchange.SZ);
		CandleData cd1 = talon.fetch(stock1, lastTradeDate);
		System.out.println(cd1);
		
		Stock stock2 = new Stock();
		stock2.setCode("600629");
		stock2.setExchange(Exchange.SH);
		CandleData cd2 = talon.fetch(stock2, lastTradeDate);
		System.out.println(cd2);
	}

}
