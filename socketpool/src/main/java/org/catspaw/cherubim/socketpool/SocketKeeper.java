/**
 * Copyright (C), 2011, 中国联通集团系统集成有限公司.
 * All right reserved.
 */
package org.catspaw.cherubim.socketpool;

import java.io.IOException;
import java.net.Socket;

/**
 * 监护Socket，保持长时间连接。
 */
public interface SocketKeeper {
	
	/**
	 * 发送心跳包，用于保持长时间连接
	 * @param socket 需要发心跳包的Socket连接
	 * @throws IOException 读写socket流出错
	 */
	void sendPulse(Socket socket) throws IOException;
}
