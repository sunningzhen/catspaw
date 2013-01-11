package org.catspaw.cherubim.persistence.spring.hibernate;

import org.springframework.orm.hibernate3.HibernateTemplate;

public final class HibernateCacheUtils {

	private static final HibernateTemplate	hibernateTemplate	= new HibernateTemplate();

	private HibernateCacheUtils() {
	}

	/**
	 * @see org.hibernate.Session#clear()
	 */
	public static void clear() {
		hibernateTemplate.clear();
	}

	/**
	 * @see org.hibernate.Session#evict(Object)
	 * @param entity 实体对象
	 */
	public static void evict(Object entity) {
		hibernateTemplate.evict(entity);
	}

	/**
	 * @see org.hibernate.SessionFactory#evict(Class)
	 */
	public static void evictFromSessionFactory(Class<?> clazz) {
		hibernateTemplate.getSessionFactory().getCache().evictEntityRegion(clazz);
	}

	/**
	 * 刷新缓存
	 */
	public static void flush() {
		hibernateTemplate.flush();
	}
}
