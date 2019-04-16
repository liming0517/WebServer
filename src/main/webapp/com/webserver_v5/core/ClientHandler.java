package main.webapp.com.webserver_v5.core;

import main.webapp.com.webserver_v5.http.HttpRequest;

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


        System.out.println("ClientHandler:��ʼ��������");
//          InputStream in = socket.getInputStream();
//            int d=0;
//            while((d=in.read())!= -1){
//                System.out.print((char)d);
//            }
        //1,��������
        httpRequest = new HttpRequest(socket);
        /**
         * 2.��������
         * 1.ͨ��request��ȡurl��������֪�û��������Դ��·��
         * 2.��webappsĿ¼�¸��ݸ���Դ·���ҵ���Ӧ��Դ
         * 3.�жϸ���Դ�Ƿ���ʵ����
         * 4.��������Ӧ����Դ
         * 5.����������Ӧ404
         */
        String path = httpRequest.getUrl();
        //ͨ��·���ҵ�webappsĿ¼�¶�Ӧ��Դ
        try {
            File file = new File("./\\src\\main\\webapp\\com\\webserver_v5\\webapps" + path);
            //���������
            OutputStream outputStream = socket.getOutputStream();
            //�ж�������Դ�Ƿ����
            if (file.exists()) {
                System.out.println("��Դ����");
                //1.����״̬��
                String line = "HTTP/1.1 200 OK";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);

                //2.������Ӧͷ
                line = "Content-Type: text/html";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);

                line = "Content-Length: " + file.length();
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);
//��������CRLF��ʾ��Ӧͷ���ͽ���
                outputStream.write(13);
                outputStream.write(10);
                FileInputStream fileInputStream1 = new FileInputStream(file);
                int len = 0;
                byte[] bytes = new byte[1024*10];
                while ((len = fileInputStream1.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            } else {
                System.out.println("404��Դ������");
                 File file404 = new File("./\\src\\main\\webapp\\com\\webserver_v5\\webapps\\myweb\\404.html");
                //1.����״̬��
                String line = "HTTP/1.1 404 NOT FOUND";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);
                //2.������Ӧͷ
                line = "Content-Type: text/html";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);

                outputStream.write(13);
                outputStream.write(10);

                //����ҳ��
                int len = 0;
                byte[] bytes = new byte[1024];
                FileInputStream fileInputStream2 = new FileInputStream(file404);
                System.out.println("file404.exists()= " + file404.exists());
                while ((len = fileInputStream2.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            }


            //3.������Ӧ
            System.out.println("ClientHandler:������ϣ�");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
