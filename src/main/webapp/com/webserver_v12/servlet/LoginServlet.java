package main.webapp.com.webserver_v12.servlet;

import main.webapp.com.webserver_v12.http.HttpRequest;
import main.webapp.com.webserver_v12.http.HttpResponse;

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
                byte[] data = new byte[32];
                randomAccessFile.read(data);
                name = new String(data).trim();
                System.out.println("������������:" + name);
                randomAccessFile.read(data);
                password = new String(data).trim();
                System.out.println("�����������ǣ�" + password);
                if(name.equals(name)){
                    randomAccessFile.read(data);
                    String pwd= new String(data,"UTF-8").trim();
                    if(pwd.equals(password)){
                        File file=new File("./\\src\\main\\webapp\\com\\webserver_test4\\webapps\\myweb\\login_success.html");
                        httpResponse.setEntity(file);
                        return;
                    }
                }
            }
            System.out.println("�˺Ų�����");
            File fileFaillogin =new File("./\\src\\main\\webapp\\com\\webserver_test4\\webapps\\myweb\\login_fail.html");
            httpResponse.setEntity(fileFaillogin);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}