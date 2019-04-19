package main.webapp.com.webserver_test4.http;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//�������ͷContext�е�����

/**
 * ��Դ��׺����Content-Type��Ӧͷ��Ӧֵ��ӳ���ϵ
 * key����Դ��׺��
 * value��Content-Type��Ӧ��ֵ
 */
public class HttpContext {
    //����һ��Map���content-type
   private  static Map<String,String> mimeMapping =new HashMap<>();;
   //��ʼ�����о�̬��Դ
    static{
       initMimiMapping();
    }
    private static void initMimiMapping(){
//        mimeMapping.put("html","text/html");
//        mimeMapping.put("css","text/css");
//        mimeMapping.put("js","application/javascript");
//        mimeMapping.put("png","image/png");
//        mimeMapping.put("gif","image/gif");
//        mimeMapping.put("jpg","image/jpeg");
        /**
         * ʹ��DOM4J����conf/web.xml�ļ�
         * ������ǩ��������Ϊ<mime-mapping>���ӱ�ǩ
         *��ȡ�������������е��ӱ�ǩ��<extension>�м���ı���Ϊkey <mime-type>�м���ı���Ϊvalue
         * ����ʼ��mimeMapping���Map
         * ��ʼ����Ϻ����MapӦ����1000���Ԫ��
         */
        try{
            SAXReader reader =new SAXReader();
            Document doc = reader.read(new File("./web.xml"));
            Element root=doc.getRootElement();
            List<Element> list = root.elements("mime-mapping");
            for(Element mimemapiing : list ){
                //ȡ��key
               String key= mimemapiing.element("extension").getText();
               //ȡ��value
                String value= mimemapiing.element("mime-type").getText();
                //��ӵ�������
                mimeMapping.put(key,value);
            }


            //��ȡmap��keyֵ
//            String ext =mimemapping.element("extension").getTextTrim();
//            //��ȡmap��value
//            String  value=mimemapping.element("mime-type").getText();



        }catch(Exception e){

        }
        System.out.println("mimeMapping.size()="+mimeMapping.size());
    }

    /**
     * ���ݸ�������Դ��׺����ȡ��Ӧ��Content-Typeֵ
     * @param ext
     * @return
     */
    public static String getMimeType(String ext){
        return mimeMapping.get(ext);
    }

}
