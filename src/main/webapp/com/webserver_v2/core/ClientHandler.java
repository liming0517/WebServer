package main.webapp.com.webserver_v2.core;

import main.webapp.com.webserver_v2.http.HttpRequest;

import java.io.IOException;
import java.net.Socket;

/**
 * 处理客户端请求
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private HttpRequest httpRequest=null;

    public ClientHandler(Socket socket){
        this.socket=socket;
    }

    public void run() {

        try {
            System.out.println("ClientHandler:开始处理请求");
//          InputStream in = socket.getInputStream();
//            int d=0;
//            while((d=in.read())!= -1){
//                System.out.print((char)d);
//            }
            //1,解析请求
           httpRequest =new HttpRequest(socket);

            //2.处理请求

            //3.发送响应
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
