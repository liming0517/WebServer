package main.webapp.com.testExercise.test4.webserver.core;

import main.webapp.com.webserver_v3.http.HttpRequest;

import java.io.*;
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
                File file = new File("./\\src\\main\\webapp\\com\\webserver_v3\\webapps"+path);
                OutputStream outputStream =socket.getOutputStream();
               //�ж�������Դ�Ƿ����
                if(file.exists()){
                    System.out.println("��Դ����");
                    //������Ӧ��
                    String line ="HTTP/1.1 200 OK";
                    outputStream.write(line.getBytes("ISO8859-1"));
                    outputStream.write(13);
                    outputStream.write(10);

                    line="Content-Type: text/html";
                    outputStream.write(line.getBytes("ISO8859-1"));
                    outputStream.write(13);
                    outputStream.write(10);
                    line="Content-Size: "+file.length();
                    outputStream.write(line.getBytes("ISO8859-1"));
                    outputStream.write(13);
                    outputStream.write(10);

                    outputStream.write(13);
                    outputStream.write(10);



                    FileInputStream fileInputStream =new FileInputStream(file);

                }else{
                    System.out.println("404��Դ������");
                }



            //3.������Ӧ
            System.out.println("ClientHandler:������ϣ�");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
