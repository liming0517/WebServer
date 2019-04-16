package main.webapp.com.webserver_v2.core;

import main.webapp.com.webserver_v2.http.HttpRequest;

import java.io.IOException;
import java.net.Socket;

/**
 * ����ͻ�������
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private HttpRequest httpRequest=null;

    public ClientHandler(Socket socket){
        this.socket=socket;
    }

    public void run() {

        try {
            System.out.println("ClientHandler:��ʼ��������");
//          InputStream in = socket.getInputStream();
//            int d=0;
//            while((d=in.read())!= -1){
//                System.out.print((char)d);
//            }
            //1,��������
           httpRequest =new HttpRequest(socket);

            //2.��������

            //3.������Ӧ
            System.out.println("ClientHandler:������ϣ�");
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
