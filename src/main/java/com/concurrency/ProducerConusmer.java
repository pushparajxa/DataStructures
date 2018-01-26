package com.concurrency;

import java.util.ArrayList;

public class ProducerConusmer {

    public static void main(String[] args) {
        Box box = new Box();
        Producer producer = new Producer(box);
        Consumer consumer = new Consumer(box);
        Thread producerThread = new Thread(producer,"Produce Thread");
        Thread consumerThread = new Thread(consumer,"Consumer Thread");
        producerThread.start();
        consumerThread.start();

    }

    static class Box {
        private int size;
        private static int CAPACITY =2;
        private ArrayList<java.lang.Integer> box;

        Box() {
            box = new ArrayList<>();
        }

        public synchronized void add(int n) {
            if(size < CAPACITY) {
                box.add(n);
                size++;
                notifyAll();
                System.out.println("Producer :: "+n);
            }else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void remove() {

            if(size > 0) {
                int remove= box.remove(0);
                size--;
                notifyAll();
                System.out.println("Consumer :: "+remove);
            }
            else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public int getSize() {
            // System.out.println("size is "+size);
            return size;
        }

    }

    static class Producer implements Runnable {
        Box box;
        int count = 0;

        Producer(Box box) {
            this.box = box;
        }

        @Override
        public void run() {
            int count = 0;
            while(true) {
                box.add(count++);
            }
        }
    }

    static class Consumer implements Runnable {

        Box box;
        int count = 0;

        Consumer(Box box) {

            this.box = box;
        }


        @Override
        public void run() {
            while(true) {
                 box.remove();
            }
        }
    }

}


