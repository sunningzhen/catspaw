package org.catspaw.cherubim.persistence.hibernate.spring;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.catspaw.cherubim.persistence.GeneralPersistenceHandler;

public class SpringHibernatePersistenceHandler extends HibernateDaoSupport implements GeneralPersistenceHandler {

	public <E> E create(Class<E> entityClass) {
		try {
			return entityClass.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	public <E> void save(E entity) {
		getHibernateTemplate().persist(entity);
	}

	public <E, P> P saveAndObtainId(E entity) {
		throw new UnsupportedOperationException();
	}

	public <E, P> E get(Class<E> entityClass, P id) {
		return getHibernateTemplate().get(entityClass, (Serializable) id);
	}

	@SuppressWarnings("unchecked")
	public <E> long total(Class<E> entityClass) {
		List<Long> list = getHibernateTemplate().find("select count(*) from " + entityClass.getName());
		return (Long) list.get(0);
	}

	public <E> List<E> findAll(Class<E> entityClass) {
		return find(entityClass, -1, -1);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> find(Class<E> entityClass, final int offset, final int max) {
		StringBuilder builder = new StringBuilder("select e from ");
		builder.append(entityClass.getName());
		builder.append(" e ");
		final String ql = builder.toString();
		return getHibernateTemplate().executeFind(new HibernateCallback<List<E>>() {

			public List<E> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				if (offset > 0) {
					query.setFirstResult(offset);
				}
				if (max > 0) {
					query.setMaxResults(max);
				}
				return query.list();
			}
		});
	}

	public <E> void update(E entity) {
		getHibernateTemplate().update(entity);
	}

	public <E> void delete(E entity) {
		getHibernateTemplate().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public <E> long countByProperties(Class<E> entityClass, String[] names, Object[] values) {
		if (names.length != values.length) {
			throw new IllegalArgumentException("the length of names and values are not equal!");
		}
		StringBuilder builder = new StringBuilder("select count(*) from ");
		builder.append(entityClass.getName());
		builder.append(" where 1=1 ");
		for (int i = 0; i < names.length; i++) {
			builder.append(" and ");
			builder.append(names[i]);
			builder.append('=');
			builder.append('?');
		}
		List<Long> list = getHibernateTemplate().find(builder.toString(), values);
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public <E> long countByProperty(Class<E> entityClass, String name, Object value) {
		String ql = "select count(*) from " + entityClass.getName() + " where " + name + "=?";
		List<Long> list = getHibernateTemplate().find(ql, value);
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public <E> long countInProperties(Class<E> entityClass, String name, Object[] value) {
		StringBuilder builder = new StringBuilder("select count(*) from ");
		builder.append(entityClass.getName());
		builder.append(" where ");
		builder.append(name);
		builder.append(" in (");
		for (int i = 0; i < value.length; i++) {
			builder.append('?');
			if (i < value.length - 1) {
				builder.append(',');
			}
		}
		builder.append(')');
		List<Long> list = getHibernateTemplate().find(builder.toString(), value);
		return list.get(0);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public <E> void deleteByProperties(Class<E> entityClass, String[] names, final Object[] values) {
		if (names.length != values.length) {
			throw new IllegalArgumentException("the length of names and values are not equal!");
		}
		StringBuilder builder = new StringBuilder("delete from ");
		builder.append(entityClass.getName());
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
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i + 1, values[i]);
				}
				query.executeUpdate();
				return null;
			}
		});
		flush();//刷新Session
	}

	public <E> void deleteByProperty(Class<E> entityClass, String name, final Object value) {
		StringBuilder builder = new StringBuilder("delete from ");
		builder.append(entityClass.getName());
		builder.append(" where ");
		builder.append(name);
		builder.append("=?");
		final String ql = builder.toString();
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				query.setParameter(1, value);
				query.executeUpdate();
				return null;
			}
		});
		flush();//刷新Session
	}

	public <E> void deleteInProperties(Class<E> entityClass, String name, final Object[] value) {
		StringBuilder builder = new StringBuilder("delete from ");
		builder.append(entityClass.getName());
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
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (int i = 0; i < value.length; i++) {
					query.setParameter(i + 1, value[i]);
				}
				query.executeUpdate();
				return null;
			}
		});
		flush();//刷新Session
	}

	public <E> List<E> findByExample(E entity) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findByProperties(Class<E> entityClass, String[] names, Object[] values) {
		if (names.length != values.length) {
			throw new IllegalArgumentException("the length of names and values are not equal!");
		}
		StringBuilder builder = new StringBuilder("select e from ");
		builder.append(entityClass.getName());
		builder.append(" e where 1=1 ");
		for (int i = 0; i < names.length; i++) {
			builder.append(" and ");
			builder.append(names[i]);
			builder.append('=');
			builder.append('?');
		}
		return getHibernateTemplate().find(builder.toString(), values);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findByProperty(Class<E> entityClass, String name, Object value) {
		String ql = "select e from " + entityClass.getName() + " e where " + name + "=?";
		return getHibernateTemplate().find(ql, value);
	}

	public <E> E findByUnique(Class<E> entityClass, String name, Object value) {
		List<E> list = findByProperty(entityClass, name, value);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public <E> List<E> findInProperties(Class<E> entityClass, String name, Object[] value) {
		return findByProperty(entityClass, name, value);
	}

	public <E> void deleteAll(final Collection<E> entities) {
		if (entities.isEmpty()) {
			return;
		}
		Class<? extends Object> entityClass = entities.iterator().next().getClass();
		final String ql = "delete from " + entityClass.getName() + " e where e in ?";
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				query.setParameter(0, entities);
				query.executeUpdate();
				return null;
			}
		});
	}

	public <E> void saveAll(Collection<E> entities) {
		for (E entity : entities) {
			getHibernateTemplate().persist(entity);
		}
	}

	public <E> void updateAll(Collection<E> entities) {
		for (E entity : entities) {
			getHibernateTemplate().merge(entity);
		}
	}

	public void execute(final String ql, final Object... params) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				query.executeUpdate();
				return null;
			}
		});
	}

	public List<?> findList(final String ql, final Object... params) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<?>>() {

			public List<?> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				return query.list();
			}
		});
	}

	public List<?> findList(final String ql, final int offset, final int max, final Object... params) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<?>>() {

			public List<?> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				query.setFirstResult(offset);
				query.setMaxResults(max);
				return query.list();
			}
		});
	}

	public Object findOne(final String ql, final Object... params) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(ql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public <E> Iterator<E> iterateAll(Class<E> entityClass) {
		String hql = "from " + entityClass.getSimpleName();
		return getHibernateTemplate().iterate(hql);
	}

	@SuppressWarnings("unchecked")
	public <E> Iterator<E> iterateByProperty(Class<E> entityClass, String name, Object value) {
		String hql = "from " + entityClass.getSimpleName() + " where " + name + " = ?";
		return getHibernateTemplate().iterate(hql, value);
	}

	@SuppressWarnings("unchecked")
	public <E> Iterator<E> iterateInProperties(Class<E> entityClass, String name, Object[] value) {
		StringBuilder builder = new StringBuilder("from ");
		builder.append(entityClass.getName());
		builder.append(" where ");
		builder.append(name);
		if (value == null || value.length < 1) {
			builder.append(" is null");
			return getHibernateTemplate().iterate(builder.toString());
		}
		if (value.length < 2) {
			builder.append("=?");
			return getHibernateTemplate().iterate(builder.toString(), value[0]);
		}
		builder.append("in(");
		for (int i = 0; i < value.length; i++) {
			builder.append("?");
			if (i < value.length - 1) {
				builder.append(",");
			}
		}
		builder.append(")");
		return getHibernateTemplate().iterate(builder.toString(), value);
	}

	@SuppressWarnings("unchecked")
	public <E> Iterator<E> iterateByProperties(Class<E> entityClass, String[] names, Object[] values) {
		StringBuilder builder = new StringBuilder("from ");
		builder.append(entityClass.getName());
		builder.append(" where ");
		for (int i = 0; i < names.length; i++) {
			builder.append(names[i]);
			builder.append("=?");
			if (i < names.length - 1) {
				builder.append(" and ");
			}
		}
		return getHibernateTemplate().iterate(builder.toString(), values);
	}

	public <E> void saveOrUpdate(E entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void clear() {
		getHibernateTemplate().clear();
		logger.info("session clear");
	}

	public <E> void evict(E entity) {
		getHibernateTemplate().evict(entity);
		logger.info("evict entity: " + entity + " from session cache");
	}

	public <E> void evictFromSessionFactory(Class<E> entityClass) {
		getHibernateTemplate().getSessionFactory().getCache().evictEntityRegion(entityClass);
		logger.info("evict entity for class: " + entityClass.getName() + " from session factory cache");
	}
}
