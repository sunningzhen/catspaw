package org.catspaw.cherubim.socketpool;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.apache.commons.pool.PoolUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * socket连接池实现类，基于commons-pool对象池组件实现
 * 此连接池中借出的socket均不允许更改任何状态（包括关闭），所有被借出的socket均由连接池创建
 */
public class DefaultSocketPool implements SocketPool {

	private static final Logger			logger	= LoggerFactory.getLogger(DefaultSocketPool.class);
	/**
	 * 创建socket的目的地址
	 */
	private String						remoteHost;
	/**
	 * 创建socket的目的端口
	 */
	private int							remotePort;
	/**
	 * commons-pool对象池实现
	 */
	private GenericObjectPool<Socket>	pool;

	/**
	 * 指定目的地址和端口创建连接池
	 * @param host socket所连接的目的地址
	 * @param port socket所连接的目的端口
	 */
	public DefaultSocketPool(String host, int port) {
		this(host, port, null);
	}

	/**
	 * 指定目的地址、端口和socket配置创建连接池
	 * @param host socket所连接的目的地址
	 * @param port socket所连接的目的端口
	 * @param config socket详细参数
	 */
	public DefaultSocketPool(String host, int port, SocketConfig config) {
		remoteHost = host;
		remotePort = port;
		SocketFactory factory = new SocketFactory(host, port, config, new ArrayList<SocketFactoryInterceptor>(), this);
		pool = new GenericObjectPool<Socket>(factory);
		initObjectPool(pool);
	}

	/**
	 * 指定目的地址、端口和socket配置创建连接池，并初始化一定数量socket
	 * @param host socket所连接的目的地址
	 * @param port socket所连接的目的端口
	 * @param config socket详细参数
	 * @param prefillSize 初始化socket数量
	 */
	public DefaultSocketPool(String host, int port, SocketConfig config, int prefillSize) {
		this(host, port, config);
		fill(prefillSize);
	}

	/**
	 * 初始化连接池参数
	 * @param pool
	 */
	private void initObjectPool(GenericObjectPool<Socket> pool) {
		setTestOnReturn(true);
		setLifo(false);
		setExhaustAction(ExhaustAction.GROW);
		setMaxActive(-1);
		setEvictInterval(300000);
		setEvictTestNum(-2);
	}

	/**
	 * 自动创建socket并填充到连接池
	 * @param num
	 */
	public void fill(int num) {
		try {
			PoolUtils.prefill(pool, num);
		} catch (IOException e) {
			// from factory.makeObject()
			throw new SocketPoolException("fill socket faild", e);
		} catch (Exception e) {
			throw new SocketPoolException("fill socket faild", e);
		}
	}

	/**
	 * 借出socket 当池中没有socket时，根据ExhaustAction策略不同会有三种处理方式： 
	 * 1. FAIL：抛出NoSuchElementException异常
	 * 2. BLOCK：线程阻塞，直到有socket归还
	 * 3. GROW：自动创建新socket
	 * @return
	 * @throws InterruptedException 当阻塞被打断时
	 * @throws NoSuchElementException 当池中没有socket时
	 */
	public Socket borrowSocket() throws InterruptedException, NoSuchElementException {
		// 如果需要在借出前测试socket是否可用
		if (isTestOnBorrow()) {
			int cnt = 0;
			// 借出socket，如果借出的socket可用则返回，否则循环借出
			// 直到连接池中所有socket都循环一遍，如果仍不可用，则抛出异常
			while (!Thread.interrupted()) {
				try {
					PoolableSocket socket = (PoolableSocket) pool.borrowObject();
					logger.debug("borrow socket: " + socket.getId());
					return socket;
				} catch (RuntimeException e) {
					throw e;
				} catch (InterruptedException e) {
					throw e;
				} catch (IOException e) {
					// from factory.makeObject()
					throw new SocketPoolException("take socket faild", e);
				} catch (Exception e) {
					// when use testOnBorrow and the borrowed socket is not
					// valid
					cnt++;
					if (cnt >= pool.getNumActive() + pool.getNumIdle()) {
						throw new SocketPoolException("take socket faild", e);
					}
				}
			}
		} else {
			try {
				PoolableSocket socket = (PoolableSocket) pool.borrowObject();
				logger.debug("borrow socket: " + socket.getId());
				return socket;
			} catch (RuntimeException e) {
				throw e;
			} catch (InterruptedException e) {
				throw e;
			} catch (IOException e) {
				// from factory.makeObject()
				throw new SocketPoolException("take socket faild", e);
			} catch (Exception e) {
				throw new SocketPoolException("take socket faild", e);
			}
		}
		return null;
	}

	/**
	 * 归还借出的socket
	 * @param socket
	 */
	public void returnSocket(Socket socket) {
		// 检查socket类型，只能归还PoolableSocket
		checkSocketType(socket);
		PoolableSocket poolableSocket = (PoolableSocket) socket;
		// 只有socket处于可用状态时，才归还到池里
		if (poolableSocket.isValid()) {
			poolableSocket.setLastReturnTime(System.currentTimeMillis());
			try {
				pool.returnObject(socket);
				logger.debug("return socket: " + poolableSocket.getId());
			} catch (RuntimeException e) {
				throw e;
			} catch (IOException e) {
				// from factory.destroyObject()
				logger.error("return socket failed", e);
			} catch (Exception e) {
				// ignored, never throws
			}
		}
	}

	public void invalidateSocket(Socket socket) {
		checkSocketType(socket);
		PoolableSocket poolableSocket = (PoolableSocket) socket;
		poolableSocket.invalidate();
		try {
			pool.invalidateObject(poolableSocket);
			logger.debug("invalidate socket: " + poolableSocket.getId());
		} catch (Exception e) {
			// from factory.destroyObject()
			logger.error("invalidate socket failed", e);
		}
	}

