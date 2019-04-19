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

    //���췽��
    public HttpResponse(Socket socket) {
        try {
            this.socket = socket;
            this.outputStream = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * ״̬�������Ϣ
     */

    /**
     * ״̬���� Ĭ��ֵ200
     * Ĭ��ֵ200��Ҫ������ԭ��
     * 1,�����ָ��Ĭ��ֵ��intĬ��ֵΪ0��������û������״̬����Ļ���HTTPЭ����û��״̬��Ϊ0���������ģ����Բ�����0��Ĭ��ֵ
     * 2.ͨ��һ����������ȷ�����ظ��ͻ���200�ǱȽ϶����������Ĭ��ֵ��200�����ڴ󲿷���Ӧʱ����ָ��״̬����Ͷ�Ӧ�����ˡ�
     */
    private int statusCode = 200;
    //״̬������Ĭ��ֵOK
    private String statusReason = "OK";

    /**
     * ��Ӧͷ�����Ϣ
     */
private Map<String,String > headers =new HashMap<>();

    /**
     * ��Ӧ���������Ϣ
     */
    //��Ӧ���ĵ�ʵ���ļ�
    private File entity = null;

    /**
     * ����ǰ��Ӧ����������һ����׼HTTP��Ӧ��ʽ���͸��ͻ���
     */
    public void flush() {
        try {
            //����״̬��
            this.sendStatusLine();
            //������Ӧͷ
            this.sendHeaders();
            //��������
            this.sendContext();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //����״̬��
    private void sendStatusLine() throws Exception {
        //1.����״̬��

        String line = "HTTP/1.1" + " " + statusCode + " " + statusReason;
        outputStream.write(line.getBytes("ISO8859-1"));
        outputStream.write(13);
        outputStream.write(10);
        System.out.println("��ʼ����״̬�гɹ�"+line);
    }

    //������Ӧͷ
    private void sendHeaders() throws Exception {
        //2.������Ӧͷ
       Set<Map.Entry<String,String>> entrySet= headers.entrySet();
       for(Map.Entry<String,String> header : entrySet ){
           String name =header.getKey();
           String value=header.getValue();
           String line = name +": "+value;
           outputStream.write(line.getBytes("ISO8859-1"));
           outputStream.write(13);
           outputStream.write(10);
       }
        //��������CRLF��ʾ��Ӧͷ���ͽ���
        outputStream.write(13);
        outputStream.write(10);
    }

    //������Ӧ����
    private void sendContext() throws Exception{
        /**
         * ���ʵ���ļ����ڣ�����Ϊ���ķ���
         *ע����Ӧ������һ�������Բ�������
         */
        if (entity !=null) {
            FileInputStream fileInputStream1 = new FileInputStream(entity);
            try{
                int len = 0;
                byte[] bytes = new byte[1024 * 10];
                while ((len = fileInputStream1.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                System.out.println("������Ӧ���ĳɹ�");
            }catch (Exception e){
                throw e;
            }finally {
                outputStream.close();
                fileInputStream1.close();
            }
        }




    }


    //get,set ����
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
     * ������Ӧ���Ķ�Ӧ��ʵ���ļ�
     * �������ĵ�ͬʱ���Զ�����Ӧ���������˵��
     * ��Ӧ���ĵ���Ӧͷ:Content-Type��Content-Length
     * @param entity
     */
    public void setEntity(File entity) {
        this.entity = entity;
        String fileName =entity.getName();
        //��ȡ��׺��
        String ext= fileName.substring(fileName.indexOf(".")+1);

        //�����Ӧͷ
        this.putHeader("Content-Type", HttpContext.getMimeType(ext));
        this.putHeader("Content-Length",entity.length()+"");
    }

    /**
     * ��ǰ��Ӧ���������һ����Ӧͷ
     * @param name
     * @param value
     */
    public void putHeader(String name,String value){
        this.headers.put(name,value);
    }

}
