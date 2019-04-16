package main.webapp.com.webserver_v7.core;
import main.webapp.com.webserver_v7.http.HttpRequest;
import main.webapp.com.webserver_v7.http.HttpResponse;

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
        try {
            httpRequest = new HttpRequest(socket);

        /**
         * 2.��������
         * 1.ͨ��request��ȡurl��������֪�û��������Դ��·��
         * 2.��webappsĿ¼�¸��ݸ���Դ·���ҵ���Ӧ��Դ
         * 3.�жϸ���Դ�Ƿ���ʵ����
         * 4.��������Ӧ����Դ
         * 5.����������Ӧ404
         */
        HttpResponse httpReponse = new HttpResponse(socket);
        String path = httpRequest.getUrl();
        //ͨ��·���ҵ�webappsĿ¼�¶�Ӧ��Դ

            File file = new File("./\\src\\main\\webapp\\com\\webserver_v5\\webapps" + path);
            if (file.exists()) {
                //��Ҫ��Ӧ����Դ���õ�response��entity������
                httpReponse.setEntity(file);
            } else {
                System.out.println("404��Դ������");
                File file404 = new File("./\\src\\main\\webapp\\com\\webserver_v7\\webapps\\myweb\\404.html");
                //����״̬�������
                httpReponse.setStatusCode(404);
                httpReponse.setStatusReason("NOT FOUND");
                //������Ӧ����404ҳ��
                httpReponse.setEntity(file404);
            }


            //3.������Ӧ
            httpReponse.flush();
            System.out.println("ClientHandler:������ϣ�");


        }
        catch(EmptyRequestException e){
            System.out.println("����������˿��������账��");
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
        System.out.println("socket.isClosed()= "+socket.isClosed());

    }
}
