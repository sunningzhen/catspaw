package org.catspaw.cherubim.util;

import java.text.DecimalFormat;

/**
 * 数字工具类
 * 提供数字大小写、百分百等转换
 * @author 孙宁振 sunningzhen@gmail.com
 * @version 1.0 2011-4-17
 */
public final class NumberUtils {

	public static String formatPercent(double number) {
		return createDefaultPercentFormat().format(number);
	}

	public static String formatPercent(double number, int decimalPlace) {
		return createPercentFormat(decimalPlace).format(number);
	}

	public static DecimalFormat createPercentFormat(int decimalPlace) {
		StringBuilder builder = new StringBuilder("##.");
		for (int i = 0; i < decimalPlace; i++) {
			builder.append('0');
		}
		builder.append('%');
		return new DecimalFormat(builder.toString());
	}

	public static DecimalFormat createDefaultPercentFormat() {
		return new DecimalFormat("##.00%");
	}

	/*
	 * 将字符串转换成中文的大写货币值 @param moneyStr @return
	 */
	public static String convertToCapitalMoney(String moneyStr) {
		double money = 0;
		try {
			money = Double.parseDouble(moneyStr);
		} catch (Exception e) {
		}
		return convertToCapitalMoney(money);
	}

	public static void main(String[] args) {
		String s = "1300.884";
		System.out.println(convertToCapitalMoney(s));
	}

	/*
	 * 将数字转换成中文的大写货币值 @param moneyValue @return
	 */
	public static String convertToCapitalMoney(double moneyValue) {
		//TODO 验证功能
		double money = moneyValue + 0.005; // 防止浮点数四舍五入造成误差
		String Result = "";
		String capitalLetter = "零壹贰叁肆伍陆柒捌玖";
		String moneytaryUnit = "分角圆拾佰仟万拾佰仟亿拾佰仟万拾佰仟亿拾佰仟";
		String tempCapital, tempUnit;
		int integer; // 钱的整数部分
		int point; // 钱的小数部分
		int tempValue; // 钱的每一位的值
		integer = (int) money;
		point = (int) (100 * (money - (float) integer));
		if (integer == 0)
			Result = "零圆";
		/*
		 * 货币整数部分操作 1. 依次取得每一位上的值 2. 转换成大写 3. 确定货币单位
		 */
		for (int i = 1; integer > 0; i++) {
			tempValue = (integer % 10);
			tempCapital = capitalLetter.substring(tempValue, tempValue + 1);
			tempUnit = moneytaryUnit.substring(i + 1, i + 2);
			Result = tempCapital + tempUnit + Result;
			integer = integer / 10;
		}
		/*
		 * 货币小数部分操作
		 */
		tempValue = (point / 10);
		for (int i = 1; i > -1; i--) {
			tempCapital = capitalLetter.substring(tempValue, tempValue + 1);
			tempUnit = moneytaryUnit.substring(i, i + 1);
			Result = Result + tempCapital + tempUnit;
			tempValue = point % 10;
		}
		return Result;
	}
}
