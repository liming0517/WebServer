package main.webapp.com.webserver_v3.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求对象
 * 该类的每一个实例用于表示浏览器发送过来的一个请求内容，每个请求由三部分构成(请求行，消息头，消息正文)
 */
public class HttpRequest {
    /**
     * 初始化HttpRequest对象
     * 初始化的过程就是解析请求的过程，实例化完毕后当前HttpRequest对象就表示浏览器发送过来的这个请求内容了。
     */
    private Socket socket = null;
    private InputStream inputStream = null;
    //请求方式
    private String method;
    //请求资源的抽象路径
    private String url;
    //请求使用的协议版本
    private String protocol;

    //请求行相关信息

    //消息头相关信息
    //Key 存取消息头的名字  Value存取消息头的值
    private Map<String, String> heards = new HashMap<String, String>();


    // 消息正文相关信息

    //构造方法
    public HttpRequest(Socket socket) {
        System.out.println("HttpRequest:开始解析请求");

        try {
            this.socket = socket;
            this.inputStream = socket.getInputStream();
            parseRequestLine();
            parseHeaders();
            parseContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("HttpRequest:解析请求完毕！");
    }


    //解析请求行
    public void parseRequestLine() {
//        try {
//            InputStream inputStream = socket.getInputStream();
//            byte[] bytes =new byte[1024];
//            int n =0;
//            while((n=inputStream.read(bytes))!= -1){
//                String s = new String(bytes,0,n);
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }r
        System.out.println("开始解析请求行");
        /**
         * 1.通过输入流读取第一行字符串(请求行内容)
         * 2.将请求行内容按照空格拆分为三部分
         * 3.将三部分内容设置到对应属性上(method，URL，protocol)
         */
        try {
            String line =readLine();
            System.out.println("请求行:\r\n" + line);
            //后期循环接收客户端连接后，下面代码可能会出现数组下标越界，这是由于空请求引起的，后边会解决
            String xyz[] = line.split("\\s");
            this.method = xyz[0];
            this.url = xyz[1];//这里会出现下标越界
            this.protocol = xyz[2];
            System.out.println("method:" + method);
            System.out.println("url:" + url);
            System.out.println("protocol:" + protocol);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("解析请求行完毕！");
    }

    //解析消息头
    public void parseHeaders() {
        System.out.println("开始解析消息头");
        /**
         * 1.循环调用readLine方法读取每个消息头
         * 2.将消息头按照“： ”拆分，并将消息头的名字作为Key，消息头的值作为value保存到属性headers这个Maps中
         * 3.如果调用readLine方法返回的是一个空字符串，则说明本次单独读取到了CRLF，那么就可以停止解析消息头了
         */
        try {
            String s= "";

            while((s=readLine())!= null ){
                String key[] = s.split(":\\s");
                if(!s.equals("")){
                    heards.put(key[0],key[1]);
                }else{
                    break;
                }
            }
            //遍历集合
//            Set<Map.Entry<String, String>> entrySet= heards.entrySet();
//            for(Map.Entry<String,String> maps:entrySet){
//                System.out.println("maps.getKey():"+maps.getKey()+"   maps.Value():"+maps.getValue());
//            }
            System.out.println(heards);
            System.out.println("解析消息头完毕！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //解析消息正文
    public void parseContent() {
        System.out.println("开始解析请求正文");
        System.out.println("解析请求正文完毕！");
    }

    /**
     *通过对应客户端的输入流读取一行字符串
     * (以CRLF结尾)
     * @return
     */
   private String readLine() throws IOException {
       //读取一行字符串，以CRLF结尾
       StringBuilder builder = new StringBuilder();
       //c1表示上次读取到的字符，C2表示本次读取到的字符
       int c1 = 0, c2 = 0;
       while ((c2 = inputStream.read()) != -1) {
           //是否连续读到了CR-->13，LF-->10
           if (c1 == 13 && c2 == 10) {
               break;
           }
           builder.append((char) c2);
           c1 = c2;
       }
      return builder.toString().trim();
   }
//get set 方法
    public String getUrl() {
        return url;
    }
    public String getMethod() {
        return method;
    }
    public String getProtocol() {
        return protocol;
    }
    //不返回集合，而是给出一个实例
    public String getHeards(String name) {
        return heards.get(name);
    }

}

