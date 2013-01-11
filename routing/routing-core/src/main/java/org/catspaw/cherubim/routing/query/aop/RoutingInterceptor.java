package org.catspaw.cherubim.routing.query.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.chain.Chain;
import org.catspaw.cherubim.routing.DefaultRouteHolder;
import org.catspaw.cherubim.routing.Route;
import org.catspaw.cherubim.routing.RouteHolder;
import org.catspaw.cherubim.routing.RouteLocal;
import org.catspaw.cherubim.routing.Router;
import org.catspaw.cherubim.routing.RoutingException;
import org.catspaw.cherubim.routing.query.RoutingParam;
import org.catspaw.cherubim.routing.query.RoutingParams;
import org.catspaw.cherubim.routing.query.chain.RoutingProcessContext;

public class RoutingInterceptor implements MethodInterceptor {

	private Chain				routeProcessChain;
	private Map<String, Router>	routers	= new HashMap<String, Router>();
	private String				defaultRouteType;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object targetObject = invocation.getThis();
		Method executingMethod = invocation.getMethod();
		Object[] parameters = invocation.getArguments();
		RoutingProcessContext context = new RoutingProcessContext();
		context.setTargetObject(targetObject);
		context.setExecutingMethod(executingMethod);
		context.setParameters(parameters);
		context.setRouteParams(new RoutingParams());
		try {
			routeProcessChain.execute(context);
		} catch (Exception e) {
			throw new RoutingException("process route chain error", e);
		}
		//获取线程中绑定的原路由
		RouteHolder history = RouteLocal.get();
		if (context.needRouting()) {
			//如果此方法需要处理路由，则将路由绑定到线程
			RoutingParams params = context.getRouteParams();
			Map<String, Route> generalRoutes = new HashMap<String, Route>();
			Map<String, RoutingParam> generalRoutingParams = params.getGeneralRoutingParam();
			for (Map.Entry<String, RoutingParam> entry : generalRoutingParams.entrySet()) {
				RoutingParam param = entry.getValue();
				Router router = routers.get(param.getType());
				String routeValue = router.findRoute(param.getKey(), param.getType());
				Route route = new Route();
				route.setValue(routeValue);
				route.setType(param.getType());
				route.setAccessStrategy(param.getAccessStrategy());
				generalRoutes.put(param.getType(), route);
			}
			Map<String, Map<String, Route>> assignedRoutes = new HashMap<String, Map<String, Route>>();
			Map<String, Map<String, RoutingParam>> assignedRoutingParams = params.getAssignedRoutingParams();
			for (Map.Entry<String, Map<String, RoutingParam>> entry : assignedRoutingParams.entrySet()) {
				String target = entry.getKey();
				Map<String, Route> routes = new HashMap<String, Route>();
				assignedRoutes.put(target, routes);
				Map<String, RoutingParam> map = entry.getValue();
				for (Map.Entry<String, RoutingParam> entry2 : map.entrySet()) {
					RoutingParam param = entry2.getValue();
					Router router = routers.get(param.getType());
					String routeValue = router.findRoute(param.getKey(), param.getType());
					Route route = new Route();
					route.setValue(routeValue);
					route.setType(param.getType());
					route.setAccessStrategy(param.getAccessStrategy());
					routes.put(param.getType(), route);
				}
			}
			RouteHolder handler = new DefaultRouteHolder(generalRoutes, assignedRoutes, defaultRouteType);
			RouteLocal.bind(handler);
			try {
				//执行方法
				return invocation.proceed();
			} finally {
				//方法执行完毕回复成原来的路由
				RouteLocal.bind(history);
			}
		}
		//执行方法
		return invocation.proceed();
	}

	public Chain getRouteProcessChain() {
		return routeProcessChain;
	}

	public Map<String, Router> getRouters() {
		return routers;
	}

	public String getDefaultRouteType() {
		return defaultRouteType;
	}

	public void setRouteProcessChain(Chain routeProcessChain) {
		this.routeProcessChain = routeProcessChain;
	}

	public void setRouters(Map<String, Router> routers) {
		this.routers = routers;
	}

	public void setDefaultRouteType(String defaultRouteType) {
		this.defaultRouteType = defaultRouteType;
	}
}
