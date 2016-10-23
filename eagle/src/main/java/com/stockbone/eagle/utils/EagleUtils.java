package com.stockbone.eagle.utils;

import org.apache.commons.lang3.StringUtils;

public class EagleUtils {

	/**
	 * 香港股票代码前不够5位补0
	 * 
	 * @param code
	 * @return
	 */
	public static String add0ForHk(String code) {
		if (StringUtils.isNotEmpty(code) && code.length() == 4) {
			code = "0" + code;
		}
		return code;
	}

}
