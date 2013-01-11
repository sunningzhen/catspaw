package org.catspaw.cherubim.socketpool;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * socket连接池实现类，用这个类生成的socket池，
 * 会启动一个定时任务，不断地向空闲的socket连接发送心跳包，以便保持socket的长时间连接
 */
public class KeepingSocketPool extends DefaultSocketPool {

	/**记录日记对象 */
	private static final Logger	logger	= LoggerFactory
												.getLogger(KeepingSocketPool.class);
	/**向空闲Socket发心跳包 */
	private SocketKeeper		socketKeeper;
	/**发送心跳包的间隔时间 */
	private long				pulseDelay;
	/**socket最大空闲时间*/
	private long				maxStandbyTime;
	/**计时器 */
	private Timer				pulseTimer;
	/**由Timer 安排为一次执行或重复执行的任务 */
	private TimerTask			pulseTask;

	/**
	 * 构造函数
	 * @param host socket所连接的目的地址
	 * @param port socket所连接的目的端口
	 * @param config socket详细参数
	 * @param socketKeeper 心跳包发送者
	 * @param pulseDelay 发送心跳包的间隔时间
	 */
	public KeepingSocketPool(String host, int port, SocketConfig config,
			SocketKeeper socketKeeper, long pulseDelay) {
		super(host, port, config);
		init(socketKeeper, pulseDelay, pulseDelay / 2);
	}
	
	/**
	 * 构造函数
	 * @param host socket所连接的目的地址
	 * @param port socket所连接的目的端口
	 * @param config socket详细参数
	 * @param prefillSize 初始化socket数量
	 * @param socketKeeper 心跳包发送者
	 * @param pulseDelay 发送心跳包的间隔时间
	 */
	public KeepingSocketPool(String host, int port, SocketConfig config,
			int prefillSize, SocketKeeper socketKeeper, long pulseDelay) {
		super(host, port, config, prefillSize);
		init(socketKeeper, pulseDelay, pulseDelay / 2);
	}

	/**
	 * 构造函数
	 * @param host socket所连接的目的地址
	 * @param port socket所连接的目的端口
	 * @param socketKeeper 心跳包发送者
	 * @param pulseDelay 发送心跳包的间隔时间
	 */
	public KeepingSocketPool(String host, int port, SocketKeeper socketKeeper,
			long pulseDelay) {
		super(host, port);
		init(socketKeeper, pulseDelay, pulseDelay / 2);
	}
	
	private void init(SocketKeeper keeper, long pulseDelay,
			long maxStandbyTime) {
		this.socketKeeper = keeper;
		this.pulseDelay = pulseDelay;
		this.maxStandbyTime = pulseDelay / 2;
		restartPulseTask(pulseDelay);
	}

	/**
	 * 重新开始发送心跳包任务
	 * @param pulseDelay 发送心跳包的间隔时间
	 */
	private void restartPulseTask(long pulseDelay) {
		if (pulseTask != null) {
			pulseTask.run();
			pulseTask.cancel();
		}
		pulseTask = new TimerTask() {

			public void run() {
				sendPulse();
			}
		};
		pulseTimer = new Timer();
		pulseTimer.schedule(pulseTask, 0, pulseDelay);
	}

	/**
	 * 发送心跳包
	 * 如果发送过程失败，表示该socket已断开，或有问题
	 * 然后关闭该socket
	 */
	private void sendPulse() {
		logger.info("start send pulse");
		int num;
		synchronized (this) {
			num = getNumIdle();
			if (isLifo()) {
				num += getNumActive();
			}
		}
		logger.debug(num + " sockets will send pulse");
		for (int i = 0; i < num; i++) {
			PoolableSocket socket;
			try {
				socket = (PoolableSocket) borrowSocket();
			} catch (InterruptedException e) {
				return;
			} catch (NoSuchElementException e) {
				return;
			}
			//当前时间和上次归还时间的偏移量
			long offset = System.currentTimeMillis() - socket.getLastReturnTime();
			//偏移量*2大于发送心跳包的间隔时
			if (offset * 2 >= maxStandbyTime) {
				try {
					socketKeeper.sendPulse(socket);
					logger.debug("send a pulse to " + socket.toString());
				} catch (IOException e) {
					logger.error("send pulse error, invalidate the socket",
									e);
					invalidateSocket(socket);
				} finally {
					returnSocket(socket);
				}
			} else {
				returnSocket(socket);
			}
		}
		logger.info("send pulse finish");
	}

	/**
	 * 获取Socket守护对象
	 * @return a SocketKeeper
	 */
	public SocketKeeper getSocketKeeper() {
		return socketKeeper;
	}

	/**
	 * 获取发送心跳包的间隔时间
	 * @return 
	 */
	public long getPulseDelay() {
		return pulseDelay;
	}

	/**
	 * 设置发送心跳包的间隔时间
	 * @param pulseDelay 间隔时间
	 */
	public void setPulseDelay(long pulseDelay) {
		this.pulseDelay = pulseDelay;
		restartPulseTask(pulseDelay);
	}
	
	public long getMaxStandbyTime() {
		return maxStandbyTime;
	}
	
	public void setMaxStandbyTime(long maxStandbyTime) {
		this.maxStandbyTime = maxStandbyTime;
	}
}
