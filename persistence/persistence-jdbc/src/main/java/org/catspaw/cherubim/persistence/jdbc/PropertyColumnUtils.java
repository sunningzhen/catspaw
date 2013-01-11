package org.catspaw.cherubim.persistence.jdbc;

public class PropertyColumnUtils {
	
	/**
	 * 根据数据库命名惯例将实体属性名转为相应的数据库字段名
	 * 规则：逢大写字母就在此字母前加_，并将其改为小写
	 * @param propertyName
	 * @return
	 */
	public static String translateToColumnName(String propertyName) {
		StringBuilder builder = new StringBuilder();
		char[] cs = propertyName.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] > 64 && cs[i] < 91) {
				builder.append('_');
				cs[i] += 32;
			}
			builder.append(cs[i]);
		}
		return builder.toString();
	}

	/**
	 * 根据数据库命名惯例将数据库字段名转为相应的实体属性名
	 * 规则：去除所有_
	 * @param columnName
	 * @return
	 */
	public static String translateToPropertyName(String columnName) {
		return columnName.replaceAll("_", "");
	}
}
