package org.catspaw.cherubim.persistence;

import java.util.List;

public interface ExtendedPersistenceHandler extends GenericPersistenceHandler {

	<E, P> P saveAndObtainId(E entity);

	<E> long countByProperties(Class<E> entityClass, String[] names, Object[] values);

	/**
	 * 统计指定属性为指定值的model的数量
	 * @param name model属性名
	 * @param value model属性的值，可以为空
	 * @return 数量
	 */
	<E> long countByProperty(Class<E> entityClass, String name, Object value);

	<E> long countInProperties(Class<E> entityClass, String name, Object[] value);

	<E> void deleteByProperties(Class<E> entityClass, String[] names, Object[] values);

	<E> void deleteByProperty(Class<E> entityClass, String name, Object value);

	<E> void deleteInProperties(Class<E> entityClass, String name, Object[] value);

	<E> List<E> findByExample(E entity);

	/**
	 * 某项属性等于指定值
	 * @param name
	 * @param value
	 * @return
	 */
	<E> List<E> findByProperty(Class<E> entityClass, String name, Object value);

	<E> List<E> findInProperties(Class<E> entityClass, String name, Object[] value);

	<E> List<E> findByProperties(Class<E> entityClass, String[] names, Object[] values);

	/**
	 * 唯一属性等于指定值
	 * @param name
	 * @param value
	 * @return
	 */
	<E> E findByUnique(Class<E> entityClass, String name, Object value);
}
