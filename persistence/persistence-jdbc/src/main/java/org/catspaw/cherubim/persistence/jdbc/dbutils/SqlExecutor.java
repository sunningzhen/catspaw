package org.catspaw.cherubim.persistence.jdbc.dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.ResultSetHandler;
import org.catspaw.cherubim.persistence.PersistenceException;
import org.catspaw.cherubim.persistence.jdbc.ConnectionCallback;
import org.catspaw.cherubim.persistence.jdbc.ConnectionManager;

/**
 * sql执行器，基于apache-commons-dbutils实现
 * @author 孙宁振
 */
public class SqlExecutor {

	private ConnectionManager		connectionManager;
	private InsertableQueryRunner	queryRunner;

	public SqlExecutor(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.queryRunner = new SuitedParameterQueryRunner();
	}

	public Object query(final String sql, final ResultSetHandler<?> handler,
			final Object... params) {
		return execute(new ConnectionCallback() {

			public Object doExecute(Connection conn) throws SQLException {
				return queryRunner.query(conn, sql, handler, params);
			}
		});
	}

	public Object insert(final String sql, final Object... params)
			throws PersistenceException {
		return execute(new ConnectionCallback() {

			public Object doExecute(Connection conn) throws SQLException {
				return queryRunner.insert(conn, sql, params);
			}
		});
	}

	public int update(final String sql, final Object... params)
			throws PersistenceException {
		return (Integer) execute(new ConnectionCallback() {

			public Object doExecute(Connection conn) throws SQLException {
				return queryRunner.update(conn, sql, params);
			}
		});
	}

	public int[] batch(final String sql, final Object[][] params)
			throws PersistenceException {
		int[] is = (int[]) execute(new ConnectionCallback() {

			public Object doExecute(Connection conn) throws SQLException {
				Object ret = queryRunner.batch(conn, sql, params);
				return ret;
			}
		});
		return is;
	}

	public Object execute(ConnectionCallback callback)
			throws PersistenceException {
		Connection conn;
		try {
			conn = connectionManager.getCurrentConnection();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		if (conn != null) {
			return callback.execute(conn);
		}
		try {
			conn = connectionManager.openConnection();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		try {
			Object ret = callback.execute(conn);
			if (!conn.getAutoCommit()) {
				conn.commit();
			}
			return ret;
		} catch (SQLException e) {
			try {
				if (!conn.getAutoCommit()) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				throw new PersistenceException(e1.getMessage(), e1);
			}
			throw new PersistenceException(e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
		}
	}
	
	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public InsertableQueryRunner getQueryRunner() {
		return queryRunner;
	}

	public void setQueryRunner(InsertableQueryRunner queryRunner) {
		this.queryRunner = queryRunner;
	}
}
