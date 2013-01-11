/**
 * Copyright (C), 2011, 中国联通集团系统集成有限公司.
 * All right reserved.
 */
package org.catspaw.cherubim.socketpool;

import java.net.Socket;
import java.util.NoSuchElementException;

/**
 * socket连接池接口
 * 使用时注意一定将借出的socket归还。
 * 使用方法如下：
	Socket socket = pool.borrowSocket();
	try {
		//...use the socket...
	} catch(Exception e) {
		// invalidate the socket
		pool.invalidateSocket(socket);
	} finally {
		// make sure the socket is returned to the pool
		pool.returnSocket(socket);
	}
 */
public interface SocketPool {

	/**
	 * 借出socket
	 * 当池中没有socket时，根据ExhaustAction策略不同会有三种处理方式：
	 * 1. FAIL	：抛出NoSuchElementException异常
	 * 2. BLOCK	：线程阻塞，直到有socket归还
	 * 3. GROW	：自动创建新socket
	 * @return
	 * @throws InterruptedException 当阻塞被打断时
	 * @throws NoSuchElementException 当池中没有socket时
	 */
	Socket borrowSocket() throws InterruptedException, NoSuchElementException;

	/**
	 * 归还借出的socket
	 * @param socket
	 */
	void returnSocket(Socket socket);

	/**
	 * 销毁socket
	 * 当使用socket时出现异常，或需要销毁此socket时使用
	 * @param socket
	 */
	void invalidateSocket(Socket socket);

	/**
	 * 清空连接池，池中所有socket都将被销毁
	 */
	void clear();

	/**
	 * 关闭连接池，连接池一旦关闭将不能再使用
	 */
	void close();

	/**
	 * 获取当前已借出的socket数量
	 * @return
	 */
	int getNumActive();

	/**
	 * 获取当前连接池中存在的空闲socket数量
	 * @return
	 */
	int getNumIdle();

	/**
	 * 如果连接池中所有socket都被借出，当再有借出请求时的处理策略
	 */
	public static enum ExhaustAction {
		/**
		 * 抛出NoSuchElementException
		 */
		FAIL,
		/**
		 * 线程阻塞
		 */
		BLOCK,
		/**
		 * 自动创建新socket
		 */
		GROW;
	}
}
