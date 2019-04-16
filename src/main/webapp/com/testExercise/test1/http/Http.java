package main.webapp.com.testExercise.test1.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Http {
    private String method = "";
    private String url = "";
    private String protocol;
    StringBuilder builder = null;
    InputStream inputStream = null;
    //���캯��
    public  Http(InputStream inputStream){
        this.inputStream =inputStream;
    }
    //����������
    public void handleReadLine() {
        System.out.println("��ʼ��ȡ��Ϣ�У�");
        String line = null;

        try {
            line = readLine();
            String str[] = line.split("\\s");
            method = str[0];
            url = str[1];
            protocol = str[2];
            System.out.println("method=" + method + "  url=" + url + "  protocol=" + protocol);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //��������ͷ
    public void handleHeards() {
        try{
            String s = "";
            //��ȡ��Ϣͷ
            Map<String, String> heards = new HashMap<>();
            System.out.println("��ʼ��ȡ��Ϣͷ");
            while ((s = readLine()) != null) {
                String[] str = s.split(":\\s");
                if (!s.equals("")) {
                    heards.put(str[0],str[1]);
                }else{
                    break;
                }


            }
            System.out.println(heards);
            Set<Map.Entry<String,String>> s1=heards.entrySet();
            for(Map.Entry<String,String> map: s1){
                System.out.println("key: "+map.getKey()+"   Value: "+map.getValue());
            }

//            Set<Map.Entry<String,String>> map1=map.entrySet();
//            //����
//            for(Map.Entry<String, String> map2 :map1){
//                System.out.println("map2.getKey()="+map2.getKey()+"     map2.getvalue()="+map2.getValue());
//            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //������Ϣ����
    public void handleContents() {

    }

    //��һ��
    public String readLine() throws IOException {
        builder = new StringBuilder();
        int i = 0, j = 0;
        while ((i = inputStream.read()) != -1) {
            if (i == 10 && j == 13) {
                break;
            }
            builder.append((Character.toChars(i)));
            j = i;
        }
        return builder.toString().trim();
    }
}
