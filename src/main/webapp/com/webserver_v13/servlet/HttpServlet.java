package main.webapp.com.webserver_v13.servlet;

import main.webapp.com.webserver_v13.http.HttpRequest;
import main.webapp.com.webserver_v13.http.HttpResponse;

import java.io.File;

public abstract class HttpServlet {
    public abstract void service(HttpRequest request, HttpResponse response);

    /**
     * 跳转指定路径(Tomcat的该方法实际上是通过HttpRequest获取的转发器dispatcher对应的方法)
     * @param path
     * @param request
     * @param httpResponse
     */
    public void forward(String path, HttpRequest request, HttpResponse httpResponse) {
      File file =new File("./\\src\\main\\webapp\\com\\webserver_v13\\webapps"+path);
      httpResponse.setEntity(file);
    }

}
