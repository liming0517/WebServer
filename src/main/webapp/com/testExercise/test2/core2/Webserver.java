package main.webapp.com.testExercise.test2.core2;

import com.sun.security.ntlm.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * ʹ��BufferedReaderʵ��
 */
public class Webserver {
    private ServerSocket serverSocket =null;

    public Webserver(){
        try {
            serverSocket = new ServerSocket(10001);
            System.out.println("�ȴ��ͻ�������");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){
        //��ʼ��������
        try {
            //�ȴ��ͻ�������
            Socket socket =serverSocket.accept();
            ClientHandle2 clientHandle2=new ClientHandle2(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Webserver webserver =new Webserver();
        webserver.start();
    }
}


