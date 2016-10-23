package com.stockbone.eagle.talon.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.talon.CandleDataTalon;
import com.stockbone.eagle.utils.EagleUtils;
import com.stockbone.utils.http.HttpClient;
import com.stockbone.utils.random.RandomUtils;

public class CandleDataTalonFromGW implements CandleDataTalon {

	private static String URI = "http://cj.gw.com.cn/stockHq.php?code=";
	
	private static Logger logger = LoggerFactory.getLogger(CandleDataTalonFromGW.class);

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
			String exchange = StringUtils.upperCase(stock.getExchange().toString());
			String data = HttpClient.getResource(URI + exchange + codeStr + ";&_=" + System.currentTimeMillis());
			JSONArray array = JSON.parseArray(data);
			if (array == null || array.size() == 0) {
				logger.error("GW:" + stock.getCode() + "未抓取到数据,请检查参数");
				return null;
			}
			for (int i = 0; i < array.size(); i++) {
				JSONObject stockJson =  array.getJSONObject(i);
				candleData = new CandleData();
				candleData.setId(RandomUtils.randomUUID());
				candleData.setOpen(stockJson.getFloat("op"));
				candleData.setClose(stockJson.getFloat("lp"));
				candleData.setHigh(stockJson.getFloat("priceMax"));
				candleData.setLow(stockJson.getFloat("priceMin"));
				candleData.setVolume(stockJson.getLong("vol") * 100);
				candleData.setAmount(stockJson.getFloat("volp") * 10000);
				candleData.setTime(date.getTime());
				candleData.setStock(stock);
				candleData.setTradePeriod(60 * 24);
			}
		} catch (ClientProtocolException e) {
			logger.error("GW:" + stock.getCode() + "异常");
		} catch (IOException e) {
			logger.error("GW:" + stock.getCode() + "异常");
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
