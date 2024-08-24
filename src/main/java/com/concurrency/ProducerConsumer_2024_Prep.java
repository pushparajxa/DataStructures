/*
 ** COPYRIGHT **
 */
package com.concurrency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer_2024_Prep {
    
    static class ConQueue<T> {
        private Queue<String> queue = new LinkedList<>();
        private int limit = 10;
        
        public void add(String s){
            boolean done = false;
            while(!done) {
                synchronized (this) {
                    if (queue.size() < limit) {
                        queue.add(s);
                        //System.out.println("Added s");
                        done = true;
                        notify();
                    } else {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            //ignore
                        }
                    }
                }
            }
        }
        
        public String poll(){
            while(true) {
                synchronized (this) {
                    if (!queue.isEmpty()) {
                        notify();
                        return queue.poll();
                    } else {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            //ignore
                        }
                    }
                }
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
         ConQueue<String> queue = new ConQueue();
        
       
        
        
        
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                
                while(true) {
                    queue.add("hello");
                    System.out.println("Producing");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        //ignore
                        System.out.println("producer interrupted");
                    }
                }

                
             
            }
        });
        
        
        Thread consumer = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                
                while (true) {
                    queue.poll();
                    System.out.println("Consuming");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        //ignore
                        System.out.println("producer interrupted");
                    }
                }

            }
        });
        
        
        producer.start();
        consumer.start();
        
        producer.join();
        consumer.join();
        
        
        
    }
    
    
}
