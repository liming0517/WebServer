package main.webapp.com.webserver_v8.http;

import java.util.HashMap;
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
        mimeMapping.put("html","text/html");
        mimeMapping.put("css","text/css");
        mimeMapping.put("js","application/javascript");
        mimeMapping.put("png","image/png");
        mimeMapping.put("gif","image/gif");
        mimeMapping.put("jpg","image/jpeg");
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
