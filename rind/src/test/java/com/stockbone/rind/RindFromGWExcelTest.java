package com.stockbone.rind;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Exchange;
import com.stockbone.components.stock.Stock;
import com.stockbone.rind.impl.RindFromGWExcel;
import com.stockbone.utils.date.DateUtils;

public class RindFromGWExcelTest {

	RindFromGWExcel rind = new RindFromGWExcel();

	@Test
	public void testRind() {
		Stock stock = new Stock();
		stock.setCode("600009");
		stock.setExchange(Exchange.SH);
		List<CandleData> list = rind.rind(stock, new File("src/test/resources/600009.XLS"));
		for (CandleData candleData : list) {
			System.out.println(DateUtils.format(new Date(candleData.getTime())) + ":" + candleData.getVolume() + ":" + candleData.getAmount());
		}
	}

}
