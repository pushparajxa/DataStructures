
package com.concurrency;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {

  public static void main(String[] args) {

    SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
    new Thread(){
      @Override
      public void run(){
          try {
            System.out.println("Producer:: "+this.getName()+"::"+10);
            Thread.sleep(5*1000l);
            synchronousQueue.put(10);

          } catch (InterruptedException e) {
            e.printStackTrace();
          }
      }
    }.start();

    new Thread(){
      @Override
      public void run(){
          try {
            System.out.println("Waiting for take");
            System.out.println("Taking"+synchronousQueue.take());
          } catch (InterruptedException e) {
            e.printStackTrace();
        }
      }
    }.start();

  }
}
