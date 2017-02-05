package com.jrzmq.core.utils;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class DateUtil extends DateUtils {

	/**
	 * 获取10 mysql位时间戳 当前时间
	 * @return
	 */
	public static int getUnixTimeStamp() {
		return getUnixTimeStamp(new Date());
	}
	
	/**
	 * 获取10 mysql位时间戳 指定时间
	 * @return
	 */
	public static int getUnixTimeStamp(Date date) {
		String time = String.valueOf(date.getTime());
		return Integer.parseInt(time.substring(0, 10));
	}
	
}
