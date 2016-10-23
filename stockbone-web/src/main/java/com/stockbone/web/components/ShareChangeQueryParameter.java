package com.stockbone.web.components;

import java.io.Serializable;

public class ShareChangeQueryParameter implements Serializable {

	private static final long serialVersionUID = 148786538095260969L;
	/**
	 * 股票编码
	 */
	private String code;
	/**
	 * 变更日期
	 */
	private long changeDate;

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

}
