package org.catspaw.cherubim.security.web;

import javax.servlet.http.HttpServletRequest;

import org.catspaw.cherubim.security.SecurityMaster;

public interface WebSecurityMaster extends SecurityMaster {

	boolean isPermitted(HttpServletRequest request, String operationSymbol);

	boolean isPermitted(String contextPath, String servletPath, String operationSymbol);
}
