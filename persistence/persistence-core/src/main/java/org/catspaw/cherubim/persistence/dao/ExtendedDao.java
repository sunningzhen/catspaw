package org.catspaw.cherubim.persistence.dao;

import java.util.List;

public interface ExtendedDao<E, P> extends GenericDao<E, P> {

	P saveAndObtainId(E entity);

	long countByProperties(String[] names, Object[] values);

	/**
	 * 统计指定属性为指定值的model的数量
	 * @param name model属性名
	 * @param value model属性的值，可以为空
	 * @return 数量
	 */
	long countByProperty(String name, Object value);

	long countInProperties(String name, Object[] value);

	void deleteByProperties(String[] names, Object[] values);

	void deleteByProperty(String name, Object value);

	void deleteInProperties(String name, Object[] value);

	List<E> findByExample(E entity);

	/**
	 * 某项属性等于指定值
	 * @param name
	 * @param value
	 * @return
	 */
	List<E> findByProperty(String name, Object value);

	List<E> findInProperties(String name, Object[] value);

	List<E> findByProperties(String[] names, Object[] values);

	/**
	 * 唯一属性等于指定值
	 * @param name
	 * @param value
	 * @return
	 */
	E findByUnique(String name, Object value);
}
