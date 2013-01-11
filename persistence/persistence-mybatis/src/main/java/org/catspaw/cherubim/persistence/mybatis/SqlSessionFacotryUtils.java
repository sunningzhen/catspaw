package org.catspaw.cherubim.persistence.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public final class SqlSessionFacotryUtils {

	private static final String						CONFIG_FILE_LOCATION	= "mybatis-config.xml";
	private static final ThreadLocal<SqlSession>	local					= new ThreadLocal<SqlSession>();
	private static String							configFile				= CONFIG_FILE_LOCATION;

	public static SqlSessionFactory getSqlSessionFactory() {
		return Holder.factory;
	}

	public static SqlSession getCurrentSqlSession() {
		SqlSession session = local.get();
		if (session == null) {
			session = openSqlSession();
			local.set(session);
		}
		return session;
	}

	public static SqlSession openSqlSession() {
		return getSqlSessionFactory().openSession();
	}

	public static void closeCurrentSqlSession() {
		SqlSession session = local.get();
		if (session != null) {
			local.remove();
			session.close();
		}
	}

	private static class Holder {

		private static final SqlSessionFactory	factory;
		static {
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			InputStream is = null;
			try {
				is = Resources.getResourceAsStream(configFile);
				factory = builder.build(is);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						throw new IllegalStateException(e);
					}
				}
			}
		}
	}
}
