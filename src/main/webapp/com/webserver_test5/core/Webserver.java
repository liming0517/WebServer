package main.webapp.com.webserver_test5.core;
import main.webapp.com.webserver_v10.core.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
    private  ServerSocket server=null;

    public Webserver(){
        try {
            System.out.println("正在启动服务端");
            server =new ServerSocket(8088);
            System.out.println("服务端启动成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void start(){
        //暂时只接收一次客户端连接，后期功能实现完毕后再启用
        while (true){
            try {
                System.out.println("等待客户端连接");
                Socket socket= server.accept();
                System.out.println("客户端连接了");
                //启动一个线程处理该客户端请求
                ClientHandler handel =new ClientHandler(socket);
                Thread tr= new Thread(handel);
                tr.start();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Webserver wbs =new Webserver();
        wbs.start();
    }

}
