package org.catspaw.cherubim.persistence.mybatis;

import org.apache.ibatis.session.SqlSession;

public interface SqlSessionCallback<T> {

	T doInSqlSession(SqlSession session);
}
