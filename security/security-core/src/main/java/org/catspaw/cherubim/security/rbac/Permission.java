package org.catspaw.cherubim.security.rbac;

public interface Permission {

	String getPermit();

	String getOperationSymbol();

	String getResourceSymbol();
}
