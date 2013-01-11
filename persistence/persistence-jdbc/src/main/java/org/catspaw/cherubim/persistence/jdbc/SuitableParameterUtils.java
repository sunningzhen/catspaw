package org.catspaw.cherubim.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

/**
 * 解决ps.setDate时丢失时间部分问题
 * 解决ps.setBoolean时数据库字段类型不匹配问题
 * @author 孙宁振
 */
public final class SuitableParameterUtils {

	public static void setSuitedParameter(PreparedStatement ps, int index,
			Object param) throws SQLException {
		if (param != null) {
			if (param instanceof Date && !(param instanceof Timestamp)) {
				Date date = (Date) param;
				Timestamp timestamp = new Timestamp(date.getTime());
				ps.setTimestamp(index, timestamp);
			} else if (param instanceof Boolean) {
				Boolean b = (Boolean) param;
				if (b.booleanValue()) {
					ps.setObject(index, 1);
				} else {
					ps.setObject(index, 0);
				}
			} else {
				ps.setObject(index, param);
			}
		} else {
			// VARCHAR works with many drivers regardless
			// of the actual column type.  Oddly, NULL and 
			// OTHER don't work with Oracle's drivers.
			ps.setNull(index, Types.VARCHAR);
		}
	}
}
