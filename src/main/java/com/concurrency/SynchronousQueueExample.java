
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
            synchronousQueue.add(10);
              System.out.println("Done with put");

          } catch (InterruptedException e) {
            e.printStackTrace();
          }
      }
    }.start();

    new Thread(() -> {
        try {
          System.out.println("Waiting for take");
          System.out.println("Taking"+synchronousQueue.take());
          System.out.println("Done with polling.");
        } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }).start();

  }
}
