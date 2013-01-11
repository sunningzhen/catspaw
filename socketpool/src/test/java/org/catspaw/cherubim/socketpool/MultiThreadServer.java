package org.catspaw.cherubim.socketpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {
	private int port=8821;
    private ServerSocket serverSocket;
    
    public MultiThreadServer() throws IOException{
        serverSocket=new ServerSocket(port);
        //Runtime的availableProcessor()方法返回当前系统的CPU数目.
        System.out.println("服务器启动");
    }
    
    public void service(){
        while(true){
            Socket socket=null;
            try {
                //接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
                socket=serverSocket.accept();
                
                BufferedReader br=getReader(socket);
                PrintWriter pw=getWriter(socket);
                String msg=null;
                while((msg=br.readLine())!=null){
                    pw.println(msg);
                    if(msg.equals("bye"))
                        break;
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut=socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }
    
    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn=socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
    
    public static void main(String[] args) throws IOException {
        new MultiThreadServer().service();
    }
}
