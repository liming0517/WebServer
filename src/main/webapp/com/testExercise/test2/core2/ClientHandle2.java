package main.webapp.com.testExercise.test2.core2;

import main.webapp.com.testExercise.test1.http.Http;
import main.webapp.com.testExercise.test2.http2.Http2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClientHandle2 implements Runnable {
    private Socket socket;
    private InputStream inputStream = null;
    private Http2 http2 = null;

    public ClientHandle2(Socket socket) {
        try {
            this.socket = socket;
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        http2 = new Http2(inputStream);
        //输出请求行
        http2.hanleLine();
        //输出请求消息头
        http2.handleHeards();
    }
}
