/** 
 * Project Name:meatball-core 
 * File Name:PhoneFormatCheckUtil.java 
 * Package Name:com.meatball.utils 
 * Date:2017年10月7日下午1:37:02 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */ 
package com.meatball.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {


	/**
	 * @Title: getYear 
	 * @Description: TODO(获取YYYY格式) 
	 * @return String    返回类型
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * @Title: getYear 
	 * @Description: TODO(获取YYYY格式) 
	 * @param date
	 * @return String    返回类型
	 */
	public static String getYear(Date date) {
		return formatDate(date, "yyyy");
	}

	/**
	 * @Title: getDay 
	 * @Description: TODO(获取YYYY-MM-DD格式) 
	 * @return String    返回类型
	 */
	public static String getDay() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * @Title: getDay 
	 * @Description: TODO(获取YYYY-MM-DD格式) 
	 * @param date
	 * @return String    返回类型
	 */
	public static String getDay(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * @Title: getDays 
	 * @Description: TODO(获取YYYYMMDD格式) 
	 * @return String    返回类型
	 */
	public static String getDays() {
		return formatDate(new Date(), "yyyyMMdd");
	}

	/**
	 * @Title: getDays 
	 * @Description: TODO(获取YYYYMMDD格式) 
	 * @param date
	 * @return String    返回类型
	 */
	public static String getDays(Date date) {
		return formatDate(date, "yyyyMMdd");
	}

	/**
	 * @Title: getTime 
	 * @Description: TODO(获取YYYY-MM-DD HH:mm:ss格式) 
	 * @return String    返回类型
	 */
	public static String getTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @Title: getMsTime 
	 * @Description: TODO(获取YYYY-MM-DD HH:mm:ss.SSS格式) 
	 * @return String    返回类型
	 */
	public static String getMsTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * @Title: getAllTime 
	 * @Description: TODO(获取YYYYMMDDHHmmss格式) 
	 * @return String    返回类型
	 */
	public static String getAllTime() {
		return formatDate(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * @Title: getTime 
	 * @Description: TODO(获取YYYY-MM-DD HH:mm:ss格式) 
	 * @param date
	 * @return String    返回类型
	 */
	public static String getTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		if (StringUtils.isNotBlank(pattern)) {
			formatDate = DateFormatUtils.format(date, pattern);
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * @Title: compareDate 
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false) 
	 * @param s
	 * @param e
	 * @return boolean    返回类型
	 */
	public static boolean compareDate(String s, String e) {
		if (parseDate(s) == null || parseDate(e) == null) {
			return false;
		}
		return parseDate(s).getTime() >= parseDate(e).getTime();
	}

	/**
	 * @Title: parseDate 
	 * @Description: TODO(格式化日期) 
	 * @param date
	 * @return Date    返回类型
	 */
	public static Date parseDate(String date) {
		return parse(date,"yyyy-MM-dd");
	}

	/**
	 * @Title: parseTime 
	 * @Description: TODO(格式化日期) 
	 * @param date
	 * @return Date    返回类型
	 */
	public static Date parseTime(String date) {
		return parse(date,"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @Title: parse 
	 * @Description: TODO(格式化日期) 
	 * @param date
	 * @param pattern
	 * @return Date    返回类型
	 */
	public static Date parse(String date, String pattern) {
		try {
			return DateUtils.parseDate(date,pattern);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title: format 
	 * @Description: TODO(格式化日期) 
	 * @param date
	 * @param pattern
	 * @return String    返回类型
	 */
	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * @Title: format 
	 * @Description: TODO(把日期转换为Timestamp) 
	 * @param date
	 * @return Timestamp    返回类型
	 */
	public static Timestamp format(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * @Title: isValidDate 
	 * @Description: TODO(校验日期是否合法) 
	 * @param s
	 * @return boolean    返回类型
	 */
	public static boolean isValidDate(String s) {
		return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
	}

	/**
	 * @Title: isValidDate 
	 * @Description: TODO(校验日期是否合法) 
	 * @param s
	 * @param pattern
	 * @return boolean    返回类型
	 */
	public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
					startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * @Title: getDaySub 
	 * @Description: TODO(功能描述：时间相减得到天数) 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long    返回类型
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * @Title: getAfterDayDate 
	 * @Description: TODO(得到n天之后的日期) 
	 * @param days
	 * @return String    返回类型
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * @Title: getAfterDayWeek 
	 * @Description: TODO(得到n天之后是周几) 
	 * @param days
	 * @return String    返回类型
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	 * @Title: main 
	 * @Description: TODO(格式化Oracle Date) 
	 * @param args
	 * @return void    返回类型
	 */
//	public static String buildDateValue(Object value){
//		if(Func.isOracle()){
//			return "to_date('"+ value +"','yyyy-mm-dd HH24:MI:SS')";
//		}else{
//			return Func.toStr(value);
//		}
//	}

	public static void main(String[] args) {
		System.out.println(getTime(new Date()));
		System.out.println(getAfterDayWeek("3"));
	}

}
