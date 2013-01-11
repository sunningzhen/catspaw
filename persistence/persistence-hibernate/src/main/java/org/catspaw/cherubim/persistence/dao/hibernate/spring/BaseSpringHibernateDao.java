package org.catspaw.cherubim.persistence.dao.hibernate.spring;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.catspaw.cherubim.persistence.dao.GeneralDao;
import org.catspaw.cherubim.persistence.dao.hibernate.HibernateDao;
import org.catspaw.cherubim.persistence.hibernate.spring.SpringHibernatePersistenceHandler;
import org.catspaw.cherubim.util.GenericTypeUtils;

public class BaseSpringHibernateDao<E, P extends Serializable> extends QueryHibernateDaoSupport implements
		GeneralDao<E, P>, HibernateDao<E, P> {

	private Class<E>							entityClass;												//model的class，即参数化类型<E>
	private Class<P>							idClass;
	private SpringHibernatePersistenceHandler	handler			= new SpringHibernatePersistenceHandler();

	@Override
	protected void initDao() {
		super.initDao();
		handler.setHibernateTemplate(getHibernateTemplate());
		getEntityClass();
		getIdClass();
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<E>) GenericTypeUtils.getSuperClassGenericType(this.getClass());
			logger.info("entity class not initialized, use generic class: " + getEntityClass().getName());
		}
		return entityClass;
	}

	protected void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	protected Class<P> getIdClass() {
		if (idClass == null) {
			idClass = (Class<P>) GenericTypeUtils.getSuperClassGenericType(this.getClass(), 1);
			logger.info("id class not initialized, use generic class: " + getIdClass().getName());
		}
		return idClass;
	}

	protected void setIdClass(Class<P> idClass) {
		this.idClass = idClass;
	}

	public E create() {
		return handler.create(getEntityClass());
	}

	public E get(P id) {
		return handler.get(getEntityClass(), id);
	}

	public void save(E entity) {
		handler.save(entity);
	}

	public P saveAndObtainId(E entity) {
		save(entity);
		return null;
	}

	public long total() {
		return handler.total(getEntityClass());
	}

	public void update(E entity) {
		handler.update(entity);
	}

	public void delete(E entity) {
		handler.delete(entity);
	}

	public List<E> findAll() {
		return handler.findAll(getEntityClass());
	}

	public void deleteAll(Collection<E> entities) {
		handler.deleteAll(entities);
	}

	public void deleteAll(E[] entities) {
		handler.deleteAll(Arrays.asList(entities));
	}

	public void saveAll(Collection<E> entities) {
		handler.saveAll(entities);
	}

	public void saveAll(E[] entities) {
		handler.saveAll(Arrays.asList(entities));
	}

	public void updateAll(Collection<E> entities) {
		handler.updateAll(entities);
	}

	public void updateAll(E[] entities) {
		handler.updateAll(Arrays.asList(entities));
	}

	public List<E> findByExample(E entity) {
		return handler.findByExample(entity);
	}

	public List<E> find(final int offset, final int max) {
		return handler.find(getEntityClass(), offset, max);
	}

	public List<E> findByProperties(String[] names, Object[] values) {
		return handler.findByProperties(getEntityClass(), names, values);
	}

	public List<E> findByProperty(String name, Object value) {
		return handler.findByProperty(getEntityClass(), name, value);
	}

	public List<E> findInProperties(String name, Object[] value) {
		return handler.findInProperties(getEntityClass(), name, value);
	}

	public E findByUnique(String name, Object value) {
		return handler.findByUnique(getEntityClass(), name, value);
	}

	public long countByProperties(String[] names, Object[] values) {
		return handler.countByProperties(getEntityClass(), names, values);
	}

	public long countByProperty(String name, Object value) {
		return handler.countByProperty(getEntityClass(), name, value);
	}

	public long countInProperties(String name, Object[] value) {
		return handler.countInProperties(getEntityClass(), name, value);
	}

	public void deleteByProperties(String[] names, Object[] values) {
		handler.deleteByProperties(getEntityClass(), names, values);
	}

	public void deleteByProperty(String name, Object value) {
		handler.deleteByProperty(getEntityClass(), name, value);
	}

	public void deleteInProperties(String name, Object[] value) {
		handler.deleteInProperties(getEntityClass(), name, value);
	}

	public Iterator<E> iterateAll() {
		return handler.iterateAll(getEntityClass());
	}

	public Iterator<E> iterateByProperties(String[] names, Object[] values) {
		return handler.iterateByProperties(getEntityClass(), names, values);
	}

	public Iterator<E> iterateByProperty(String name, Object value) {
		return handler.iterateByProperty(getEntityClass(), name, value);
	}

	public Iterator<E> iterateInProperties(String name, Object[] value) {
		return handler.iterateInProperties(getEntityClass(), name, value);
	}

	public E load(P id) {
		return getHibernateTemplate().load(getEntityClass(), id);
	}

	public void saveOrUpdate(E entity) {
		handler.saveOrUpdate(entity);
	}

	/**
	 * 刷新缓存 主要作用是立刻抛出可能出现的异常，以便产生相应的业务异常 （否则异常只能在事务提交时才抛出）
	 */
	public void flush() {
		handler.flush();
	}

	public E merge(E entity) {
		return getHibernateTemplate().merge(entity);
	}

	public void persist(E entity) {
		getHibernateTemplate().persist(entity);
	}

	public void refresh(E entity) {
		getHibernateTemplate().refresh(entity);
	}

	public void clear() {
		handler.clear();
	}

	public void evict(E entity) {
		handler.evict(entity);
	}

	public void evictFromSessionFactory() {
		handler.evictFromSessionFactory(getEntityClass());
	}

	public SpringHibernatePersistenceHandler getPersistenceHandler() {
		return handler;
	}
}
