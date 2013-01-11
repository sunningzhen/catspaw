package org.catspaw.cherubim.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface SessionManagerImplementor {

	Session getSession(boolean allowCreate) throws HibernateException;

	Session getSession() throws HibernateException;

	Session getCurrentSession() throws HibernateException;

	Session openSession() throws HibernateException;

	void closeCurrentSession() throws HibernateException;

	SessionFactory getSessionFactory();
}
