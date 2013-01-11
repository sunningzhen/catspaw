package org.catspaw.ppp.engine;

import java.util.List;

import org.catspaw.ppp.context.Context;

public interface Dispatcher {

	void dispatch(List<String> tasks, Context context);
}
