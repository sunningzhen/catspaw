package org.catspaw.cherubim.resource;

import java.util.ServiceLoader;

public class DiscoveryResourceMaster<R> implements ResourceMaster<R> {

	private ResourceMaster<R>	delegate;

	public DiscoveryResourceMaster(Class<? extends ResourceMaster<R>> delegateClass) {
		ServiceLoader<? extends ResourceMaster<R>> loader = ServiceLoader
				.load(delegateClass, delegateClass.getClassLoader());
		delegate = loader.iterator().next();
	}

	public <T> T execute(ResourceCallback<R, T> callback) {
		return delegate.execute(callback);
	}

	public R getResource() {
		return delegate.getResource();
	}

	public ResourceMaster<R> getDelegate() {
		return delegate;
	}
}
