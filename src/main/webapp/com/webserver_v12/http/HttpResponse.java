package main.webapp.com.webserver_v12.http;

import main.webapp.com.webserver_v12.http.HttpContext;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpResponse {
    private Socket socket = null;
    private OutputStream outputStream = null;

    //构造方法
    public HttpResponse(Socket socket) {
        try {
            this.socket = socket;
            this.outputStream = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 状态行相关信息
     */

    /**
     * 状态代码 默认值200
     * 默认值200主要有两个原因：
     * 1,如果不指定默认值，int默认值为0，若我们没有设置状态代码的话，HTTP协议是没有状态码为0的这个情况的，所以不能用0做默认值
     * 2.通常一个请求都能正确处理，回复客户端200是比较多的情况，因此默认值用200可以在大部分响应时不用指定状态代码和对应描述了。
     */
    private int statusCode = 200;
    //状态描述，默认值OK
    private String statusReason = "OK";

    /**
     * 响应头相关信息
     */
private Map<String,String > headers =new HashMap<>();

    /**
     * 响应正文相关信息
     */
    //响应正文的实体文件
    private File entity = null;

    /**
     * 将当前响应对象内容以一个标准HTTP响应格式发送给客户端
     */
    public void flush() {
        try {
            //发送状态行
            this.sendStatusLine();
            //发送响应头
            this.sendHeaders();
            //发送正文
            this.sendContext();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送状态行
    private void sendStatusLine() throws Exception {
        //1.发送状态行

        String line = "HTTP/1.1" + " " + statusCode + " " + statusReason;
        outputStream.write(line.getBytes("ISO8859-1"));
        outputStream.write(13);
        outputStream.write(10);
        System.out.println("开始发送状态行成功"+line);
    }

    //发送响应头
    private void sendHeaders() throws Exception {
        //2.发送响应头
       Set<Map.Entry<String,String>> entrySet= headers.entrySet();
       for(Map.Entry<String,String> header : entrySet ){
           String name =header.getKey();
           String value=header.getValue();
           String line = name +": "+value;
           outputStream.write(line.getBytes("ISO8859-1"));
           outputStream.write(13);
           outputStream.write(10);
       }
        //单独发送CRLF表示响应头发送结束
        outputStream.write(13);
        outputStream.write(10);
    }

    //发送响应正文
    private void sendContext() throws Exception{
        /**
         * 如果实体文件存在，则作为正文发送
         *注：响应与请求一样，可以不含正文
         */
        if (entity !=null) {
            FileInputStream fileInputStream1 = new FileInputStream(entity);
            try{
                int len = 0;
                byte[] bytes = new byte[1024 * 10];
                while ((len = fileInputStream1.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                System.out.println("发送响应正文成功");
            }catch (Exception e){
                throw e;
            }finally {
                outputStream.close();
                fileInputStream1.close();
            }
        }




    }


    //get,set 方法
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public File getEntity() {
        return entity;
    }

    /**
     * 设置响应正文对应的实体文件
     * 设置正文的同时会自动向响应对象中添加说明
     * 响应正文的响应头:Content-Type和Content-Length
     * @param entity
     */
    public void setEntity(File entity) {
        this.entity = entity;
        String fileName =entity.getName();
        //获取后缀名
        String ext= fileName.substring(fileName.indexOf(".")+1);

        //添加响应头
        this.putHeader("Content-Type", HttpContext.getMimeType(ext));
        this.putHeader("Content-Length",entity.length()+"");
    }

    /**
     * 向当前响应对象中添加一个响应头
     * @param name
     * @param value
     */
    public void putHeader(String name,String value){
        this.headers.put(name,value);
    }

}
