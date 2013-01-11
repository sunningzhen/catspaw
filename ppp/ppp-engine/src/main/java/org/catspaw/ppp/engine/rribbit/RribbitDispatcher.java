package org.catspaw.ppp.engine.rribbit;

import java.util.List;

import org.catspaw.ppp.context.Context;
import org.catspaw.ppp.engine.Dispatcher;
import org.rribbit.RequestResponseBus;

public class RribbitDispatcher implements Dispatcher {

	private RequestResponseBus	bus;

	public void dispatch(List<String> tasks, Context context) {
		for (String task : tasks) {
			bus.send(Commands.TASK_START, task, context);
		}
	}
}
