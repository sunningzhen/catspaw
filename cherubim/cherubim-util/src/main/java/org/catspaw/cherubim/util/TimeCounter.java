package org.catspaw.cherubim.util;

import java.util.Calendar;

public class TimeCounter {

	private static final long	EIGHT_HOURS	= DateUtils.HOUR * 8;	//8小时的毫秒数
	private long				datum;
	private int					firstDayOfWeek;

	public TimeCounter() {
		this(System.currentTimeMillis());
	}

	public TimeCounter(long datum) {
		this(datum, Calendar.SUNDAY);
	}

	/**
	 * @param firstDayOfWeek between java.util.Calendar.SUNDAY and SATURDAY
	 */
	public TimeCounter(int firstDayOfWeek) {
		this(System.currentTimeMillis(), firstDayOfWeek);
	}

	/**
	 * @param datum
	 * @param firstDayOfWeek between java.util.Calendar.SUNDAY and SATURDAY
	 */
	public TimeCounter(long datum, int firstDayOfWeek) {
		if (firstDayOfWeek < Calendar.SUNDAY
				|| firstDayOfWeek > Calendar.SATURDAY) {
			throw new IllegalArgumentException(
					"firstDayOfWeek's value must between java.util.Calendar.SUNDAY and SATURDAY");
		}
		this.datum = datum;
		this.firstDayOfWeek = firstDayOfWeek;
	}

	public long countYear(long time) {
		Calendar currCalendar = Calendar.getInstance();
		currCalendar.setTimeInMillis(time);
		Calendar datumCalendar = Calendar.getInstance();
		datumCalendar.setTimeInMillis(datum);
		return DateUtils.compareYear(currCalendar, datumCalendar);
	}

	public long countMonth(long time) {
		Calendar currCalendar = Calendar.getInstance();
		currCalendar.setTimeInMillis(time);
		Calendar datumCalendar = Calendar.getInstance();
		datumCalendar.setTimeInMillis(datum);
		return DateUtils.compareMonth(currCalendar, datumCalendar);
	}

	public long countWeek(long time) {
		long currFirstDayInWeek = getFirstDayInWeek(time);
		long datumFirstDayInWeek = getFirstDayInWeek(datum);
		return (currFirstDayInWeek - datumFirstDayInWeek) / DateUtils.WEEK;
	}

	private long getFirstDayInWeek(long time) {
		int dayOfWeek = getDayOfWeek(time);
		long currDay = getZeroHour(time);
		int diffDay = (dayOfWeek + 7 - firstDayOfWeek) % 7;//与本周第一天所差的天数
		return currDay - (diffDay * DateUtils.DAY);
	}

	private int getDayOfWeek(long time) {
		long diffTime = time + EIGHT_HOURS;//当前时间与1970-1-1 0点所差时间
		long odd = diffTime % DateUtils.WEEK;//除整周后余时间
		long diffDay = odd / DateUtils.DAY;//余天数
		int day = (int) ((diffDay + Calendar.THURSDAY - 1) % 7 + 1);//1970-1-1是周四
		return day;
	}

	public long countDay(long time) {
		long currDay = getZeroHour(time);
		long datumDay = getZeroHour(datum);
		long diff = (currDay - datumDay) / DateUtils.DAY;
		return diff;
	}

	private long getZeroHour(long time) {
		long oddment = (time + EIGHT_HOURS) % DateUtils.DAY;//time时间与当天0点之间的差（long时间0值为1970-1-1 8点，所以多加8小时）
		return time - oddment;//time时间当天0点
	}

	public long countHour(long time) {
		long currHour = getZeroMinute(time);
		long datumHour = getZeroMinute(datum);
		long diff = (currHour - datumHour) / DateUtils.HOUR;
		return diff;
	}

	private long getZeroMinute(long time) {
		long oddment = time % DateUtils.HOUR;//time时间与当前小时0分之间的差
		return time - oddment;
	}

	public long countMinute(long time) {
		long currMinute = getZeroSecond(time);
		long datumMinute = getZeroSecond(datum);
		long diff = (currMinute - datumMinute) / DateUtils.HOUR;
		return diff;
	}

	private long getZeroSecond(long time) {
		long oddment = time % DateUtils.MINUTE;//time时间与当前分钟0秒之间的差
		return time - oddment;
	}

	public long countMillisecond(long time) {
		return time - datum;
	}

	public long getDatum() {
		return datum;
	}
}
