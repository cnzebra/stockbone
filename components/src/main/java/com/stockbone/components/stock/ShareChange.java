package com.stockbone.components.stock;

import java.io.Serializable;

/**
 * 股本变动
 */
public class ShareChange implements Serializable {

	private static final long serialVersionUID = -4885153710524231771L;
	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 股票编码
	 */
	private String code;
	/**
	 * 变更日期
	 */
	private long changeDate;
	/**
	 * 变更原因
	 */
	private String changeReason;
	/**
	 * 流通股本
	 */
	private long AShares;
	/**
	 * 非流通股本
	 */
	private long BShares;
	/**
	 * 总股本
	 */
	private long totalShares;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(long changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public long getAShares() {
		return AShares;
	}

	public void setAShares(long aShares) {
		AShares = aShares;
	}

	public long getBShares() {
		return BShares;
	}

	public void setBShares(long bShares) {
		BShares = bShares;
	}

	public long getTotalShares() {
		return totalShares;
	}

	public void setTotalShares(long totalShares) {
		this.totalShares = totalShares;
	}

	@Override
	public String toString() {
		return "ShareChange [id=" + id + ", code=" + code + ", changeDate=" + changeDate + ", changeReason=" + changeReason + ", AShares=" + AShares + ", BShares=" + BShares + ", totalShares=" + totalShares + "]";
	}

}
