package main.webapp.com.webserver_v5.core;

import main.webapp.com.webserver_v5.http.HttpRequest;

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


        System.out.println("ClientHandler:开始处理请求");
//          InputStream in = socket.getInputStream();
//            int d=0;
//            while((d=in.read())!= -1){
//                System.out.print((char)d);
//            }
        //1,解析请求
        httpRequest = new HttpRequest(socket);
        /**
         * 2.处理请求
         * 1.通过request获取url，用来得知用户请求的资源的路径
         * 2.从webapps目录下根据该资源路径找到对应资源
         * 3.判断该资源是否真实存在
         * 4.存在则响应该资源
         * 5.不存在则响应404
         */
        String path = httpRequest.getUrl();
        //通过路径找到webapps目录下对应资源
        try {
            File file = new File("./\\src\\main\\webapp\\com\\webserver_v5\\webapps" + path);
            //创建输出流
            OutputStream outputStream = socket.getOutputStream();
            //判断请求资源是否存在
            if (file.exists()) {
                System.out.println("资源存在");
                //1.发送状态行
                String line = "HTTP/1.1 200 OK";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);

                //2.发送响应头
                line = "Content-Type: text/html";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);

                line = "Content-Length: " + file.length();
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);
//单独发送CRLF表示响应头发送结束
                outputStream.write(13);
                outputStream.write(10);
                FileInputStream fileInputStream1 = new FileInputStream(file);
                int len = 0;
                byte[] bytes = new byte[1024*10];
                while ((len = fileInputStream1.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            } else {
                System.out.println("404资源不存在");
                 File file404 = new File("./\\src\\main\\webapp\\com\\webserver_v5\\webapps\\myweb\\404.html");
                //1.发送状态行
                String line = "HTTP/1.1 404 NOT FOUND";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);
                //2.发送响应头
                line = "Content-Type: text/html";
                outputStream.write(line.getBytes("ISO8859-1"));
                outputStream.write(13);
                outputStream.write(10);

                outputStream.write(13);
                outputStream.write(10);

                //发送页面
                int len = 0;
                byte[] bytes = new byte[1024];
                FileInputStream fileInputStream2 = new FileInputStream(file404);
                System.out.println("file404.exists()= " + file404.exists());
                while ((len = fileInputStream2.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            }


            //3.发送响应
            System.out.println("ClientHandler:处理完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
