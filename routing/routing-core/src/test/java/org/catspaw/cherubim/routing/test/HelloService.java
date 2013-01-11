package org.catspaw.cherubim.routing.test;

import org.catspaw.cherubim.routing.Route;
import org.catspaw.cherubim.routing.RouteHolder;
import org.catspaw.cherubim.routing.RouteLocal;
import org.catspaw.cherubim.routing.annotation.Key;

public class HelloService {

	private String	id	= "p123";

	public void query(@Key String id) {
		RouteHolder holder = RouteLocal.get();
		Route route = holder.getRoute("h2");
		System.out.println("id=" + id + ", route=" + route.getValue());
	}

	@Key(property = "id")
	public void query2(String id) {
		RouteHolder holder = RouteLocal.get();
		Route route = holder.getRoute("h2");
		System.out.println("id=" + id + ", route=" + route.getValue());
	}
	
	public String getId() {
		return id;
	}
}
