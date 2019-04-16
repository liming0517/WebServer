package main.webapp.com.webserver_v1.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 处理客户端请求
 */
public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket=socket;
    }

    public void run() {

        try {
            System.out.println("ClientHandler:开始处理请求");
            InputStream in = socket.getInputStream();
            int d=0;
            while((d=in.read())!= -1){
                System.out.print((char)d);
            }
            System.out.println("ClientHandler:处理完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
