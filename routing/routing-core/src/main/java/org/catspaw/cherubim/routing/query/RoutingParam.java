package org.catspaw.cherubim.routing.query;

import org.catspaw.cherubim.routing.AccessStrategy;

public class RoutingParam {

	private String			key;
	private String			kind;
	private String			type;
	private AccessStrategy	accessStrategy	= AccessStrategy.DEFAULT;

	public String getKey() {
		return key;
	}

	public String getKind() {
		return kind;
	}

	public String getType() {
		return type;
	}

	public AccessStrategy getAccessStrategy() {
		return accessStrategy;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAccessStrategy(AccessStrategy accessStrategy) {
		this.accessStrategy = accessStrategy;
	}
}
