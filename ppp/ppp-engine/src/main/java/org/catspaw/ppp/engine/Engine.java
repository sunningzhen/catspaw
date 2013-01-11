package org.catspaw.ppp.engine;

import java.util.Map;

public interface Engine {

	void execute(String business, Map<String, Object> args);
}
