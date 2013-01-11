package org.catspaw.ppp.engine;

import org.catspaw.ppp.context.Context;

public interface Task {

	void execute(Context context) throws Exception;
}
