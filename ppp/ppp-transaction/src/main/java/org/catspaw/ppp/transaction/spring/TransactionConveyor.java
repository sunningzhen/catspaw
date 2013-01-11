package org.catspaw.ppp.transaction.spring;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @see TransactionSynchronizationManager
 * @author sunningzhen
 */
public class TransactionConveyor {

	private Map<Object, Object>				resources;
	private Set<TransactionSynchronization>	synchronizations;
	private String							currentTransactionName;
	private Boolean							currentTransactionReadOnly;
	private int								currentTransactionIsolationLevel;
	private Boolean							actualTransactionActive;

	public void extractFromCurrentThread() {
		Map<Object, Object> map = TransactionSynchronizationManager.getResourceMap();
		if (map != null && !map.isEmpty()) {
			resources = map;
		}
		List<TransactionSynchronization> syncs = TransactionSynchronizationManager.getSynchronizations();
		if (syncs != null) {
			synchronizations = new LinkedHashSet<TransactionSynchronization>(syncs);
		}
		currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
		currentTransactionReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
		currentTransactionIsolationLevel = TransactionSynchronizationManager.getCurrentTransactionIsolationLevel();
		actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
	}

	public void putToCurrentThread() {
		if (resources != null && !resources.isEmpty()) {
			for (Map.Entry<Object, Object> entry : resources.entrySet()) {
				TransactionSynchronizationManager.bindResource(entry.getKey(), entry.getValue());
			}
		}
		if (synchronizations != null) {
			for (TransactionSynchronization synchronization : synchronizations) {
				TransactionSynchronizationManager.registerSynchronization(synchronization);
			}
		}
		TransactionSynchronizationManager.setCurrentTransactionName(currentTransactionName);
		TransactionSynchronizationManager.setCurrentTransactionReadOnly(currentTransactionReadOnly);
		TransactionSynchronizationManager.setCurrentTransactionIsolationLevel(currentTransactionIsolationLevel);
		TransactionSynchronizationManager.setActualTransactionActive(actualTransactionActive);
	}

	public void clearOnCurrentThread() {
		Set<Object> keys = TransactionSynchronizationManager.getResourceMap().keySet();
		for (Object key : keys) {
			TransactionSynchronizationManager.unbindResource(key);
		}
		TransactionSynchronizationManager.clear();
	}
}
