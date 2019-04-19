package main.webapp.com.webserver_test2.webserver_test3.servlet;

import main.webapp.com.webserver_v11.http.HttpResponse;
import main.webapp.com.webserver_v11.http.HttpRequest;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 处理注册业务
 */
public class RegServlet {
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("RegServlet：开始处理注册...");
/**
 * 1:通过request获取用户在页面上输入的注册信息
 * 2.将该用户的注册信息写入到文件user.dat中
 * 3.设置response响应注册结果页面
 */
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        String nickname = httpRequest.getParameter("nickname");
        int age = Integer.parseInt(httpRequest.getParameter("age"));
//2.
        try(RandomAccessFile raf = new RandomAccessFile("user.dat","rw")){

            //现将指针移动到文件末尾以便追加一条新记录
            raf.seek(raf.length());
            //写用户名
            byte[] data = username.getBytes("UTF-8");
            data = Arrays.copyOf(data,32);//扩容到32字节
            //将32字节一次性写出
            raf.write(data);

            //写密码
            data = password.getBytes("UTF-8");
            data = Arrays.copyOf(data,32);
            raf.write(data);

            //写昵称
            data = nickname.getBytes("UTF-8");
            data = Arrays.copyOf(data,32);
            raf.write(data);

            //写年龄
            raf.writeInt(age);
        }catch(Exception e){
            e.printStackTrace();
        }
      //3.
        File successPage= new File("./\\src\\main\\webapp\\com\\webserver_v11\\webapps\\myweb\\reg_success.html");
        httpResponse.setEntity(successPage);
        System.out.println("username=" + username);
        System.out.println("password=" + password);
        System.out.println("nickname=" + nickname);
        System.out.println("age=" + age);
        System.out.println("RegServlet：处理注册业务完毕");
    }
}

