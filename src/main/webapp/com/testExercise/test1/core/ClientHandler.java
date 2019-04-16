package main.webapp.com.testExercise.test1.core;

import main.webapp.com.testExercise.test1.http.Http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler {
    InputStream inputStream = null;
    private Socket socket = null;





    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
            System.out.println("进入客户端实例");
            inputStream = socket.getInputStream();
            Http http =new Http(inputStream);
            http.handleReadLine();
            http.handleHeards();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
