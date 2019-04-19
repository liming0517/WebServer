package main.webapp.com.webserver_v13.http;

import main.webapp.com.webserver_v13.core.EmptyRequestException;

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
    //保存url中"?"左侧的请求部分
    private String requestURI;
    //保存url中"?"右侧的参数部分
    private String queryString;
    //保存解析出来的每一个参数
    private Map<String, String> parameters = new HashMap<>();

    //请求行相关信息

    //消息头相关信息
    //Key 存取消息头的名字  Value存取消息头的值
    private Map<String, String> heards = new HashMap<String, String>();


    // 消息正文相关信息

    //构造方法
    public HttpRequest(Socket socket) throws EmptyRequestException {
        System.out.println("HttpRequest:开始解析请求");

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

        System.out.println("HttpRequest:解析请求完毕！");
    }


    //解析请求行
    public void parseRequestLine() throws EmptyRequestException {
        System.out.println("开始解析请求行");
        /**
         * 1.通过输入流读取第一行字符串(请求行内容)
         * 2.将请求行内容按照空格拆分为三部分
         * 3.将三部分内容设置到对应属性上(method，URL，protocol)
         */
        try {
            String line = readLine();
            System.out.println("请求行:\r\n" + line);
            if (line.equals("")) {
                throw new EmptyRequestException();
            }
            //后期循环接收客户端连接后，下面代码可能会出现数组下标越界，这是由于空请求引起的，后边会解决
            String xyz[] = line.split("\\s");
            this.method = xyz[0];
            this.url = xyz[1];//这里会出现下标越界
            this.protocol = xyz[2];
            System.out.println("method:" + method);
            System.out.println("url:" + url);
            System.out.println("protocol:" + protocol);
            //进一步解析请求行中抽象路径url的内容
            parseURL();
        } catch (EmptyRequestException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("解析请求行完毕！");
    }

    //进一步解析请求行中抽象路径url的内容
    private void parseURL() {
        System.out.println("开始解析请求行中的内容");
/**
 * 一个请求的请求行红当中抽象路径部分有两种情况
 * 1.不含有参数的，如：/myweb/index.html
 * 2.含有参数的，如：/myweb/reg?username=xxx&password=xxx&...
 * 因此我们对抽象路径进一步解析
 * （1）首先判断url的值是否含有?
 *      若不含有"?"则直接将url的值赋值给属性requestURI即可
 *      若含有"?"，执行（2）
 * （2）将url按照"?"拆分成两部分，第一部分应当是请求部分，赋值给属性requestURI
 * 第二部分应是参数部分，赋值给属性queryString
 * （3）进一步解析queryString,将其按照"&"拆分为若干个参数，每一个参数再按照"="拆分为参数名与参数值。
 * 并将参数名作为key,参数值作为value保存到属性parameters这个Map中完成解析操作。
 */
        System.out.println("开始拆分requestURI------------------------");
        if(url.contains("?")) {
            System.out.println("包含问好--------------------------");
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
        System.out.println("requestURI=" + requestURI);
        System.out.println("queryString=" + queryString);
        System.out.println("parameters=" + parameters);
        System.out.println("解析抽象路径完成");
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
            String s = "";

            while ((s = readLine()) != null) {
                String key[] = s.split(":\\s");
                if (!s.equals("")) {
                    heards.put(key[0], key[1]);
                } else {
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
     * 通过对应客户端的输入流读取一行字符串
     * (以CRLF结尾)
     *
     * @return
     */
    private String readLine() throws IOException {
        //读取一行字符串，以CRLF结尾
        StringBuilder builder = new StringBuilder();
        //c1表示上次读取到的字符，C2表示本次读取到的字符
        int c1 = 0, c2 = 0;
        while ((c2 = inputStream.read()) != -1) {
            //是否连续读到了CR-->13，LF-->10
            if (c1 == HttpContext.CR && c2 == HttpContext.LF) {
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

    public String getRequestURI() {
        return requestURI;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }

    //不返回集合，而是给出一个实例
    public String getHeards(String name) {
        return heards.get(name);
    }

}

