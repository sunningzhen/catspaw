package org.catspaw.ppp.transaction;

public interface Transaction {

	void setRollbackOnly(boolean rollbackOnly);

	boolean isRollbackOnly();
}
