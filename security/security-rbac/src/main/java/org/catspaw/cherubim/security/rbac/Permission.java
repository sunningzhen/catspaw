package org.catspaw.cherubim.security.rbac;

/**
 * 授权，授权=资源+操作
 * @author sunnz
 */
public interface Permission {

	String getCode();

	String getDomainCode();

	String getActionCode();
	
	String getInstance();
}
