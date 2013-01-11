package org.catspaw.cherubim.persistence;

import java.util.List;

public interface ExtraPersistenceHandler extends PersistenceHandler {

	List<?> findList(String query, Object... params);

	List<?> findList(String query, int offset, int max, Object... params);

	Object findOne(String query, Object... params);

	void execute(String query, Object... params);
}
