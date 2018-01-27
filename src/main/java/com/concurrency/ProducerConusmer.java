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
        private static int CAPACITY = 2;
        private int size;
        private ArrayList<java.lang.Integer> box;

        Box() {
            box = new ArrayList<>();
        }

        public synchronized void add(int n) {

            while(size>=CAPACITY) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            box.add(n);
            size++;
            System.out.println("Producer :: "+n);
            notifyAll();

        }

        public synchronized void remove() {

            while(size<=0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int remove = box.remove(0);
            size--;
            System.out.println("Consumer :: "+remove);
            notifyAll();
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


