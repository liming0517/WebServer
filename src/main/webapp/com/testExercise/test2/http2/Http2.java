package main.webapp.com.testExercise.test2.http2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 把具体的http拆解写入
 */
public class Http2 {
    private InputStream inputStream;
    BufferedReader bufferedReader = null;

    //拆解请求行
    private String method;
    private String url;
    private String protocol;
    public Http2(InputStream inputStream){
        this.inputStream =inputStream;
    }
    //处理请求行
    public void hanleLine() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String handleReadLine = bufferedReader.readLine();
            String[] str = handleReadLine.split("\\s");
            method=str[0];
            url=str[1];
            protocol=str[2];
            System.out.println("请求行：\r\n" +"method= "+method+"\r\nurl= "+url+"\r\nprotocol= "+protocol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理请求消息头
    public void handleHeards() {
        try {
            String handleHeards = "";
            Map<String, String> heards = new HashMap<>();
            while ((handleHeards = bufferedReader.readLine()) != null) {
                String[] key = handleHeards.split(":\\s");
                if (!handleHeards.equals("")) {
                    heards.put(key[1], key[0]);
                } else {
                    break;
                }
            }
            Set<Map.Entry<String,String>> heard= heards.entrySet();
            for(Map.Entry<String,String> map: heard){
                System.out.println("key= "+map.getValue()+"   Value= "+map.getKey());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
