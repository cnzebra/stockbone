package com.stockbone.eagle.talon.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Exchange;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.talon.CandleDataTalon;
import com.stockbone.eagle.utils.EagleUtils;
import com.stockbone.utils.http.HttpClient;
import com.stockbone.utils.random.RandomUtils;

public class CandleDataTalonFromSina implements CandleDataTalon {

	private static String URI = "http://hq.sinajs.cn/list=";

	private static Logger logger = LoggerFactory.getLogger(CandleDataTalonFromSina.class);

	/**
	 * 从http接口抓取股票数据
	 * 
	 * @param url
	 * @param code股票代码
	 * @param date日期
	 * @return
	 */
	public CandleData fetch(Stock stock, Date date) {
		CandleData candleData = null;
		try {
			String data = HttpClient.getResource(URI + StringUtils.lowerCase(stock.getExchange().toString()) + EagleUtils.add0ForHk(stock.getCode()));
			String[] stockInfo = data.replaceAll("(\\w+=\")|\"", "").split(",");
			if (stockInfo.length < 8) {
				logger.error("Sina:" + stock.getCode() + "未抓取到数据,请检查参数");
				return null;
			}
			candleData = new CandleData();
			candleData.setId(RandomUtils.randomUUID());
			candleData.setStock(stock);
			candleData.setTradePeriod(60 * 24);
			if (stock.getExchange() == Exchange.HK) {// 香港股票
				candleData.setOpen(Float.valueOf(stockInfo[2]));
				candleData.setHigh(Float.valueOf(stockInfo[4]));
				candleData.setLow(Float.valueOf(stockInfo[5]));
				candleData.setClose(Float.valueOf(stockInfo[6]));
				candleData.setVolume(Long.valueOf(stockInfo[12]));
				candleData.setTime(date.getTime());
			} else {// 深证、上证
				candleData.setOpen(Float.valueOf(stockInfo[1]));
				candleData.setClose(Float.valueOf(stockInfo[3]));
				candleData.setHigh(Float.valueOf(stockInfo[4]));
				candleData.setLow(Float.valueOf(stockInfo[5]));
				candleData.setVolume(Long.valueOf(stockInfo[8]));
				candleData.setAmount(Float.valueOf(stockInfo[9]));
				candleData.setTime(date.getTime());
			}
		} catch (ClientProtocolException e) {
			logger.error("Sina:" + stock.getCode() + "异常");
		} catch (IOException e) {
			logger.error("Sina:" + stock.getCode() + "异常");
		}
		return candleData;
	}

	/**
	 * 从页面接口抓取股票数据
	 * 
	 * @param url
	 * @param code股票代码
	 * @param date日期
	 * @return
	 */
	public CandleData fetchHTML(Stock stock, Date date) {
		logger.error("StockFetchFromTencent.fetchHTML空实现");
		return null;
	}

}
