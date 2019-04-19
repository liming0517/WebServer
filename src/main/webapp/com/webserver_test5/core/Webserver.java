package main.webapp.com.webserver_test5.core;
import main.webapp.com.webserver_v10.core.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
    private  ServerSocket server=null;

    public Webserver(){
        try {
            System.out.println("�������������");
            server =new ServerSocket(8088);
            System.out.println("����������ɹ�");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void start(){
        //��ʱֻ����һ�οͻ������ӣ����ڹ���ʵ����Ϻ�������
        while (true){
            try {
                System.out.println("�ȴ��ͻ�������");
                Socket socket= server.accept();
                System.out.println("�ͻ���������");
                //����һ���̴߳���ÿͻ�������
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
