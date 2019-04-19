package main.webapp.com.webserver_v13.servlet;

import main.webapp.com.webserver_v13.http.HttpRequest;
import main.webapp.com.webserver_v13.http.HttpResponse;

import java.io.File;
import java.io.RandomAccessFile;

public class LoginServlet extends HttpServlet{

    private String name;
    private String password;

    //处理登录请求
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("进入处理登录业务请求？？？");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("user.dat", "r")) {
            for (int i = 0; i < randomAccessFile.length() / 100; i++) {
                randomAccessFile.seek(i * 100);
                byte[] data = new byte[32];
                randomAccessFile.read(data);
                name = new String(data).trim();
                System.out.println("读到的内容是:" + name);
                if(name.equals(httpRequest.getParameter("username"))){
//                    randomAccessFile.read(data);
//                    String pwd= new String(data,"UTF-8").trim();
                    randomAccessFile.read(data);
                    password = new String(data).trim();
                    System.out.println("读到的密码是：" + password);
                    if(password.equals(httpRequest.getParameter("password"))){
                        forward("\\myweb\\login_success.html",httpRequest,httpResponse);
                        return;
                    }
                }
            }
            System.out.println("账号不存在");
            //跳转失败
                    forward("\\myweb\\login_fail.html",httpRequest,httpResponse);
//            File fileFaillogin =new File("");
//            httpResponse.setEntity(fileFaillogin);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}