package org.catspaw.cherubim.persistence;

import java.util.Collection;

public interface PluralPersistenceHandler extends
		GenericPersistenceHandler {

	<E> void deleteAll(Collection<E> entities);

	<E> void saveAll(Collection<E> entities);

	<E> void updateAll(Collection<E> entities);
}
