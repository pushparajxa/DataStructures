package com.nio;

import java.io.IOException;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",9879);
            System.out.println("Made a connection");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
