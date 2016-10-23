package com.stockbone.eagle;

import java.io.IOException;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stockbone.utils.date.DateUtils;
import com.stockbone.utils.http.HttpClient;

public class StockCalendar {
	
	private static Logger logger = LoggerFactory.getLogger(StockCalendar.class);
	
	public Date getLastTradeDate(){
		Date date = null;
		try {
			String url = "http://hq.sinajs.cn/list=sh000001";
			String data = HttpClient.getResource(url);
			String[] stockInfo = data.replaceAll("(\\w+=\")|\"", "").split(",");
			date = DateUtils.parse(stockInfo[30], "yyyy-MM-dd");
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

}
