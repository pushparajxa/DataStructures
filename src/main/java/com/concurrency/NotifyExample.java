
package com.concurrency;

import java.util.Optional;

public class NotifyExample {

    public static void main(String[] args) {
        Object lock = new Object();
        Thread1 thread1 = new Thread1(lock);
      Thread2 thread2 = new Thread2(thread1,lock);
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
    static class Thread1 extends Thread{
        private final Object lock;

        Thread1(Object lock){
            this.lock = lock;
        }
        @Override
        public void run(){
            synchronized(lock){
                System.out.println("Thread1:: Acquired lock");
                try {
                    System.out.println("Thread1:: Going to wait");
                    lock.wait(); // releases the lock.. when interrupted needs to reacquire lock .. but it is hold by thread2..which is calling thread1.interrupt() in synchronized block
                    System.out.println("Thread1:: After wait call ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Thread2 extends  Thread{
        Thread1 thread1;
        Object lock;
        Thread2(Thread1 thread1,Object lock){
            this.thread1 = thread1;
            this.lock = lock;
        }
        @Override
        public void run() {
            synchronized(lock){
                System.out.println("Thread2:: Acquired lock");
                lock.notify();
                System.out.println("Thread2:: Printing this line after notify");
                System.out.println("Thread2:: Sleeping for sometime");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2:: Finished sleeping");
            }
        }
    }
}
