package main.webapp.com.testExercise.test1.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
    private ServerSocket serverSocket = null;
    private Socket socket = null;

    public Webserver() {
        try {
            //创建服务端实例，指定端口
            System.out.println("服务器实例创建成功");
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        try {
            //接收请求
            System.out.println("开始接收请求");
            socket = serverSocket.accept();
            //解析请求
           ClientHandler clientHandler =new ClientHandler(socket);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Webserver webserver = new Webserver();
        webserver.start();
    }
}
