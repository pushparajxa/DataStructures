package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/*
Connect to the server and read once and then close the connection.
 */
public class client {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT );
            socketChannel.connect(new InetSocketAddress( "localhost",9879));
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(true){
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                if(selector.selectedKeys().size() >=1 )
                    System.out.println("The number of keys are "+selector.selectedKeys().size());

                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                   if(selectionKey.isConnectable()){
                        try {
                            socketChannel.finishConnect();
                            socketChannel.register(selector, SelectionKey.OP_READ );
                        }catch (IOException exception){
                            System.out.println("Connection to the server has failed");
                        }
                        iterator.remove();
                        System.out.println("Made connection to the Server");
                    }else if((selectionKey.readyOps() & SelectionKey.OP_READ )== SelectionKey.OP_READ){
                        buffer.clear();
                        socketChannel.read(buffer);
                        if(buffer.limit()==0){
                            System.out.println("Connection was closed. So closing the socket");
                            socketChannel.close();
                        }else{
                            System.out.println("I have read following from the server:"+new String(buffer.array()));
                            buffer.flip();
                            socketChannel.close();
                        }
                        iterator.remove();
                    }else{
                        System.out.println("Received some unknown key" + selectionKey.toString());
                        iterator.remove();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
