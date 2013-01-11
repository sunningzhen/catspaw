package org.catspaw.ppp.engine.rribbit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.rribbit.ListenerObject;
import org.rribbit.execution.AbstractListenerObjectExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolListenerObjectExecutor extends AbstractListenerObjectExecutor {

	private static final Logger	log				= LoggerFactory.getLogger(ThreadPoolListenerObjectExecutor.class);
	private ExecutorService		executorService	= Executors.newFixedThreadPool(Runtime.getRuntime()
														.availableProcessors());

	@Override
	protected Collection<ExecutionResult> doExecuteListeners(final Collection<ListenerObject> listenerObjects,
			final Object... parameters) {
		final Collection<ExecutionResult> executionResults = new ConcurrentLinkedQueue<ExecutionResult>(); //Vector is Thread-safe
		if (listenerObjects.size() == 1) { //There is only one, don't spawn a new Thread, but do it in this Thread
			log.debug("There is only one ListenerObject, not creating new Thread, executing it in this Thread");
			executionResults.add(this.executeSingleListenerObject(listenerObjects.iterator().next(), parameters));
			return executionResults;
		}
		Collection<Callable<Object>> callables = new ArrayList<Callable<Object>>(listenerObjects.size());
		log.debug("Creating tasks for each ListenerObject");
		for (final ListenerObject listenerObject : listenerObjects) {
			log.debug("Creating task for ListenerObject \"" + listenerObject + "\"");
			Callable<Object> callable = new Callable<Object>() {

				@Override
				public Object call() throws Exception {
					executionResults.add(ThreadPoolListenerObjectExecutor.this
							.executeSingleListenerObject(listenerObject, parameters));
					return null;
				}
			};
			callables.add(callable);
		}
		log.debug("Starting all tasks");
		log.debug("Waiting for all tasks to finish");
		try {
			executorService.invokeAll(callables);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
		log.debug("All tasks have finished, returning");
		return executionResults;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
}
