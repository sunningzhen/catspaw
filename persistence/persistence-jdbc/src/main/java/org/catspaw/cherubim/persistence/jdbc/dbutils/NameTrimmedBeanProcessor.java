package org.catspaw.cherubim.persistence.jdbc.dbutils;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.dbutils.BeanProcessor;

import org.catspaw.cherubim.persistence.mapping.PropertyColumnUtils;

/**
 * 数据库字段与对象属性匹配时忽略字段中的下划线
 * @author 孙宁振
 */
public class NameTrimmedBeanProcessor extends BeanProcessor {

	@Override
	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,
			PropertyDescriptor[] props) throws SQLException {
		int cols = rsmd.getColumnCount();
		int[] columnToProperty = new int[cols + 1];
		Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);
		for (int col = 1; col <= cols; col++) {
			String columnName = rsmd.getColumnName(col);
			String propertyName = PropertyColumnUtils
					.translateToPropertyName(columnName);
			for (int i = 0; i < props.length; i++) {
				if (propertyName.equalsIgnoreCase(props[i].getName())) {
					columnToProperty[col] = i;
					break;
				}
			}
		}
		return columnToProperty;
	}
}
