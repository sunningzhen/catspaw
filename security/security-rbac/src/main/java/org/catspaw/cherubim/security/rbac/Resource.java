package org.catspaw.cherubim.security.rbac;

/**
 * 资源，如URL等
 * @author sunnz
 */
public interface Resource extends Domain {

	String getResourceString();
}
