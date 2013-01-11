package org.catspaw.cherubim.socketpool;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 连接池中创建的socket
 */
public class PoolableSocket extends Socket {

	private String		id;
	/**
	 * 此socket是否可用
	 */
	private boolean		valid			= true;
	private long		lastReturnTime	= System.currentTimeMillis();
	private SocketPool	socketPool;

	/**
	 * 构造函数
	 * @param host socket所连接的目的地址
	 * @param port socket所连接的目的端口
	 * @param config socket详细参数
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	PoolableSocket(String host, int port, SocketConfig config, SocketPool socketPool) throws UnknownHostException,
			IOException {
		super(host, port);
		if (config != null) {
			initSocket(config);
		}
		id = generateId(null, host, port);
		this.socketPool = socketPool;
	}

	private String generateId(String prefix, String host, int port) {
		String id = host + ":" + port + "_" + System.nanoTime() + ((int) (Math.random() * 10000));
		if (prefix != null) {
			id = prefix + id;
		}
		return id;
	}

	/**
	 * 初始化socket
	 * @param config
	 * @throws SocketException
	 */
	private void initSocket(SocketConfig config) throws SocketException {
		super.setKeepAlive(config.isKeepAlive());
		super.setOOBInline(config.isOobInline());
		super.setReceiveBufferSize(config.getReceiveBufferSize());
		super.setReuseAddress(config.isReuseAddress());
		super.setSendBufferSize(config.getSendBufferSize());
		super.setSoTimeout(config.getSoTimeout());
		super.setTcpNoDelay(config.isTcpNoDelay());
	}

	public String getId() {
		return id;
	}

	/**
	 * 将此socket标记为不可用
	 */
	void invalidate() {
		valid = false;
	}

	/**
	 * 此socket是否可用
	 * @return
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * 关闭socket，调用父类的close方法
	 * @throws IOException
	 */
	void realClose() throws IOException {
		super.close();
	}

	public long getLastReturnTime() {
		return lastReturnTime;
	}
	
	public SocketPool getSocketPool() {
		return socketPool;
	}

	void setLastReturnTime(long lastReturnTime) {
		this.lastReturnTime = lastReturnTime;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str += "_" + getId();
		return str;
	}

	/**
	 * 归还socket
	 */
	@Override
	public void close() {
		socketPool.returnSocket(this);
	}

	/**
	 * 不支持
	 */
	@Override
	public void setKeepAlive(boolean on) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setOOBInline(boolean on) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setReceiveBufferSize(int size) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setReuseAddress(boolean on) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setSendBufferSize(int size) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setSoLinger(boolean on, int linger) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setSoTimeout(int timeout) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setTcpNoDelay(boolean on) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void setTrafficClass(int tc) {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void shutdownInput() {
		// do nothing
	}

	/**
	 * 不支持
	 */
	@Override
	public void shutdownOutput() {
		// do nothing
	}
}
