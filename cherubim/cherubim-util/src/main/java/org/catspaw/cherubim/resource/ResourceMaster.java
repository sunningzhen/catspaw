package org.catspaw.cherubim.resource;

public interface ResourceMaster<R> {

	R getResource();

	<T> T execute(ResourceCallback<R, T> callback);
}
