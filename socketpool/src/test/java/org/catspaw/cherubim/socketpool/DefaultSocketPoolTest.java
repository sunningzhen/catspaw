package org.catspaw.cherubim.socketpool;

import java.io.PrintWriter;
import java.net.Socket;

public class DefaultSocketPoolTest {
	
	public static void main(String[] args) {
		try {
			SocketPool pool = new DefaultSocketPool("localhost", 8821);
			Socket socket = pool.borrowSocket();
			System.out.println("socket size = " + pool.getNumActive());
			System.out.println("socket size = " + pool.getNumIdle());
			Socket socket1 = pool.borrowSocket();
			System.out.println("socket size = " + pool.getNumActive());
			System.out.println("socket size = " + pool.getNumIdle());
			
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			os.println("test");
			// 将从系统标准输入读入的字符串输出到Server
			os.flush();
			os.close(); // 关闭Socket输出流
			socket.close(); // 关闭Socket
			System.out.println("socket size = " + pool.getNumActive());
			System.out.println("socket size = " + pool.getNumIdle());
			pool.returnSocket(socket);
			System.out.println("socket size = " + pool.getNumActive());
			System.out.println("socket size = " + pool.getNumIdle());
			
		} catch (Exception e) {
			System.out.println("Error" + e); // 出错，则打印出错信息
		}
	}
	
}
