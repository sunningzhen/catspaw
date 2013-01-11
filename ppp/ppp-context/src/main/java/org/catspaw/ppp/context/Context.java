package org.catspaw.ppp.context;

import org.catspaw.ppp.transaction.Transaction;

public interface Context {

	Transaction getTransaction();

	void setException(Exception e);

	Exception getException();

	void setAttribute(String name, Object value);

	Object getAttribute(String name);

	void removeAttribute(String name);

	void init();

	void destroy();
}
