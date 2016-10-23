package com.stockbone.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stockbone.utils.valid.Preconditions;

public class DateUtils {

	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String format(Date date, String format) {
		format = StringUtils.defaultString(format, DEFAULT_DATE_FORMAT);
		return new SimpleDateFormat(format).format(date);
	}

	public static String format(Date date) {
		return format(date, null);
	}
	
	public static String format(Long time, String format) {
		return format(new Date(time), format);
	}

	public static String format(Long time) {
		return format(new Date(time), null);
	}

	public static Date parse(String dateStr, String format) {
		Date date = null;
		format = StringUtils.defaultString(format, DEFAULT_DATE_FORMAT);
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

	public static Date parse(String date) {
		return parse(date, null);
	}

	public static long getLong(String dateStr, String format) {
		Date date = parse(dateStr, format);
		Preconditions.checkNotNull(date);
		return date.getTime();
	}

	public static long getLong(String dateStr) {
		Date date = parse(dateStr, null);
		Preconditions.checkNotNull(date);
		return date.getTime();
	}

}
