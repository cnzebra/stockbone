package com.stockbone.web.components;

import java.io.Serializable;

import com.stockbone.components.stock.Exchange;

public class StockQueryParameter implements Serializable {

	private static final long serialVersionUID = -4305925035839580870L;

	/**
	 * 股票编码
	 */
	private String code;
	/**
	 * 交易所
	 */
	private Exchange exchange;
	/**
	 * 股票名称
	 */
	private String name;
	/**
	 * 上市时间
	 */
	private long goPublicTimeFrom;
	/**
	 * 上市时间
	 */
	private long goPublicTimeTo;
	/**
	 * 开始条数
	 */
	private int start = 0;
	/**
	 * 取多少条
	 */
	private int length = 10;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getGoPublicTimeFrom() {
		return goPublicTimeFrom;
	}

	public void setGoPublicTimeFrom(long goPublicTimeFrom) {
		this.goPublicTimeFrom = goPublicTimeFrom;
	}

	public long getGoPublicTimeTo() {
		return goPublicTimeTo;
	}

	public void setGoPublicTimeTo(long goPublicTimeTo) {
		this.goPublicTimeTo = goPublicTimeTo;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
