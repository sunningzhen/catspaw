package org.catspaw.cherubim.routing.test;

import org.catspaw.cherubim.routing.Router;

public class TestRouter implements Router {

	@Override
	public String findRoute(String key, String type) {
		return "jdbc:h2:mem:test";
	}
}
