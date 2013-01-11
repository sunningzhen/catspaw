package org.catspaw.cherubim.util;

import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class HttpSessionWrapper implements HttpSession {

	private HttpSession	httpSession;

	public HttpSessionWrapper(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public Object getAttribute(String name) {
		return httpSession.getAttribute(name);
	}

	public Enumeration<?> getAttributeNames() {
		return httpSession.getAttributeNames();
	}

	public long getCreationTime() {
		return httpSession.getCreationTime();
	}

	public String getId() {
		return httpSession.getId();
	}

	public long getLastAccessedTime() {
		return httpSession.getLastAccessedTime();
	}

	public int getMaxInactiveInterval() {
		return httpSession.getMaxInactiveInterval();
	}

	public ServletContext getServletContext() {
		return httpSession.getServletContext();
	}

	public HttpSessionContext getSessionContext() {
		return httpSession.getSessionContext();
	}

	public Object getValue(String arg0) {
		return httpSession.getValue(arg0);
	}

	public String[] getValueNames() {
		return httpSession.getValueNames();
	}

	public void invalidate() {
		httpSession.invalidate();
	}

	public boolean isNew() {
		return httpSession.isNew();
	}

	public void putValue(String name, Object value) {
		httpSession.putValue(name, value);
	}

	public void removeAttribute(String name) {
		httpSession.removeAttribute(name);
	}

	public void removeValue(String name) {
		httpSession.removeValue(name);
	}

	public void setAttribute(String name, Object value) {
		httpSession.setAttribute(name, value);
	}

	public void setMaxInactiveInterval(int second) {
		httpSession.setMaxInactiveInterval(second);
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}
}
