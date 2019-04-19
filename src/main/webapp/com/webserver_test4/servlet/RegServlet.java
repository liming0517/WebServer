package main.webapp.com.webserver_test4.servlet;

import main.webapp.com.webserver_test4.http.HttpResponse;
import main.webapp.com.webserver_test4.http.HttpRequest;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * ����ע��ҵ��
 */
public class RegServlet {

    String namePrint;
    String passwordPrint;
    RandomAccessFile raf = null;

    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        System.out.println("RegServlet����ʼ����ע��...");
/**
 * 1:ͨ��request��ȡ�û���ҳ���������ע����Ϣ
 * 2.�����û���ע����Ϣд�뵽�ļ�user.dat��
 * 3.����response��Ӧע����ҳ��
 */
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        String nickname = httpRequest.getParameter("nickname");
        int age = Integer.parseInt(httpRequest.getParameter("age"));
//2.

        try {
            raf = new RandomAccessFile("user.dat", "rw");
/**
 * ���ȶ�ȡuser.dat�ļ��е�ÿ���û���Ϣ�ȶ��û����뵱ǰע���û��������Ƿ�һ�£���һ����˵�����û��Ѿ���ʹ�ã�
 * ��ʱ����response��Ӧ�û���ʾҳ�棺���û��Ѵ��ڣ�����ִ������ԭ�е�ע�������
 *
 * �û���ʾҳ�棺reg_fail.html
 */
            for (int i = 0; i < raf.length() / 100; i++) {
                raf.seek(i * 100);
                byte[] data = new byte[32];
                raf.read(data);
                namePrint = new String(data, "UTF-8").trim();
                raf.read(data);
                System.out.println("�����ǣ�-----------------------------" + namePrint);
                passwordPrint = new String(data, "UTF-8").trim();
                System.out.println("�����ǣ�-----------------------------" + passwordPrint);
            }

            if (namePrint.equals(username)) {
                //��ע���û�
                File failPage = new File("./\\src\\main\\webapp\\com\\webserver_test4\\webapps\\myweb\\reg_fail.html");
                httpResponse.setEntity(failPage);
                return;
            }
            //�ֽ�ָ���ƶ����ļ�ĩβ�Ա�׷��һ���¼�¼
            raf.seek(raf.length());
            //д�û���
            byte[] data = username.getBytes("UTF-8");
            data = Arrays.copyOf(data, 32);//���ݵ�32�ֽ�

            //��32�ֽ�һ����д��
            raf.write(data);
            //д����
            data = password.getBytes("UTF-8");
            data = Arrays.copyOf(data, 32);
            raf.write(data);
            //д�ǳ�
            data = nickname.getBytes("UTF-8");
            data = Arrays.copyOf(data, 32);
            raf.write(data);

            //д����
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
        System.out.println("RegServlet������ע��ҵ�����");
    }

    //��ѯϵͳ�Ƿ��Ѿ����ڸ��˺�
    public boolean queryIsReg(String query) {
        boolean isQuery = false;
        System.out.println(namePrint+passwordPrint+"���˺��Ѿ�����");
        if (query.equals(namePrint) && query.equals(passwordPrint)) {
            isQuery = true;
        }
        return isQuery;
    }
}
