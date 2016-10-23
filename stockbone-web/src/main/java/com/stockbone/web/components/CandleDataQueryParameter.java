package com.stockbone.web.components;

import java.io.Serializable;

public class CandleDataQueryParameter implements Serializable {

	private static final long serialVersionUID = 4436075478489932909L;

	/**
	 * 股票编码
	 */
	private String code;
	/**
	 * 交易开始时间
	 */
	private long timeFrom;
	/**
	 * 交易结束时间
	 */
	private long timeTo;
	/**
	 * 交易间隔(分钟)
	 */
	private long tradePeriod;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(long timeFrom) {
		this.timeFrom = timeFrom;
	}

	public long getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(long timeTo) {
		this.timeTo = timeTo;
	}

	public long getTradePeriod() {
		return tradePeriod;
	}

	public void setTradePeriod(long tradePeriod) {
		this.tradePeriod = tradePeriod;
	}

}
