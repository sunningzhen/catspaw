package org.catspaw.cherubim.resource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class DynamicProxyResourceMaster<R> implements ResourceMaster<R> {

	private R	resource;

	public DynamicProxyResourceMaster(Class<? extends R> resourceClass) {
		resource = (R) Proxy.newProxyInstance(resourceClass.getClassLoader(),
												new Class[] { resourceClass },
												new ResourceInvocationHandler());
	}

	public R getResource() {
		return resource;
	}

	private class ResourceInvocationHandler implements InvocationHandler {

		public Object invoke(Object proxy, final Method method, final Object[] args)
				throws Throwable {
			return DynamicProxyResourceMaster.this.execute(new ResourceCallback<R, Object>() {

				public Object doInResource(R resource) {
					try {
						return method.invoke(resource, args);
					} catch (IllegalAccessException e) {
						throw new IllegalStateException(e);
					} catch (InvocationTargetException e) {
						throw new IllegalStateException(e.getTargetException());
					}
				}
			});
		}
	}
}
