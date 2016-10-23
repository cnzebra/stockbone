package com.stockbone.components.stock;

import java.io.Serializable;
import java.util.List;

/**
 * 股票
 */
public class Stock implements Serializable {

	private static final long serialVersionUID = -6646031836505372116L;

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
	private long goPublicTime;
	/**
	 * 股本变动
	 */
	private List<ShareChange> shareChanges;

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getGoPublicTime() {
		return goPublicTime;
	}

	public void setGoPublicTime(long goPublicTime) {
		this.goPublicTime = goPublicTime;
	}

	public List<ShareChange> getShareChanges() {
		return shareChanges;
	}

	public void setShareChanges(List<ShareChange> shareChanges) {
		this.shareChanges = shareChanges;
	}

}
