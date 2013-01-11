package org.catspaw.cherubim.socketpool;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;

/**
 * SocketPool的简化调用工具类
 */
public class SocketTemplate {

	private SocketPool	pool;	//连接池

	/**
	 * 构造方法
	 * @param pool 连接池
	 */
	public SocketTemplate(SocketPool pool) {
		this.pool = pool;
	}

	/**
	 * 执行回调方法
	 * @param <T> 返回值类型
	 * @param callback 回调接口
	 * @return
	 * @throws NoSuchElementException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public <T> T execute(SocketCallback<T> callback)
			throws NoSuchElementException, InterruptedException, IOException {
		Socket socket = pool.borrowSocket();
		try {
			return callback.execute(socket);
		} catch (IOException e) {
			pool.invalidateSocket(socket);
			throw e;
		} catch (RuntimeException e) {
			pool.invalidateSocket(socket);
			throw e;
		} catch (Error e) {
			pool.invalidateSocket(socket);
			throw e;
		} finally {
			pool.returnSocket(socket);
		}
	}

	public SocketPool getSocketPool() {
		return pool;
	}
}
