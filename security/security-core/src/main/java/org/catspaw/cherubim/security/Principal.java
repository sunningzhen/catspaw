package org.catspaw.cherubim.security;

import java.io.Serializable;
import java.util.Collection;

public interface Principal extends java.security.Principal, Serializable {

	String getUsername();

	String getPassword();

	Collection<String> getAuthorities();
}
