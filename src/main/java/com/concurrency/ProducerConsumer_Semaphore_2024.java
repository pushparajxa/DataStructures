/*
 ** COPYRIGHT **
 */
package com.concurrency;

import com.concurrency.ProducerConsumer_2024_Prep.ConQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumer_Semaphore_2024 {
    
    static class ConQueue<T> {
        
        private Queue<String> queue = new LinkedList<>();
        private int limit = 10;
        private Semaphore semaphore = new Semaphore(1, true);
        
        
        public void add(String s) throws InterruptedException {
            boolean done = false;
            while (!done) {
                semaphore.acquire();
                if (queue.size() < limit) {
                    queue.add(s);
                    done = true;
                }
                semaphore.release();
            }
        }
        
        public String poll() throws InterruptedException {
            while(true) {
                semaphore.acquire();
                if (!queue.isEmpty()) {
                    String s = queue.poll();
                    semaphore.release();
                    return s;
                }
                else {
                    semaphore.release();
                }
                
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        com.concurrency.ProducerConsumer_2024_Prep.ConQueue<String> queue = new com.concurrency.ProducerConsumer_2024_Prep.ConQueue();
        
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                
                while (true) {
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