	protected void checkSocketType(Socket socket) {
		if (!(socket instanceof PoolableSocket)) {
			throw new IllegalArgumentException("not supported socket type: " + socket.getClass().getName());
		}
	}

	public void clear() {
		try {
			pool.clear();
		} catch (Exception e) {
			// ignored, never throws
		}
	}

	public void close() {
		try {
			pool.close();
		} catch (Exception e) {
			// ignored, never throws
		}
	}

	public int getNumActive() {
		return pool.getNumActive();
	}

	public int getNumIdle() {
		return pool.getNumIdle();
	}

	protected void makeNewSocket() throws IOException {
		try {
			pool.addObject();
		} catch (RuntimeException e) {
			throw e;
		} catch (IOException e) {
			// from factory.makeObject()
			throw e;
		} catch (Exception e) {
			// ignored, never throws
		}
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public int getMaxActive() {
		return pool.getMaxActive();
	}

	public int getMaxIdle() {
		return pool.getMaxIdle();
	}

	public int getMinIdle() {
		return pool.getMinIdle();
	}

	public long getMaxWait() {
		return pool.getMaxWait();
	}

	public long getMinIdleTime() {
		return pool.getMinEvictableIdleTimeMillis();
	}

	public long getSoftMinIdleTime() {
		return pool.getSoftMinEvictableIdleTimeMillis();
	}

	public int getEvictTestNum() {
		return pool.getNumTestsPerEvictionRun();
	}

	public long getEvictInterval() {
		return pool.getTimeBetweenEvictionRunsMillis();
	}

	public boolean isLifo() {
		return pool.getLifo();
	}

	public boolean isTestOnBorrow() {
		return pool.getTestOnBorrow();
	}

	public boolean isTestOnReturn() {
		return pool.getTestOnReturn();
	}

	public SocketPool.ExhaustAction getExhaustAction() {
		int when = pool.getWhenExhaustedAction();
		switch (when) {
			case GenericObjectPool.WHEN_EXHAUSTED_FAIL:
				return SocketPool.ExhaustAction.FAIL;
			case GenericObjectPool.WHEN_EXHAUSTED_BLOCK:
				return SocketPool.ExhaustAction.BLOCK;
			case GenericObjectPool.WHEN_EXHAUSTED_GROW:
				return SocketPool.ExhaustAction.GROW;
			default:
				throw new IllegalStateException("unknow when exhausted action type: " + when);
		}
	}

	/**
	 * 最可大借出socket数
	 * default is 8
	 * @param maxActive
	 */
	public void setMaxActive(int maxActive) {
		pool.setMaxActive(maxActive);
	}

	/**
	 * 池内最大保持的空闲socket数（超过的socket会被回收）
	 * default is 8
	 * @param maxIdle
	 */
	public void setMaxIdle(int maxIdle) {
		pool.setMaxIdle(maxIdle);
	}

	/**
	 * 池内至少保持的空闲socket数（不会被回收） default is 0
	 * @param minIdle
	 */
	public void setMinIdle(int minIdle) {
		pool.setMinIdle(minIdle);
	}

	/**
	 * 如ExhaustAction值为WHEN_EXHAUSTED_BLOCK，所有socket都被借出时线程等待的时间（超过此时间会抛出异常）
	 * default is -1L, always wait
	 * @param maxWait
	 */
	public void setMaxWait(long maxWait) {
		pool.setMaxWait(maxWait);
	}

	/**
	 * socket空闲时间 空闲超过此时间会自动将socket销毁 default is 1000L * 60L * 30L(30 minutes)
	 * @param minIdleTime
	 */
	public void setMinIdleTime(long minIdleTime) {
		pool.setMinEvictableIdleTimeMillis(minIdleTime);
	}

	/**
	 * socket空闲时间 空闲超过此时间且连接池size大于minIdle会自动将socket销毁 default is -1, never test
	 * @param softMinIdleTime
	 */
	public void setSoftMinIdleTime(long softMinIdleTime) {
		pool.setSoftMinEvictableIdleTimeMillis(softMinIdleTime);
	}

	/**
	 * 逐出检测时每次检查多少个socket，如为负数则取绝对值的倒数*池大小 default is 3
	 * @param num
	 */
	public void setEvictTestNum(int num) {
		pool.setNumTestsPerEvictionRun(num);
	}

	/**
	 * socket逐出检测间隔时间 default is -1L, never test
	 * @param interval
	 */
	public void setEvictInterval(long interval) {
		pool.setTimeBetweenEvictionRunsMillis(interval);
	}

	/**
	 * 后进先出模式（栈模式） default is true
	 * @param lifo
	 */
	public void setLifo(boolean lifo) {
		pool.setLifo(lifo);
	}

	/**
	 * 借出时先测试socket是否可用
	 * @param test
	 */
	public void setTestOnBorrow(boolean test) {
		pool.setTestOnBorrow(test);
	}

	/**
	 * 归还时检测socket是否可用
	 * @param test
	 */
	public void setTestOnReturn(boolean test) {
		pool.setTestOnReturn(test);
	}

	/**
	 * 设置当所有socket都被借出时，再有借出调用时的执行策略 default is BLOCK
	 * @param whenExhausted
	 */
	public void setExhaustAction(SocketPool.ExhaustAction whenExhausted) {
		switch (whenExhausted) {
			// 抛出异常
			case FAIL:
				pool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_FAIL);
				break;
			case BLOCK:
				// 阻塞线程
				pool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_BLOCK);
				break;
			case GROW:
				// 自动创建新socket
				pool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_GROW);
				break;
		}
	}
}
