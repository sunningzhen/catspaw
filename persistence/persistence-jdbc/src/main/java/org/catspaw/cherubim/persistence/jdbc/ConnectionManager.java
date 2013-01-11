package org.catspaw.cherubim.persistence.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {

	Connection getCurrentConnection() throws SQLException;

	void closeCurrentConnection() throws SQLException;

	Connection openConnection() throws SQLException;

	Connection getConnection() throws SQLException;

	Connection getConnection(boolean allowCreate) throws SQLException;
}
