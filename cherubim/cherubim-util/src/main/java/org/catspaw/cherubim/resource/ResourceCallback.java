package org.catspaw.cherubim.resource;

public interface ResourceCallback<R, T> {

	T doInResource(R resource);
}
