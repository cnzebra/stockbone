package com.stockbone.eagle.talon.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.talon.CandleDataTalon;
import com.stockbone.eagle.utils.EagleUtils;
import com.stockbone.utils.http.HttpClient;
import com.stockbone.utils.random.RandomUtils;

public class CandleDataTalonFromHexun implements CandleDataTalon {

	private static String URI = "http://bdcjhq.hexun.com/quote?s2=";

	private static Logger logger = LoggerFactory.getLogger(CandleDataTalonFromHexun.class);

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
			String codeStr = EagleUtils.add0ForHk(stock.getCode());
			String exchange = StringUtils.lowerCase(stock.getExchange().toString());
			String data = HttpClient.getResource(URI + codeStr + "." + exchange);
			data = StringUtils.substringsBetween(data, "<script>try{parent.bdcallback(", ")}catch(e){}</script></html>")[0];
			JSONObject json = JSON.parseObject(data);
			JSONObject stockJson = json.getJSONObject(codeStr + "." + exchange);
			if (stockJson == null) {
				logger.error("Hexun:" + stock.getCode() + "未抓取到数据,请检查参数");
				return null;
			}
			candleData = new CandleData();
			candleData.setId(RandomUtils.randomUUID());
			candleData.setOpen(stockJson.getFloat("op"));
			candleData.setClose(stockJson.getFloat("la"));
			candleData.setHigh(stockJson.getFloat("hi"));
			candleData.setLow(stockJson.getFloat("lo"));
			candleData.setVolume(stockJson.getLong("vo") * 100);
			candleData.setAmount(stockJson.getFloat("tu") * 10000);
			candleData.setTime(date.getTime());
			candleData.setStock(stock);
			candleData.setTradePeriod(60 * 24);
		} catch (ClientProtocolException e) {
			logger.error("Hexun:" + stock.getCode() + "异常");
		} catch (IOException e) {
			logger.error("Hexun:" + stock.getCode() + "异常");
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
