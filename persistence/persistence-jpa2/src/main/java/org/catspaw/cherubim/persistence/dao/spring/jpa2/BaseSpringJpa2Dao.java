package org.catspaw.cherubim.persistence.dao.spring.jpa2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.core.SpringVersion;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import org.catspaw.cherubim.persistence.dao.ExtendedDao;
import org.catspaw.cherubim.util.GenericTypeUtils;

public class BaseSpringJpa2Dao<E, P extends Serializable> extends JpaDaoSupport
		implements ExtendedDao<E, P> {

	private Class<E>	entityClass;	//model的class，即参数化类型<E>
	private Class<P>	idClass;

	@Override
	protected void initDao() {
		getEntityClass();
		getIdClass();
	}

	protected Class<E> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<E>) GenericTypeUtils
					.getSuperClassGenericType(this.getClass());
			logger.info("entity class not initialized, use generic class: "
					+ getEntityClass().getName());
		}
		return entityClass;
	}

	protected void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	protected Class<P> getIdClass() {
		if (idClass == null) {
			idClass = (Class<P>) GenericTypeUtils.getSuperClassGenericType(this
					.getClass(), 1);
			logger.info("id class not initialized, use generic class: "
					+ getIdClass().getName());
		}
		return idClass;
	}

	protected void setIdClass(Class<P> idClass) {
		this.idClass = idClass;
	}

	/**
	 * 刷新缓存 主要作用是立刻抛出可能出现的异常，以便产生相应的业务异常 （否则异常只能在事务提交时才抛出）
	 */
	public void flush() {
		getJpaTemplate().flush();
		logger.debug("session cache flush");
	}

	public E create() {
		try {
			return getEntityClass().newInstance();
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * 求总数
	 */
	public long total() {
		Number num = getJpaTemplate().execute(new JpaCallback<Number>() {

			public Number doInJpa(EntityManager em) throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Long> criteriaQuery = builder
						.createQuery(Long.class);
				Root<E> root = criteriaQuery.from(getEntityClass());
				criteriaQuery.select(builder.count(root));
				TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
				return typedQuery.getSingleResult();
			}
		});
		return num.longValue();
	}

	public E get(P id) {
		return (E) getJpaTemplate().find(getEntityClass(), (Serializable) id);
	}

	public void save(E entity) {
		getJpaTemplate().persist(entity);
	}

	public P saveAndObtainId(E entity) {
		getJpaTemplate().persist(entity);
		return null;
	}

	public void saveAll(E[] entities) {
		saveAll(Arrays.asList(entities));
	}

	public void saveAll(final Collection<E> entities) {
		getJpaTemplate().execute(new JpaCallback<Object>() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				for (E e : entities) {
					em.persist(e);
				}
				return null;
			}
		});
		flush();
	}

	public void delete(E entity) {
		getJpaTemplate().remove(entity);
	}

	public void deleteAll(final Collection<E> entities) {
		getJpaTemplate().execute(new JpaCallback<Object>() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				for (E e : entities) {
					em.remove(e);
				}
				return null;
			}
		});
		flush();
	}

	public void deleteAll(E[] entities) {
		deleteAll(Arrays.asList(entities));
	}

	public void deleteByProperty(String name, final Object value) {
		StringBuilder builder = new StringBuilder("delete from ");
		builder.append(getEntityClass().getName());
		builder.append(" where ");
		builder.append(name);
		builder.append("=?");
		final String ql = builder.toString();
		getJpaTemplate().execute(new JpaCallback<Integer>() {

			public Integer doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createQuery(ql);
				SpringVersion.getVersion();
				getJpaTemplate().prepareQuery(query);//spring 3.0
				query.setParameter(1, value);
				return query.executeUpdate();
			}
		});
		flush();//刷新Session
	}

	public void deleteInProperties(String name, final Object[] value) {
		StringBuilder builder = new StringBuilder("delete from ");
		builder.append(getEntityClass().getName());
		builder.append(" where ");
		builder.append(name);
		if (value == null || value.length < 1) {
			builder.append(" is null ");
		} else if (value.length < 2) {
			builder.append("=?");
		} else {
			builder.append(" in(");
			for (int i = 0; i < value.length; i++) {
				builder.append("?");
				if (i < value.length - 1) {
					builder.append(",");
				}
			}
			builder.append(") ");
		}
		final String ql = builder.toString();
		getJpaTemplate().execute(new JpaCallback<Object>() {

			public Integer doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createQuery(ql);
				getJpaTemplate().prepareQuery(query);//spring 3.0
				for (int i = 0; i < value.length; i++) {
					query.setParameter(i + 1, value[i]);
				}
				return query.executeUpdate();
			}
		});
		flush();//刷新Session
	}

	public void deleteByProperties(String[] names, final Object[] values) {
		if (names.length != values.length) {
			throw new IllegalArgumentException(
					"the length of names and values are not equal!");
		}
		StringBuilder builder = new StringBuilder("delete from ");
		builder.append(getEntityClass().getName());
		builder.append(" where ");
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			builder.append(name);
			builder.append("=?");
			if (i < names.length - 1) {
				builder.append(" and ");
			}
		}
		final String ql = builder.toString();
		getJpaTemplate().execute(new JpaCallback<Object>() {

			public Integer doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createQuery(ql);
				getJpaTemplate().prepareQuery(query);//spring 3.0
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i + 1, values[i]);
				}
				return query.executeUpdate();
			}
		});
		flush();//刷新Session
	}

	public void update(E entity) {
		getJpaTemplate().merge(entity);
	}

	public void updateAll(E[] entities) {
		for (E entity : entities) {
			getJpaTemplate().merge(entity);
		}
	}

	public void updateAll(Collection<E> entities) {
		for (E entity : entities) {
			getJpaTemplate().merge(entity);
		}
	}

	public List<E> findAll() {
		return find(-1, -1);
	}

	public List<E> findByProperty(String name, Object value) {
		return findByProperty(name, value, -1, -1);
	}

	public List<E> findByProperty(final String name, final Object value,
			final int offset, final int max) {
		return (List<E>) getJpaTemplate().execute(new JpaCallback<List<E>>() {

			public List<E> doInJpa(EntityManager em)
					throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<E> criteriaQuery = builder
						.createQuery(getEntityClass());
				Root<E> root = criteriaQuery.from(getEntityClass());
				if (value != null) {
					criteriaQuery.where(builder.equal(root.get(name), value));
				} else {
					criteriaQuery.where(builder.isNull(root.get(name)));
				}
				addDefaultOrder(criteriaQuery, builder, root);
				TypedQuery<E> typedQuery = em.createQuery(criteriaQuery);
				typedQuery.setFirstResult(offset);
				typedQuery.setMaxResults(max);
				return typedQuery.getResultList();
			}
		});
	}

	public List<E> findInProperties(String name, Object[] value) {
		return findInProperties(name, value, -1, -1);
	}

	public List<E> findInProperties(final String name, final Object[] value,
			final int offset, final int max) {
		return (List<E>) getJpaTemplate().execute(new JpaCallback<List<E>>() {

			public List<E> doInJpa(EntityManager em)
					throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<E> criteriaQuery = builder
						.createQuery(getEntityClass());
				Root<E> root = criteriaQuery.from(getEntityClass());
				if (value == null || value.length < 1) {
					criteriaQuery.where(builder.isNull(root.get(name)));
				} else if (value.length < 2) {
					criteriaQuery
							.where(builder.equal(root.get(name), value[0]));
				} else {
					criteriaQuery.where(builder.in(root.in(value)));
				}
				addDefaultOrder(criteriaQuery, builder, root);
				TypedQuery<E> typedQuery = em.createQuery(criteriaQuery);
				typedQuery.setFirstResult(offset);
				typedQuery.setMaxResults(max);
				return typedQuery.getResultList();
			}
		});
	}

	public List<E> findByProperties(String[] names, Object[] values) {
		return findByProperties(names, values, -1, -1);
	}

	public List<E> findByProperties(final String[] names,
			final Object[] values, final int offset, final int max) {
		if (names.length != values.length) {
			throw new IllegalArgumentException(
					"the length of names and values are not equal!");
		}
		return (List<E>) getJpaTemplate().execute(new JpaCallback<List<E>>() {

			public List<E> doInJpa(EntityManager em)
					throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<E> criteriaQuery = builder
						.createQuery(getEntityClass());
				Root<E> root = criteriaQuery.from(getEntityClass());
				for (int i = 0; i < names.length; i++) {
					String name = names[i];
					Object value = values[i];
					if (value != null) {
						criteriaQuery.where(builder
								.equal(root.get(name), value));
					} else {
						criteriaQuery.where(builder.isNull(root.get(name)));
					}
				}
				addDefaultOrder(criteriaQuery, builder, root);
				TypedQuery<E> typedQuery = em.createQuery(criteriaQuery);
				typedQuery.setFirstResult(offset);
				typedQuery.setMaxResults(max);
				return typedQuery.getResultList();
			}
		});
	}

	public E findByUnique(String name, Object value) {
		List<E> list = findByProperty(name, value);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public List<E> find(final int offset, final int max) {
		return (List<E>) getJpaTemplate().execute(new JpaCallback<List<E>>() {

			public List<E> doInJpa(EntityManager em)
					throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<E> criteriaQuery = builder
						.createQuery(getEntityClass());
				Root<E> root = criteriaQuery.from(getEntityClass());
				addDefaultOrder(criteriaQuery, builder, root);
				TypedQuery<E> typedQuery = em.createQuery(criteriaQuery);
				if (offset >= 0) {
					typedQuery.setFirstResult(offset);
				}
				if (max >= 0) {
					typedQuery.setMaxResults(max);
				}
				return typedQuery.getResultList();
			}
		});
	}

	public List<E> findByExample(E entity) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 统计指定属性为指定值的model的数量
	 * @param name model属性名
	 * @param value model属性的值，可以为空
	 * @return 数量
	 */
	public long countByProperty(final String name, final Object value) {
		Number num = getJpaTemplate().execute(new JpaCallback<Number>() {

			public Number doInJpa(EntityManager em) throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Long> criteriaQuery = builder
						.createQuery(Long.class);
				Root<E> root = criteriaQuery.from(getEntityClass());
				if (value != null) {
					criteriaQuery.where(builder.equal(root.get(name), value));
				} else {
					criteriaQuery.where(builder.isNull(root.get(name)));
				}
				criteriaQuery.select(builder.count(root));
				TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
				return typedQuery.getSingleResult();
			}
		});
		return num.longValue();
	}

	public long countInProperties(final String name, final Object[] value) {
		Number num = getJpaTemplate().execute(new JpaCallback<Number>() {

			public Number doInJpa(EntityManager em) throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Long> criteriaQuery = builder
						.createQuery(Long.class);
				Root<E> root = criteriaQuery.from(getEntityClass());
				if (value == null || value.length < 1) {
					criteriaQuery.where(builder.isNull(root.get(name)));
				} else if (value.length < 2) {
					criteriaQuery
							.where(builder.equal(root.get(name), value[0]));
				} else {
					criteriaQuery.where(builder.in(root.in(value)));
				}
				criteriaQuery.select(builder.count(root));
				TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
				return typedQuery.getSingleResult();
			}
		});
		return num.longValue();
	}

	public long countByProperties(final String[] names, final Object[] values) {
		if (names.length != values.length) {
			throw new IllegalArgumentException(
					"the length of names and values are not equal!");
		}
		Number num = getJpaTemplate().execute(new JpaCallback<Number>() {

			public Number doInJpa(EntityManager em) throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Long> criteriaQuery = builder
						.createQuery(Long.class);
				Root<E> root = criteriaQuery.from(getEntityClass());
				for (int i = 0; i < names.length; i++) {
					String name = names[i];
					Object value = values[i];
					if (value != null) {
						criteriaQuery.where(builder
								.equal(root.get(name), value));
					} else {
						criteriaQuery.where(builder.isNull(root.get(name)));
					}
				}
				TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
				return typedQuery.getSingleResult();
			}
		});
		return num.longValue();
	}

	protected void addDefaultOrder(CriteriaQuery<E> query,
			CriteriaBuilder builder, Root<E> root) {
	}

	public void persist(E entity) {
		getJpaTemplate().persist(entity);
	}

	public void refresh(E entity) {
		getJpaTemplate().refresh(entity);
	}

	public E merge(E entity) {
		return (E) getJpaTemplate().merge(entity);
	}
}
