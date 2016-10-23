package com.stockbone.components.stock;

import java.io.Serializable;

/**
 * 分笔交易数据
 */
public class DealData implements Serializable {

	private static final long serialVersionUID = 4672549774685240991L;
	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 交易时间
	 */
	private long dealTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getDealTime() {
		return dealTime;
	}

	public void setDealTime(long dealTime) {
		this.dealTime = dealTime;
	}

}
