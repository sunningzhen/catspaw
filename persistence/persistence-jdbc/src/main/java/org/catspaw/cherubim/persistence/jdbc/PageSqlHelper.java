package org.catspaw.cherubim.persistence.jdbc;

/**
 * 生成分页sql等功能的工具
 * @author 孙宁振
 */
public interface PageSqlHelper {

	/**
	 * 生成分页sql
	 * @param sql
	 * @param start 开始记录数，从0开始
	 * @param max 每页记录数
	 * @return
	 */
	String createLimitSql(String sql, int start, int max);

	/**
	 * 生成查询总数sql
	 * @param sql
	 * @return
	 */
	String createCountSql(String sql);

	/**
	 * 生成分页后sql的参数
	 * @param params 原sql的参数
	 * @return
	 */
	Object[] createPageParams(Object... param);
}
