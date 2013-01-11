package org.catspaw.cherubim.persistence.jdbc;

public class SqlParts {

	private String selectPart;
	private String fromPart;
	private String wherePart;
	private String groupByPart;
	private String orderByPart;

	public SqlParts(String sql) {
		init(sql);
	}

	protected void init(String sql) {
		sql = formatSql(sql);
		int fromBeginPos = sql.lastIndexOf("from");
		String selectAfter = sql.substring(fromBeginPos);
		int whereBeginPos = selectAfter.indexOf("where") != -1 ? sql
				.lastIndexOf("where") : -1;
		int groupBeginPos = selectAfter.indexOf("group by") != -1 ? sql
				.lastIndexOf("group by") : -1;
		int orderBeginPos = selectAfter.indexOf("order by") != -1 ? sql
				.lastIndexOf("order by") : -1;
		selectPart = getSelect(sql, fromBeginPos);
		fromPart = getFrom(sql, fromBeginPos, whereBeginPos, groupBeginPos,
						   orderBeginPos);
		wherePart = getWhere(sql, whereBeginPos, groupBeginPos, orderBeginPos);
		groupByPart = getGroupBy(sql, groupBeginPos, orderBeginPos);
		orderByPart = getOrderBy(sql, orderBeginPos);
	}

	public static String formatSql(String sql) {
		sql = sql.toLowerCase();
		sql = sql.replaceAll("order\\s*by", "order by");
		sql = sql.replaceAll("group\\s*by", "group by");
		return sql;
	}

	public static String getSelect(String sql, int fromBeginPos) {
		String select = sql.substring(0, fromBeginPos);
		return select;
	}

	public static String getFrom(String sql, int fromBeginPos,
			int whereBeginPos, int groupBeginPos, int orderBeginPos) {
		String from;
		if (whereBeginPos != -1) {
			from = sql.substring(fromBeginPos, whereBeginPos);
		} else if (groupBeginPos != -1) {
			from = sql.substring(fromBeginPos, groupBeginPos);
		} else if (orderBeginPos != -1) {
			from = sql.substring(fromBeginPos, orderBeginPos);
		} else {
			from = sql.substring(fromBeginPos);
		}
		return from;
	}

	public static String getWhere(String sql, int whereBeginPos,
			int groupBeginPos, int orderBeginPos) {
		String where = "";
		if (whereBeginPos != -1) {
			if (groupBeginPos != -1) {
				where = sql.substring(whereBeginPos, groupBeginPos);
			} else if (orderBeginPos != -1) {
				where = sql.substring(whereBeginPos, orderBeginPos);
			} else {
				where = sql.substring(whereBeginPos);
			}
		}
		return where;
	}

	public static String getGroupBy(String sql, int groupBeginPos,
			int orderBeginPos) {
		String group = "";
		if (groupBeginPos != -1) {
			if (orderBeginPos != -1) {
				group = sql.substring(groupBeginPos, orderBeginPos);
			} else {
				group = sql.substring(groupBeginPos);
			}
		}
		return group;
	}

	public static String getOrderBy(String sql, int orderBeginPos) {
		String order = "";
		if (orderBeginPos != -1) {
			order = sql.substring(orderBeginPos);
		}
		return order;
	}

	public String getFromPart() {
		return fromPart;
	}

	public String getGroupByPart() {
		return groupByPart;
	}

	public String getOrderByPart() {
		return orderByPart;
	}

	public String getSelectPart() {
		return selectPart;
	}

	public String getWherePart() {
		return wherePart;
	}

	@Override
	public String toString() {
		String sql = getSelectPart() + getFromPart() + getWherePart()
				+ getGroupByPart() + getOrderByPart();
		return "sql: " + sql;
	}
}
