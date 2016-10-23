package com.stockbone.eagle.talon;

import java.util.Date;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Stock;

public interface CandleDataTalon {

	/**
	 * 从http接口抓取股票数据
	 * 
	 * @param url
	 * @param code股票代码
	 * @param date日期
	 * @return
	 */
	public CandleData fetch(Stock stock, Date date);

	/**
	 * 从页面接口抓取股票数据
	 * 
	 * @param url
	 * @param code股票代码
	 * @param date日期
	 * @return
	 */
	public CandleData fetchHTML(Stock stock, Date date);

}
