package org.catspaw.cherubim.routing;

import org.catspaw.cherubim.routing.AccessStrategy;

public class Route {

	private String			type;
	private String			value;
	private AccessStrategy	accessStrategy	= AccessStrategy.DEFAULT;

	public String getValue() {
		return value;
	}

	public String getType() {
		return type;
	}

	public AccessStrategy getAccessStrategy() {
		return accessStrategy;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAccessStrategy(AccessStrategy accessStrategy) {
		this.accessStrategy = accessStrategy;
	}
}
