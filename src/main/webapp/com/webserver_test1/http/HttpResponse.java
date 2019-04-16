package main.webapp.com.webserver_test1.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private Socket socket = null;
    private OutputStream outputStream = null;
    private File file = null;
    private int responseLineCode = 200;
    private String responseLineContext = "OK";

    public HttpResponse(Socket socket) {
        try {
            this.socket = socket;
            this.outputStream = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //������Ӧ
    public void response() {
        try {
            //1.����״̬��
            this.responseLine();
            //2.������Ӧͷ
            this.responseHeader();
            //3.������Ӧ����
            this.responseContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //��Ӧ״̬��
    public void responseLine() throws Exception {
        //1.����״̬��
        String line = "HTTP/1.1" + " " + this.responseLineCode + " " + this.responseLineContext;
        System.out.println("----------------------line=" + line);
        outputStream.write(line.getBytes("ISO8859-1"));
        outputStream.write(13);
        outputStream.write(10);
    }

    //������Ӧͷ
    public void responseHeader() throws Exception {

        String key =file.getName().substring(file.getName().indexOf(".")+1);
        String line = "Content-Type: "+HttpContext.httpContext(key);
        System.out.println("HttpContext.httpContext(key)======"+HttpContext.httpContext(key));


        outputStream.write(line.getBytes("ISO8859-1"));
        outputStream.write(13);
        outputStream.write(10);

        line = "Content-Length: " + file.length();
        outputStream.write(line.getBytes("ISO8859-1"));
        outputStream.write(13);
        outputStream.write(10);
//��������CRLF��ʾ��Ӧͷ���ͽ���
        outputStream.write(13);
        outputStream.write(10);
    }

    //��Ӧ����
    public void responseContext() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try{
            System.out.println("��ȡ���ļ������ǣ�" + file.getName());
            int len = 0;
            byte[] bytes = new byte[1024 * 10];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        }catch(Exception e){
            throw e;
        }finally {
            outputStream.close();
            fileInputStream.close();

        }
    }

    //Get Set����
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getResponseLineCode() {
        return responseLineCode;
    }

    public void setResponseLineCode(int responseLineCode) {
        this.responseLineCode = responseLineCode;
    }

    public String getResponseLineContext() {
        return responseLineContext;
    }

    public void setResponseLineContext(String responseLineContext) {
        this.responseLineContext = responseLineContext;
    }


}
