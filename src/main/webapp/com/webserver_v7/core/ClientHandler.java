package main.webapp.com.webserver_v7.core;
import main.webapp.com.webserver_v7.http.HttpRequest;
import main.webapp.com.webserver_v7.http.HttpResponse;

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
        try {
            httpRequest = new HttpRequest(socket);

        /**
         * 2.处理请求
         * 1.通过request获取url，用来得知用户请求的资源的路径
         * 2.从webapps目录下根据该资源路径找到对应资源
         * 3.判断该资源是否真实存在
         * 4.存在则响应该资源
         * 5.不存在则响应404
         */
        HttpResponse httpReponse = new HttpResponse(socket);
        String path = httpRequest.getUrl();
        //通过路径找到webapps目录下对应资源

            File file = new File("./\\src\\main\\webapp\\com\\webserver_v5\\webapps" + path);
            if (file.exists()) {
                //将要响应的资源设置到response的entity属性上
                httpReponse.setEntity(file);
            } else {
                System.out.println("404资源不存在");
                File file404 = new File("./\\src\\main\\webapp\\com\\webserver_v7\\webapps\\myweb\\404.html");
                //设置状态码和描述
                httpReponse.setStatusCode(404);
                httpReponse.setStatusReason("NOT FOUND");
                //设置响应正文404页面
                httpReponse.setEntity(file404);
            }


            //3.发送响应
            httpReponse.flush();
            System.out.println("ClientHandler:处理完毕！");


        }
        catch(EmptyRequestException e){
            System.out.println("浏览器发送了空请求，无需处理");
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
        System.out.println("socket.isClosed()= "+socket.isClosed());

    }
}
