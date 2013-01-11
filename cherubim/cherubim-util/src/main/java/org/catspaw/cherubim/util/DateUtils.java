package org.catspaw.cherubim.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类 提供日期时间比较、日期时间计算等功能
 * @author 孙宁振 sunningzhen@gmail.com
 * @version 1.0 2011-4-17
 */
public final class DateUtils {

	// Time values in milliseconds
	public static final int	SECOND	= 1000;
	public static final int	MINUTE	= SECOND * 60;
	public static final int	HOUR	= MINUTE * 60;
	public static final int	DAY		= HOUR * 24;
	public static final int	WEEK	= DAY * 7;

	private DateUtils() {
	}

	/**
	 * 比较c1,c2相差多少分钟
	 * @param c1
	 * @param c2
	 * @return 相差的分钟数
	 */
	public static int compareMinutes(Calendar c1, Calendar c2) {
		return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / MINUTE);
	}

	/**
	 * 比较c1,c2相差多少分钟
	 * @param date1
	 * @param date2
	 * @return 相差的分钟数
	 */
	public static int compareMinutes(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / MINUTE);
	}

	/**
	 * 比较c1,c2相差多少天 返回0 则代表同一天
	 * @param date1
	 * @param date2
	 * @return 相差的天数
	 */
	public static int compareDay(final Calendar date1, final Calendar date2) {
		Calendar bigCopy = (Calendar) date1.clone();
		Calendar smallCopy = (Calendar) date2.clone();
		setTimeToZeroHour(bigCopy);
		setTimeToZeroHour(smallCopy);
		int diff = (int) ((bigCopy.getTimeInMillis() - smallCopy.getTimeInMillis()) / DAY);
		return diff;
	}

	/**
	 * 比较两个时间相差多少天
	 * @param date1
	 * @param date2
	 * @return 相差的天数
	 */
	public static int compareDay(final Date date1, final Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		setTimeToZeroHour(cal1);
		setTimeToZeroHour(cal2);
		int diff = (int) ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / DAY);
		return diff;
	}

	/**
	 * 将calendar设为当天时间的零点
	 * @param calendar
	 */
	private static void setTimeToZeroHour(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	/**
	 * 与今天相差多少天 返回0 则代表今天
	 * @param small
	 * @return 相差的天数
	 */
	public static int compareTody(Calendar small) {
		return compareDay(Calendar.getInstance(), small);
	}

	/**
	 * 与今天相差多少天 返回0 则代表今天
	 * @param date
	 * @return 相差的天数
	 */
	public static int compareTody(Date date) {
		return compareDay(new Date(), date);
	}

	/**
	 * 比较c1,c2相差多少月
	 * @param c1
	 * @param c2
	 * @return 相差的月数
	 */
	public static int compareMonth(Calendar c1, Calendar c2) {
		return compareYear(c1, c2) * 12 + (c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH));
	}

	/**
	 * 比较date1,date2相差多少月
	 * @param date1
	 * @param date2
	 * @return 相差的月数
	 */
	public static int compareMonth(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return compareYear(date1, date2) * 12
				+ (cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH));
	}

	/**
	 * 比较c1,c2相差多少年
	 * @param c1
	 * @param c2
	 * @return 相差的年数
	 */
	public static int compareYear(Calendar c1, Calendar c2) {
		return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
	}

	/**
	 * 比较c1,c2相差多少年
	 * @param date1
	 * @param date2
	 * @return 相差的年数
	 */
	public static int compareYear(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
	}

	/**
	 * 根据所给年份，返回此年的1月1日零时零分零秒日历对象
	 * @param year
	 */
	public static Calendar getYearCalendar(int year) {
		Calendar result = Calendar.getInstance();
		result.clear();
		result.set(Calendar.YEAR, year);
		return result;
	}

	/**
	 * 获取当前年1月1日0时的Calendar对象
	 * @return calendar
	 */
	public static Calendar getYearCalendar() {
		Calendar c = Calendar.getInstance();
		return getYearCalendar(c.get(Calendar.YEAR));
	}

	/**
	 * 获取years年以后的1月1日0时的Calendar对象
	 * @param years
	 * @return calendar
	 */
	public static Calendar getNextYearCalendar(int years) {
		Calendar c = Calendar.getInstance();
		return getYearCalendar(c.get(Calendar.YEAR) + years);
	}

	/**
	 * 根据所给年份，返回此年月1日零时零分零秒日历对象
	 * @param year
	 * @param month
	 * @return calendar
	 */
	public static Calendar getYearAndMonthCalendar(int year, int month) {
		if (month < 0 || month > 11) {
			throw new IllegalArgumentException("month must >=0 && <=11");
		}
		Calendar result = Calendar.getInstance();
		result.clear();
		result.set(Calendar.YEAR, year);
		result.set(Calendar.MONTH, month);
		return result;
	}

	/**
	 * 获取当前年
	 * @return 当前年
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获取当前月
	 * @return 当前月
	 */
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	/**
	 * 返回此日期的第0秒，如2007-3-14 19:00:35 返回 2007-3-14 00:00:00
	 * @param date
	 * @return calendar
	 */
	public static Calendar getFirstSecond(Calendar date) {
		Calendar result = (Calendar) date.clone();
		result.set(Calendar.HOUR_OF_DAY, 0);
		result.set(Calendar.MINUTE, 0);
		result.set(Calendar.SECOND, 0);
		return result;
	}

	/**
	 * 返回此日期的最后1秒，如2007-3-14 19:00:35 返回 2007-3-14 23:59:59
	 * @param date
	 * @return calendar
	 */
	public static Calendar getLastSecond(Calendar date) {
		Calendar result = (Calendar) date.clone();
		result.set(Calendar.HOUR_OF_DAY, 23);
		result.set(Calendar.MINUTE, 59);
		result.set(Calendar.SECOND, 59);
		return result;
	}

	/**
	 * 获取一周以前当前时间的calendar
	 * @return calendar
	 */
	public static Calendar getOneWeekBeforeNow() {
		return addDate(-7);
	}

	/**
	 * 获取两周以前当前时间的calendar
	 * @return calendar
	 */
	public static Calendar getTwoWeekBeforeNow() {
		return addDate(-7 * 2);
	}

	/**
	 * 获取当前时间的days天后的calerndar
	 * @param days
	 * @return calendar
	 */
	private static Calendar addDate(int days) {
		Calendar result = Calendar.getInstance();
		result.add(Calendar.DATE, days);
		return result;
	}

	/**
	 * 是否是今天
	 * @param date
	 * @return 是否
	 */
	public static boolean isToday(Calendar date) {
		return compareDay(date, Calendar.getInstance()) == 0;
	}

	public static DateFormat createShortDateFormat() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	public static DateFormat createGeneralDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	public static DateFormat createChineseDateFormat() {
		return new SimpleDateFormat("yyyy年MM月dd日");
	}

	public static DateFormat createGeneralDateTimeFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
}
