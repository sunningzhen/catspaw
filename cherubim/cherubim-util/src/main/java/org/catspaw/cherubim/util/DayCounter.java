package org.catspaw.cherubim.util;

public class DayCounter {

	private static final long	EIGHT_HOURS	= DateUtils.HOUR * 8;	//8小时的毫秒数
	private long				datum;

	public DayCounter() {
		this(System.currentTimeMillis());
	}

	public DayCounter(long datum) {
		long oddment = datum % DateUtils.DAY;//datum时间与当天8点之间的差（long时间0值为1970-1-1 8点）
		this.datum = datum - oddment - EIGHT_HOURS;//datum时间当天0点
	}

	public int countDay(long millisecond) {
		int diff = (int) ((millisecond - datum) / DateUtils.DAY);
		return diff;
	}

	public long getDatum() {
		return datum;
	}
}
