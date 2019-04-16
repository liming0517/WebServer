package main.webapp.com.webserver_test1.core;

import main.webapp.com.webserver_test1.http.HttpResponse;
import main.webapp.com.webserver_test1.http.HttpRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * ����ͻ�������
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private HttpRequest httpRequest = null;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
        System.out.println("ClientHandler:��ʼ��������");
        //1,��������
        httpRequest = new HttpRequest(socket);

        // 2.��������
        String path = httpRequest.getUrl();
        //ͨ��·���ҵ�webappsĿ¼�¶�Ӧ��Դ

            File file = new File("./\\src\\main\\webapp\\com\\webserver_test1\\webapps" + path);
            File file404 = new File("./\\src\\main\\webapp\\com\\webserver_test1\\webapps\\myweb\\404.html");
            HttpResponse httpResponse = new HttpResponse(socket);
            //�ж�������Դ�Ƿ����
            if (file.exists()) {
                System.out.println("��Դ����");
                httpResponse.setFile(file);
            } else {
                System.out.println("404��Դ������");
                httpResponse.setResponseLineCode(404);
                httpResponse.setResponseLineContext("NOT FOUND");
                httpResponse.setFile(file404);

            }


            //3.������Ӧ
            httpResponse.response();
            System.out.println("ClientHandler:������ϣ�");

        }catch(ExceptionHttpnullException e){
            System.out.println("�����������һ���յ������Ѻ���....");
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
