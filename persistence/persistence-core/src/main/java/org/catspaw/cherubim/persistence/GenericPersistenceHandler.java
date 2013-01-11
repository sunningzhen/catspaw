package org.catspaw.cherubim.persistence;

import java.util.List;

public interface GenericPersistenceHandler extends PersistenceHandler {

	<E> E create(Class<E> entityClass);

	<E> void delete(E entity);

	/**
	 * 查询所有
	 * @return
	 */
	<E> List<E> findAll(Class<E> entityClass);

	<E, P> E get(Class<E> entityClass, P id);

	<E> void save(E entity);

	/**
	 * 方法描述：求总数
	 * @return 总数，类型long
	 */
	<E> long total(Class<E> entityClass);

	<E> void update(E entity);
}
