package com.concurrency;

// The deadlock situation is explained here
// https://carlmastrangelo.com/blog/javas-mysterious-interrupt
public class DeadLock {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread1 thread1 = new Thread1(lock);
        Thread2 thread2 = new Thread2(thread1,lock);
        thread1.start();
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
                thread1.interrupt();
                try {
                    System.out.println("Thread2:: Joining the thread1");
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
