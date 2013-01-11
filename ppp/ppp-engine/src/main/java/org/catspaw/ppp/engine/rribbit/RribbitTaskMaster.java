package org.catspaw.ppp.engine.rribbit;

import java.util.List;

import org.catspaw.ppp.context.Context;
import org.catspaw.ppp.engine.Dispatcher;
import org.catspaw.ppp.engine.Scheduler;
import org.catspaw.ppp.engine.Task;
import org.catspaw.ppp.engine.TaskFactory;
import org.catspaw.ppp.transaction.Transaction;
import org.rribbit.Listener;
import org.rribbit.RequestResponseBus;

public class RribbitTaskMaster {

	private TaskFactory			factory;
	private Scheduler			scheduler;
	private Dispatcher			dispatcher;
	private RequestResponseBus	bus;

	@Listener(hint = Commands.TASK_START)
	public void dispatchTask(String taskCode, Context context) {
		Task task = factory.getTask(taskCode);
		try {
			task.execute(context);
			bus.send(Commands.TASK_COMPLETE, context);
		} catch (Exception e) {
			context.getTransaction().setRollbackOnly(true);
			context.setException(e);
			bus.send(Commands.TASK_ERROR, context);
		}
	}

	@Listener(hint = Commands.TASK_COMPLETE)
	public void onTaskComplete(Context context) {
		List<String> tasks = scheduler.acquireTasks(context);
		if (!tasks.isEmpty()) {
			dispatcher.dispatch(tasks, context);
		}
	}

	@Listener(hint = Commands.TASK_ERROR)
	public void onTaskError(Context context) {
		Transaction trasaction = context.getTransaction();
		trasaction.setRollbackOnly(true);
		bus.send(Commands.ROLLBACK_TRANSACTION, context);
	}
}
