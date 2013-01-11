package org.catspaw.ppp.engine;

import java.util.List;

import org.catspaw.ppp.context.Context;

public interface Scheduler {

	List<String> acquireTasks(Context context);
}
