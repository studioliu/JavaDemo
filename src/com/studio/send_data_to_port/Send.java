package com.studio.send_data_to_port;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class Send {
    public static void main(String[] args) {
        String[] str = {"hello", "world", "flink", "spark", "kafka"};
        Socket socket = null;
        OutputStream os = null;
        try {
            socket = new Socket("localhost", 9999);
            os = socket.getOutputStream();
            Random random = new Random();
            while (true) {
                int choice = random.nextInt(5);
                os.write((str[choice] + '\n').getBytes());
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
