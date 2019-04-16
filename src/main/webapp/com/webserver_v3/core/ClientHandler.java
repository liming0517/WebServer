package main.webapp.com.webserver_v3.core;

import main.webapp.com.webserver_v3.http.HttpRequest;

import java.io.File;
import java.io.IOException;
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
                System.out.println("file.getCanonicalFile()="+file.getCanonicalFile());
                System.out.println("file.getPath()="+file.getPath());
                System.out.println("file.getAbsoluteFile()="+file.getAbsoluteFile());
                System.out.println(file.exists());

               //�ж�������Դ�Ƿ����
                if(file.exists()){
                    System.out.println("��Դ����");
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
