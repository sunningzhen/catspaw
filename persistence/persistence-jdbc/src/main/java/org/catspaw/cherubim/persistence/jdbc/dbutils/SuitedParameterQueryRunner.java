package org.catspaw.cherubim.persistence.jdbc.dbutils;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.catspaw.cherubim.persistence.jdbc.SuitableParameterUtils;

public class SuitedParameterQueryRunner extends InsertableQueryRunner {

	/**
	 * Is {@link ParameterMetaData#getParameterType(int)} broken (have we tried
	 * it yet)?
	 */
	private volatile boolean	pmdKnownBroken	= false;

	@Override
	public void fillStatement(PreparedStatement stmt, Object... params)
			throws SQLException {
		if (params == null) {
			return;
		}
		ParameterMetaData pmd = stmt.getParameterMetaData();
		if (pmd.getParameterCount() < params.length) {
			throw new SQLException("Too many parameters: expected "
					+ pmd.getParameterCount() + ", was given " + params.length);
		}
		for (int i = 0; i < params.length; i++) {
			if (params[i] != null) {
				SuitableParameterUtils.setSuitedParameter(stmt, i + 1,
															params[i]);
			} else {
				// VARCHAR works with many drivers regardless
				// of the actual column type.  Oddly, NULL and 
				// OTHER don't work with Oracle's drivers.
				int sqlType = Types.VARCHAR;
				if (!pmdKnownBroken) {
					try {
						sqlType = pmd.getParameterType(i + 1);
					} catch (SQLException e) {
						pmdKnownBroken = true;
					}
				}
				stmt.setNull(i + 1, sqlType);
			}
		}
	}
}
