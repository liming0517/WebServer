package main.webapp.com.webserver_test4.servlet;

import main.webapp.com.webserver_test4.http.HttpRequest;
import main.webapp.com.webserver_test4.http.HttpResponse;

import java.io.File;
import java.io.RandomAccessFile;

public class LoginServlet {

    private String name;
    private String password;

    //�����¼����
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("���봦���¼ҵ�����󣿣���");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("user.dat", "r")) {
            for (int i = 0; i < randomAccessFile.length() / 100; i++) {
                randomAccessFile.seek(i * 100);
                byte[] bytes = new byte[32];
                randomAccessFile.read(bytes);
                name = new String(bytes).trim();
                System.out.println("������������:" + name);
                randomAccessFile.read(bytes);
                password = new String(bytes).trim();
                System.out.println("�����������ǣ�" + password);
                if(name.equals(httpRequest.getParameter("username")) &&password.equals(httpRequest.getParameter("password"))){
                    System.out.println("�˺��Ѵ���");
                    File fileLoginSuccess = new File("./\\src\\main\\webapp\\com\\webserver_test4\\webapps\\myweb\\login_success.html");
                    httpResponse.setEntity(fileLoginSuccess);
                    return;
                }
            }
            System.out.println("�˺Ų�����");
            File fileFaillogin =new File("./\\src\\main\\webapp\\com\\webserver_test4\\webapps\\myweb\\login_fail.html");
            httpResponse.setEntity(fileFaillogin);
            return;



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}