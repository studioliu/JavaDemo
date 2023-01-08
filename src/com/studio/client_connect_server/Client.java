package com.studio.client_connect_server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        InetAddress inet = InetAddress.getByName("localhost");
        Socket socket = new Socket(inet, 9999);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好，我是客户端\n".getBytes());
        outputStream.close();
        socket.close();
    }
}
