package main.webapp.com.webserver_v13.servlet;

import main.webapp.com.webserver_v13.http.HttpRequest;
import main.webapp.com.webserver_v13.http.HttpResponse;

import java.io.File;
import java.io.RandomAccessFile;

public class LoginServlet extends HttpServlet{

    private String name;
    private String password;

    //�����¼����
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("���봦���¼ҵ�����󣿣���");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("user.dat", "r")) {
            for (int i = 0; i < randomAccessFile.length() / 100; i++) {
                randomAccessFile.seek(i * 100);
                byte[] data = new byte[32];
                randomAccessFile.read(data);
                name = new String(data).trim();
                System.out.println("������������:" + name);
                if(name.equals(httpRequest.getParameter("username"))){
//                    randomAccessFile.read(data);
//                    String pwd= new String(data,"UTF-8").trim();
                    randomAccessFile.read(data);
                    password = new String(data).trim();
                    System.out.println("�����������ǣ�" + password);
                    if(password.equals(httpRequest.getParameter("password"))){
                        forward("\\myweb\\login_success.html",httpRequest,httpResponse);
                        return;
                    }
                }
            }
            System.out.println("�˺Ų�����");
            //��תʧ��
                    forward("\\myweb\\login_fail.html",httpRequest,httpResponse);
//            File fileFaillogin =new File("");
//            httpResponse.setEntity(fileFaillogin);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}