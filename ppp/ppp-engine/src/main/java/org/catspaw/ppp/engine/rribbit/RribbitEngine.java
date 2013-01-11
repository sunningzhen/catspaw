package org.catspaw.ppp.engine.rribbit;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.catspaw.ppp.context.Context;
import org.catspaw.ppp.context.ContextFactory;
import org.catspaw.ppp.engine.Dispatcher;
import org.catspaw.ppp.engine.Engine;
import org.catspaw.ppp.engine.Scheduler;

public class RribbitEngine implements Engine {

	private ContextFactory	factory;
	private Scheduler		scheduler;
	private Dispatcher		dispatcher;

	public void execute(String business, Map<String, Object> args) {
		Context context = factory.createContext(business);
		CountDownLatch latch = new CountDownLatch(1);
		context.setAttribute("DONE_LATCH", latch);
		for (Map.Entry<String, Object> entry : args.entrySet()) {
			context.setAttribute(entry.getKey(), entry.getValue());
		}
		List<String> tasks = scheduler.acquireTasks(context);
		dispatcher.dispatch(tasks, context);
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
