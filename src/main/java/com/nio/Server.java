package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/*
Accept connection from a single client then send a buffer.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress("localhost",9879));
            Selector selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            SocketChannel clientSocketChannel=null;
            SelectionKey clientSelectionKey =null;
            boolean done =false;

             while(true){
                 selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                if(selector.selectedKeys().size() >=1 )
                 System.out.println("The number of keys are "+selector.selectedKeys().size());

                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()){
                        System.out.println("Connection request has come from the client.Status of write="+selectionKey.isWritable());
                        clientSocketChannel = serverChannel.accept();
                        clientSocketChannel.configureBlocking(false);
                        clientSelectionKey = clientSocketChannel.register(selector, SelectionKey.OP_WRITE);
                        iterator.remove();
                    }else if(selectionKey.isWritable()){
                        if(!done){
                            ByteBuffer buffer = ByteBuffer.wrap("Hello from Server".getBytes());
                            clientSocketChannel.write(buffer);
                            System.out.println("Wrote buffer to the client");
                            done=true;
                        }else{
                            clientSocketChannel.close();
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
