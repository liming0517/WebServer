package main.webapp.com.webserver_test4.http;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//存放请求头Context中的内容

/**
 * 资源后缀名与Content-Type响应头对应值的映射关系
 * key：资源后缀名
 * value：Content-Type对应的值
 */
public class HttpContext {
    //定义一个Map存放content-type
   private  static Map<String,String> mimeMapping =new HashMap<>();;
   //初始化所有静态资源
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
         * 使用DOM4J解析conf/web.xml文件
         * 将根标签下所有名为<mime-mapping>的子标签
         *获取回来，并将其中的子标签：<extension>中间的文本作为key <mime-type>中间的文本作为value
         * 来初始化mimeMapping这个Map
         * 初始化完毕后，这个Map应当有1000多个元素
         */
        try{
            SAXReader reader =new SAXReader();
            Document doc = reader.read(new File("./web.xml"));
            Element root=doc.getRootElement();
            List<Element> list = root.elements("mime-mapping");
            for(Element mimemapiing : list ){
                //取出key
               String key= mimemapiing.element("extension").getText();
               //取出value
                String value= mimemapiing.element("mime-type").getText();
                //添加到集合中
                mimeMapping.put(key,value);
            }


            //获取map的key值
//            String ext =mimemapping.element("extension").getTextTrim();
//            //获取map的value
//            String  value=mimemapping.element("mime-type").getText();



        }catch(Exception e){

        }
        System.out.println("mimeMapping.size()="+mimeMapping.size());
    }

    /**
     * 根据给定的资源后缀名获取对应的Content-Type值
     * @param ext
     * @return
     */
    public static String getMimeType(String ext){
        return mimeMapping.get(ext);
    }

}
