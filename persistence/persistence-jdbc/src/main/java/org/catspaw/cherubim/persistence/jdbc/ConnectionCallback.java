package org.catspaw.cherubim.persistence.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.catspaw.cherubim.persistence.PersistenceException;

public abstract class ConnectionCallback {

	public final Object execute(Connection conn) throws PersistenceException {
		try {
			return doExecute(conn);
		} catch (SQLException e) {
			throw new PersistenceException( e);
		}
	}

	public abstract Object doExecute(Connection conn) throws SQLException;
}
