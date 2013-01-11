package org.catspaw.cherubim.socketpool;

import java.io.IOException;
import java.net.Socket;

/**
 * socket回调接口
 */
public interface SocketCallback<T> {

	/**
	 * 回调方法
	 * @param socket socket
	 * @return
	 * @throws IOException
	 */
	T execute(Socket socket) throws IOException;
}
