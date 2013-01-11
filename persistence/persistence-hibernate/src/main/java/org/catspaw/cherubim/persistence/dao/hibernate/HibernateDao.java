package org.catspaw.cherubim.persistence.dao.hibernate;

import java.io.Serializable;
import java.util.Iterator;

import org.catspaw.cherubim.persistence.dao.ExtendedDao;

/**
 * 使用Hibernate框架的Dao接口 提供一些依赖Hibernate的方法
 * @author 孙宁振
 * @param <E> dao所操作的model类型
 * @param <P> model的id类型
 */
public interface HibernateDao<E, P extends Serializable>
		extends ExtendedDao<E, P> {

	/**
	 * @see org.hibernate.Session#clear()
	 */
	void clear();

	/**
	 * @see org.hibernate.Session#evict(Object)
	 * @param entity 实体对象
	 */
	void evict(E entity);

	/**
	 * @see org.hibernate.SessionFactory#evict(Class)
	 */
	void evictFromSessionFactory();

	/**
	 * 刷新缓存
	 */
	void flush();

	/**
	 * 以iterate方式查询所有对象，参见Hibernate的iterate模式查询
	 */
	Iterator<E> iterateAll();

	/**
	 * 以iterate方式查询，参见Hibernate的iterate模式查询
	 */
	Iterator<E> iterateByProperty(String name, Object value);

	Iterator<E> iterateInProperties(String name, Object[] value);

	Iterator<E> iterateByProperties(String[] names, Object[] values);

	/**
	 * @see org.hibernate.Session#load(java.lang.Class, java.io.Serializable)
	 * @param id
	 * @return
	 */
	E load(P id);

	/**
	 * @see org.hibernate.Session#merge(java.lang.Object)
	 * @param entity
	 * @return
	 */
	E merge(E entity);

	/**
	 * @see org.hibernate.Session#persist(java.lang.Object)
	 * @param entity
	 */
	void persist(E entity);

	/**
	 * @see org.hibernate.Session#refresh(java.lang.Object)
	 * @param entity
	 */
	void refresh(E entity);

	/**
	 * @see org.hibernate.Session#saveOrUpdate(java.lang.Object)
	 * @param entity
	 */
	void saveOrUpdate(E entity);
}
