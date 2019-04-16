package main.webapp.com.webserver_v3.core;

import main.webapp.com.webserver_v3.http.HttpRequest;

import java.io.File;
import java.io.IOException;
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
                File file = new File("./\\src\\main\\webapp\\com\\webserver_v3\\webapps"+path);
                System.out.println("file.getCanonicalFile()="+file.getCanonicalFile());
                System.out.println("file.getPath()="+file.getPath());
                System.out.println("file.getAbsoluteFile()="+file.getAbsoluteFile());
                System.out.println(file.exists());

               //判断请求资源是否存在
                if(file.exists()){
                    System.out.println("资源存在");
                }else{
                    System.out.println("404资源不存在");
                }



            //3.发送响应
            System.out.println("ClientHandler:处理完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
