package org.rookie.test.date;

import java.util.Calendar;

import org.junit.Test;

/**
 * DateUtils:日期的工具类
 *
 * Author: 不二   
 *
 * Copyright @ 2019
 * 
 */
public class DateUtils {
	/**
	 * 上个月时间段
	 * 
	 * @return YYYYMMDD-YYYYMMDD
	 */
	public String lastMonth() {
		Calendar calendar = Calendar.getInstance();
		//设置为月初的第一天
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		//减去一天，日期就变成了上个月的最后一天
		calendar.add(Calendar.DATE, -1);
		//获取年份
		int year = calendar.get(Calendar.YEAR);
		//获取月份（月份从0开始，实际值要加1）
		int month = calendar.get(Calendar.MONTH) + 1;
		//日期字符串化
		String monthStr = (month < 10 ? "0" + month : "" + month);
		//拼接 buffer
		StringBuffer sb = new StringBuffer();
		//获取当月有多少天，都是>=28的，不用判断是否为个位数
		int dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		sb.append(year).append(monthStr).append("01-").append(year).append(monthStr).append(dayOfMonth);
		return sb.toString();
	}
	
	@Test
	public void testMethods() {
		System.out.println(lastMonth());
		String first = "first";
		String second = "second";
		swap(first, second);
		System.out.println(first);
	}
	
	public void swap(String a, String b) {
		String tmp = a;
		a = b;
		b = tmp;
	}
	
}
