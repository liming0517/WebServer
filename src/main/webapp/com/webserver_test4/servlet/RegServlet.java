package main.webapp.com.webserver_test4.servlet;

import main.webapp.com.webserver_test4.http.HttpResponse;
import main.webapp.com.webserver_test4.http.HttpRequest;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 处理注册业务
 */
public class RegServlet {

    String namePrint;
    String passwordPrint;
    RandomAccessFile raf = null;

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

        try {
            raf = new RandomAccessFile("user.dat", "rw");
/**
 * 首先读取user.dat文件中的每个用户信息比对用户名与当前注册用户的名字是否一致，若一致则说明该用户已经被使用，
 * 这时设置response响应用户提示页面：该用户已存在，否则执行下面原有的注册操作。
 *
 * 用户提示页面：reg_fail.html
 */
            for (int i = 0; i < raf.length() / 100; i++) {
                raf.seek(i * 100);
                byte[] data = new byte[32];
                raf.read(data);
                namePrint = new String(data, "UTF-8").trim();
                raf.read(data);
                System.out.println("名字是：-----------------------------" + namePrint);
                passwordPrint = new String(data, "UTF-8").trim();
                System.out.println("密码是：-----------------------------" + passwordPrint);
            }

            if (namePrint.equals(username)) {
                //已注册用户
                File failPage = new File("./\\src\\main\\webapp\\com\\webserver_test4\\webapps\\myweb\\reg_fail.html");
                httpResponse.setEntity(failPage);
                return;
            }
            //现将指针移动到文件末尾以便追加一条新记录
            raf.seek(raf.length());
            //写用户名
            byte[] data = username.getBytes("UTF-8");
            data = Arrays.copyOf(data, 32);//扩容到32字节

            //将32字节一次性写出
            raf.write(data);
            //写密码
            data = password.getBytes("UTF-8");
            data = Arrays.copyOf(data, 32);
            raf.write(data);
            //写昵称
            data = nickname.getBytes("UTF-8");
            data = Arrays.copyOf(data, 32);
            raf.write(data);

            //写年龄
            raf.writeInt(age);
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //3.
        File successPage = new File("./\\src\\main\\webapp\\com\\webserver_v11\\webapps\\myweb\\reg_success.html");
        httpResponse.setEntity(successPage);
        System.out.println("username=" + username);
        System.out.println("password=" + password);
        System.out.println("nickname=" + nickname);
        System.out.println("age=" + age);
        System.out.println("RegServlet：处理注册业务完毕");
    }

    //查询系统是否已经存在该账号
    public boolean queryIsReg(String query) {
        boolean isQuery = false;
        System.out.println(namePrint+passwordPrint+"该账号已经存在");
        if (query.equals(namePrint) && query.equals(passwordPrint)) {
            isQuery = true;
        }
        return isQuery;
    }
}
