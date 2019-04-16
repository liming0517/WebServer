package main.webapp.com.webserver_test1.core;

import main.webapp.com.webserver_test1.http.HttpResponse;
import main.webapp.com.webserver_test1.http.HttpRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 处理客户端请求
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private HttpRequest httpRequest = null;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
        System.out.println("ClientHandler:开始处理请求");
        //1,解析请求
        httpRequest = new HttpRequest(socket);

        // 2.处理请求
        String path = httpRequest.getUrl();
        //通过路径找到webapps目录下对应资源

            File file = new File("./\\src\\main\\webapp\\com\\webserver_test1\\webapps" + path);
            File file404 = new File("./\\src\\main\\webapp\\com\\webserver_test1\\webapps\\myweb\\404.html");
            HttpResponse httpResponse = new HttpResponse(socket);
            //判断请求资源是否存在
            if (file.exists()) {
                System.out.println("资源存在");
                httpResponse.setFile(file);
            } else {
                System.out.println("404资源不存在");
                httpResponse.setResponseLineCode(404);
                httpResponse.setResponseLineContext("NOT FOUND");
                httpResponse.setFile(file404);

            }


            //3.发送响应
            httpResponse.response();
            System.out.println("ClientHandler:处理完毕！");

        }catch(ExceptionHttpnullException e){
            System.out.println("浏览器发送了一个空的请求，已忽略....");
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


    }
}
