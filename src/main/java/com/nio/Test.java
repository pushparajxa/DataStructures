package com.nio;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello dudes");
        ByteBuffer buffer = ByteBuffer.allocate(10);
        try {
            FileChannel fileChannel = FileChannel.open(new File("/Users/pushparaj.motamari/Desktop/file").toPath(), StandardOpenOption.CREATE);
            ByteBuffer.allocateDirect(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
