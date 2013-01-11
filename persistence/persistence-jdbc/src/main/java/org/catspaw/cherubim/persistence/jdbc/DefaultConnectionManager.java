package org.catspaw.cherubim.persistence.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.catspaw.cherubim.persistence.PersistenceException;

public class DefaultConnectionManager implements ConnectionManager {

	private static final ThreadLocal<Connection>	threadLocal	= new ThreadLocal<Connection>();
	private static final DataSource					dataSource;
	private static final String						DIRECT_IMPL	= "DIRECT";
	private static final String						JNDI_IMPL	= "JNDI";
	static {
		Properties props = new Properties();
		InputStream is = DefaultConnectionManager.class.getClassLoader()
				.getResourceAsStream("persistence.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			throw new PersistenceException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new PersistenceException(e);
			}
		}
		String impl = props.getProperty("persistence.datasource.impl");
		String jndi = props.getProperty("persistence.datasource.jndi_name");
		String driverClassName = props
				.getProperty("persistence.connection.driver_class");
		String url = props.getProperty("persistence.connection.url");
		String username = props.getProperty("persistence.connection.username");
		String password = props.getProperty("persistence.connection.password");
		if (DIRECT_IMPL.equalsIgnoreCase(impl)) {
			dataSource = new DirectDataSource(driverClassName, url, username,
					password, props);
		} else if (JNDI_IMPL.equalsIgnoreCase(impl)) {
			try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup(jndi);
			} catch (NamingException e) {
				throw new IllegalArgumentException(e);
			}
		} else {
			throw new IllegalArgumentException(
					"persistence.datasource.impl must be not null");
		}
	}

	public Connection getCurrentConnection() throws PersistenceException {
		return getConnection(false);
	}

	public Connection getConnection() throws PersistenceException {
		return getConnection(true);
	}

	public Connection getConnection(boolean allowCreate)
			throws PersistenceException {
		Connection conn = threadLocal.get();
		try {
			if ((conn == null || conn.isClosed()) && allowCreate) {
				conn = dataSource.getConnection();
				threadLocal.set(conn);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return conn;
	}

	public Connection openConnection() throws PersistenceException {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	public void closeCurrentConnection() throws PersistenceException {
		Connection conn = threadLocal.get();
		threadLocal.remove();
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}
