package org.catspaw.cherubim.socketpool;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Socket的工厂类
 */
public class SocketFactory extends BasePoolableObjectFactory<Socket> {

	/** 日志记录者 */
	private static final Logger				logger	= LoggerFactory.getLogger(SocketFactory.class);
	/** ip地址 */
	private String							host;
	/** 端口号 */
	private int								port;
	/** socket的参数，对应.net.Socket的setXxx方法 */
	private SocketConfig					socketConfig;
	/** 拦截器 */
	private List<SocketFactoryInterceptor>	interceptors;
	private SocketPool						socketPool;

	/**
	 * 构造函数 创建一个流套接字并将其连接到指定 IP 地址的指定端口号。
	 * @param host ip地址
	 * @param port 端口号
	 */
	public SocketFactory(String host, int port, SocketConfig socketConfig, List<SocketFactoryInterceptor> interceptors,
			SocketPool socketPool) {
		this.host = host;
		this.port = port;
		this.socketConfig = socketConfig;
		this.interceptors = interceptors;
		this.socketPool = socketPool;
	}

	/**
	 * 生成对象
	 * @return 返回生成的Objcet（Socket）
	 */
	public Socket makeObject() throws UnknownHostException, IOException, MakeSocketException {
		// 遍历拦截器
		for (SocketFactoryInterceptor interceptor : interceptors) {
			try {
				interceptor.beforeCreateSocket(this);
			} catch (Exception e) {
				throw new MakeSocketException("init socket error", e);
			}
		}
		PoolableSocket socket = null;
		int createCount = 0;
		while (socket == null) {
			socket = new PoolableSocket(host, port, socketConfig, socketPool);
			for (SocketFactoryInterceptor interceptor : interceptors) {
				try {
					interceptor.afterCreateSocket(socket, this);
				} catch (Exception e) {
					logger.error("after create socket interceptor error", e);
					try {
						socket.realClose();
					} catch (IOException ioe) {
						logger.error("close socket error", ioe);
					}
					createCount++;
					if (createCount >= 5) {
						throw new MakeSocketException("init socket error", e);
					}
					socket = null;
					break;
				}
			}
		}
		return socket;
	}

	/**
	 * 销毁对象
	 * @param obj 需要销毁的对象
	 */
	public void destroyObject(Socket obj) throws IOException {
		PoolableSocket socket = (PoolableSocket) obj;
		for (SocketFactoryInterceptor interceptor : interceptors) {
			try {
				interceptor.beforeDestroySocket(socket, this);
			} catch (Exception e) {
				logger.error("before destroy socket interceptor error", e);
			}
		}
		socket.realClose();
		for (SocketFactoryInterceptor interceptor : interceptors) {
			try {
				interceptor.afterDestroySocket(socket, this);
			} catch (Exception e) {
				logger.error("after destroy socket interceptor error", e);
			}
		}
	}

	/**
	 * 检测对象是否可用
	 * @param obj 需要检测的对象
	 * @return 返回一个boolean类型，true可用，false不可用
	 */
	@Override
	public boolean validateObject(Socket obj) {
		PoolableSocket socket = (PoolableSocket) obj;
		return socket.isValid() && socket.isConnected() && !socket.isClosed();
	}

	/**
	 * 为工厂类添加拦截器
	 * @param interceptor SocketFactory对象的拦截器
	 */
	public void addInterceptor(SocketFactoryInterceptor interceptor) {
		synchronized (interceptors) {
			interceptors.add(interceptor);
		}
	}

	/**
	 * 删除工厂类指定的拦截器
	 * @param interceptor SocketFactory对象的拦截器
	 */
	public void removeInterceptor(SocketFactoryInterceptor interceptor) {
		synchronized (interceptors) {
			interceptors.remove(interceptor);
		}
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public SocketConfig getSocketConfig() {
		return socketConfig;
	}

	public List<SocketFactoryInterceptor> getInterceptors() {
		return Collections.unmodifiableList(interceptors);
	}

	public SocketPool getSocketPool() {
		return socketPool;
	}
}
