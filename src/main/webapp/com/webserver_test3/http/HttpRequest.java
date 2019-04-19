package main.webapp.com.webserver_test3.http;

import main.webapp.com.webserver_test3.core.EmptyRequestException;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * �������
 * �����ÿһ��ʵ�����ڱ�ʾ��������͹�����һ���������ݣ�ÿ�������������ֹ���(�����У���Ϣͷ����Ϣ����)
 */
public class HttpRequest {
    /**
     * ��ʼ��HttpRequest����
     * ��ʼ���Ĺ��̾��ǽ�������Ĺ��̣�ʵ������Ϻ�ǰHttpRequest����ͱ�ʾ��������͹�����������������ˡ�
     */
    private Socket socket = null;
    private InputStream inputStream = null;
    //����ʽ
    private String method;
    //������Դ�ĳ���·��
    private String url;
    //����ʹ�õ�Э��汾
    private String protocol;
    private String requestURI;
    private String queryString;
    Map<String,String> parameters;
    //�����������Ϣ

    //��Ϣͷ�����Ϣ
    //Key ��ȡ��Ϣͷ������  Value��ȡ��Ϣͷ��ֵ
    private Map<String, String> heards = new HashMap<String, String>();


    // ��Ϣ���������Ϣ

    //���췽��
    public HttpRequest(Socket socket) throws EmptyRequestException {
        System.out.println("HttpRequest:��ʼ��������");
        try {
            this.socket = socket;
            this.inputStream = socket.getInputStream();
            parseRequestLine();
            parseHeaders();
            parseContent();
        } catch (EmptyRequestException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("HttpRequest:����������ϣ�");
    }

    public void parseURL(){
      //��������ͷ  /myweb/reg?username=adc&password=123&nickname=rewrwe&age=212
        System.out.println("��ʼ���requestURI------------------------");
        if(url.contains("?")) {
            System.out.println("�����ʺ�--------------------------");
            String[] data = url.split("\\?");
            requestURI = data[0];
            if (data.length > 1) {
                queryString = data[1];
                data = queryString.split("\\&");
                for (String para : data) {
                    String[] arr = para.split("\\=");
                    if (arr.length > 1) {
                        parameters.put(arr[0], arr[1]);
                    } else {
                        parameters.put(arr[0], null);
                    }
                }
            }
        } else {
            this.requestURI = this.url;
        }

//        String[] data = url.split("\\?");
//        requestURI = data[0];
//        if (data.length > 1) {
//            queryString = data[1];
//            data = queryString.split("\\&");
//            for (String para : data) {
//                String[] arr = para.split("\\=");
//                if (arr.length > 1) {
//                    parameters.put(arr[0], arr[1]);
//                } else {
//                    parameters.put(arr[0], null);
//                }
//            }
//        }

        System.out.println("requestURI="+requestURI);
        System.out.println("queryString="+queryString);
        System.out.println("parameters="+parameters);
    }

    //����������
    public void parseRequestLine() throws EmptyRequestException {
        System.out.println("��ʼ����������");
        /**
         * 1.ͨ����������ȡ��һ���ַ���(����������)
         * 2.�����������ݰ��տո���Ϊ������
         * 3.���������������õ���Ӧ������(method��URL��protocol)
         */
        try {
            String line = readLine();
            System.out.println("������:\r\n" + line);
            if (line.equals("")) {
                throw new EmptyRequestException();
            }
            //����ѭ�����տͻ������Ӻ����������ܻ���������±�Խ�磬�������ڿ���������ģ���߻���
            String xyz[] = line.split("\\s");
            this.method = xyz[0];
            this.url = xyz[1];//���������±�Խ��
            this.protocol = xyz[2];
            System.out.println("method:" + method);
            System.out.println("url:" + url);
            System.out.println("protocol:" + protocol);
                  this.parseURL();

        } catch (EmptyRequestException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("������������ϣ�");
    }

    //������Ϣͷ
    public void parseHeaders() {
        System.out.println("��ʼ������Ϣͷ");
        /**
         * 1.ѭ������readLine������ȡÿ����Ϣͷ
         * 2.����Ϣͷ���ա��� ����֣�������Ϣͷ��������ΪKey����Ϣͷ��ֵ��Ϊvalue���浽����headers���Maps��
         * 3.�������readLine�������ص���һ�����ַ�������˵�����ε�����ȡ����CRLF����ô�Ϳ���ֹͣ������Ϣͷ��
         */
        try {
            String s = "";

            while ((s = readLine()) != null) {
                String key[] = s.split(":\\s");
                if (!s.equals("")) {
                    heards.put(key[0], key[1]);
                } else {
                    break;
                }
            }
            //��������
//            Set<Map.Entry<String, String>> entrySet= heards.entrySet();
//            for(Map.Entry<String,String> maps:entrySet){
//                System.out.println("maps.getKey():"+maps.getKey()+"   maps.Value():"+maps.getValue());
//            }
            System.out.println(heards);
            System.out.println("������Ϣͷ��ϣ�");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //������Ϣ����
    public void parseContent() {
        System.out.println("��ʼ������������");
        System.out.println("��������������ϣ�");
    }

    /**
     * ͨ����Ӧ�ͻ��˵���������ȡһ���ַ���
     * (��CRLF��β)
     *
     * @return
     */
    private String readLine() throws IOException {
        //��ȡһ���ַ�������CRLF��β
        StringBuilder builder = new StringBuilder();
        //c1��ʾ�ϴζ�ȡ�����ַ���C2��ʾ���ζ�ȡ�����ַ�
        int c1 = 0, c2 = 0;
        while ((c2 = inputStream.read()) != -1) {
            //�Ƿ�����������CR-->13��LF-->10
            if (c1 == 13 && c2 == 10) {
                break;
            }
            builder.append((char) c2);
            c1 = c2;
        }
        return builder.toString().trim();
    }

    //get set ����
    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getProtocol() {
        return protocol;
    }

    //�����ؼ��ϣ����Ǹ���һ��ʵ��
    public String getHeards(String name) {
        return heards.get(name);
    }

}
