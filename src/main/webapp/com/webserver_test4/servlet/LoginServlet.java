package main.webapp.com.webserver_test4.servlet;

import main.webapp.com.webserver_test4.http.HttpRequest;
import main.webapp.com.webserver_test4.http.HttpResponse;

import java.io.File;
import java.io.RandomAccessFile;

public class LoginServlet {

    private String name;
    private String password;

    //处理登录请求
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("进入处理登录业务请求？？？");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("user.dat", "r")) {
            for (int i = 0; i < randomAccessFile.length() / 100; i++) {
                randomAccessFile.seek(i * 100);
                byte[] bytes = new byte[32];
                randomAccessFile.read(bytes);
                name = new String(bytes).trim();
                System.out.println("读到的内容是:" + name);
                randomAccessFile.read(bytes);
                password = new String(bytes).trim();
                System.out.println("读到的密码是：" + password);
                if(name.equals(httpRequest.getParameter("username")) &&password.equals(httpRequest.getParameter("password"))){
                    System.out.println("账号已存在");
                    File fileLoginSuccess = new File("./\\src\\main\\webapp\\com\\webserver_test4\\webapps\\myweb\\login_success.html");
                    httpResponse.setEntity(fileLoginSuccess);
                    return;
                }
            }
            System.out.println("账号不存在");
            File fileFaillogin =new File("./\\src\\main\\webapp\\com\\webserver_test4\\webapps\\myweb\\login_fail.html");
            httpResponse.setEntity(fileFaillogin);
            return;



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}