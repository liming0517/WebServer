package main.webapp.com.webserver_test1.http;

import java.util.HashMap;
import java.util.Map;

public class HttpContext {
    private static Map<String,String> mimeMapping=null;
    static{
        mimeMapping = new HashMap<>();
        saveContext();
    }
    private static void saveContext(){
        mimeMapping.put("html","text/html");
        mimeMapping.put("css","text/css");
        mimeMapping.put("js","application/javascript");
        mimeMapping.put("jpg","image/jpeg");
        mimeMapping.put("png","image/png");
        mimeMapping.put("gif","image/gif");
    }
    public static String  httpContext(String key){
        return mimeMapping.get(key);
    }
}
