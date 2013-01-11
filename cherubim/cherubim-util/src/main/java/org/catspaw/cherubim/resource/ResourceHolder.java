package org.catspaw.cherubim.resource;

public interface ResourceHolder<T> {

	T getCurrentResource();

	void closeCurrentResource();

	T createResource();
}
