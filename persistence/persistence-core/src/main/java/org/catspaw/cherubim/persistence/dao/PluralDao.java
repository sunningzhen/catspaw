package org.catspaw.cherubim.persistence.dao;

import java.util.Collection;

public interface PluralDao<E, P> extends GenericDao<E, P> {

	void deleteAll(Collection<E> entities);

	void deleteAll(E[] entities);

	void saveAll(Collection<E> entities);

	void saveAll(E[] entities);

	void updateAll(Collection<E> entities);

	void updateAll(E[] entities);
}
