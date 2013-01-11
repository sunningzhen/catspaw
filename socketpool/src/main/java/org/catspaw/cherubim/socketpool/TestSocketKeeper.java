package org.catspaw.cherubim.socketpool;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * SocketKeeper的默认实现
 */
public class TestSocketKeeper implements SocketKeeper {

	/**
	 * 发送心跳包
	 * @param socket 发送心跳包的Socket
	 */
	public void sendPulse(Socket socket) throws IOException {
		OutputStream os = socket.getOutputStream();
		os.write(0);
		os.flush();
	}
}
