package org.catspaw.cherubim.security.rbac;

public interface Resource {

	String getSymbol();

	String getType();

	String asString();
}
