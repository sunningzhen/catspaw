package org.catspaw.cherubim.socketpool;

import java.net.Socket;

/**
 * SocketFactory 的拦截器
 */
public interface SocketFactoryInterceptor {
	
	/**
	 * 创建Socket前执行的方法
	 * @param factory 拦截的工厂
	 * @throws Exception
	 */
	void beforeCreateSocket(SocketFactory factory) throws Exception;
	
	/**
	 * 创建Socket后执行的方法
	 * @param socket 创建出的Socket
	 * @param factory 拦截的工厂
	 * @throws Exception
	 */
	void afterCreateSocket(Socket socket, SocketFactory factory)
			throws Exception;
	
	/**
	 * 销毁Socket前执行的方法
	 * @param socket 需要销毁的Socket
	 * @param factory 拦截的工厂
	 * @throws Exception
	 */
	void beforeDestroySocket(Socket socket, SocketFactory factory)
			throws Exception;
	
	/**
	 * 销毁Socket后执行的方法
	 * @param socket 需要销毁的Socket
	 * @param factory 拦截的工厂
	 * @throws Exception
	 */
	void afterDestroySocket(Socket socket, SocketFactory factory)
			throws Exception;
}
