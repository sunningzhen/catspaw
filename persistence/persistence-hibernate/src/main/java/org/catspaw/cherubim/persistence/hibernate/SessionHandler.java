package org.catspaw.cherubim.persistence.hibernate;

import org.hibernate.Session;

public interface SessionHandler {

	Session getSession();
}
