package org.catspaw.cherubim.persistence.hibernate;

import org.catspaw.cherubim.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public abstract class HibernateCallback {

	public final Object execute(Session session) throws PersistenceException {
		try {
			return doExecute(session);
		} catch (HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	public abstract Object doExecute(Session session) throws HibernateException;
}
