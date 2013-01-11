package org.catspaw.cherubim.persistence.dao;

import java.util.List;
import java.util.Map;

public interface SearchDao {

	Object findOne(String queryString, Map<String, ?> params);

	Object findOne(String queryString, Object... params);

	List<?> findList(String queryString, Map<String, ?> params);

	List<?> findList(String queryString, int offset, int limit,
			Map<String, ?> params);

	List<?> findList(String queryString, Object... params);

	List<?> findList(String queryString, int offset, int limit,
			Object... params);
}
