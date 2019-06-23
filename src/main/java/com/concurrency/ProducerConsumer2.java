
package com.concurrency;

import java.util.ArrayList;
import java.util.Random;

public class ProducerConsumer2 {



  public static void main(String[] args) {

    ArrayList<Integer> list = new ArrayList<>();

    Object lock = new Object();

    Thread producer = new Thread() {
      Random random = new Random(
      );
      @Override
      public void run() {
        while(true) {
          synchronized (lock) {
            if (list.size() < 2) {
              int toAdd= Math.abs(random.nextInt());
              System.out.println("Producer: Adding integer="+toAdd);
              list.add(toAdd);
              lock.notify();
              try {
                lock.wait();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              //  System.out.println("Producer: Notifying from producer");
            } else {
              while (list.size() >= 2) {
                try {
                  lock.wait();
                } catch (InterruptedException e) {
                  System.out.println("Producer: Interrupted while waiting");
                }
              }
            }
          }
        }
      }
    };

    Thread consumer = new Thread() {

      @Override
      public void run() {
    while(true) {
      synchronized (lock) {
        if (list.size() > 0) {
          Integer removed = list.remove(0);
          System.out.println("Removed " + removed + " from the list");
          lock.notify();
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          //  System.out.println("Consumer: Notifying from consumer");
        } else {
          while (list.size() == 0) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              System.out.println("Consumer: Interrupted while waiting");
            }
          }
        }
      }
    }
      }

    };

    consumer.start();
    producer.start();

    try {
      Thread.sleep(60*60*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }



}
