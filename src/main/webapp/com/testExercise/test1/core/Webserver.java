package main.webapp.com.testExercise.test1.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
    private ServerSocket serverSocket = null;
    private Socket socket = null;

    public Webserver() {
        try {
            //���������ʵ����ָ���˿�
            System.out.println("������ʵ�������ɹ�");
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        try {
            //��������
            System.out.println("��ʼ��������");
            socket = serverSocket.accept();
            //��������
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
