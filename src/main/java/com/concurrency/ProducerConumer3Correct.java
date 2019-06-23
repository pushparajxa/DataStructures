
package com.concurrency;

import java.util.ArrayList;
import java.util.Random;

public class ProducerConumer3Correct {

  public static void main(String[] args) {

    Container container = new Container();

    Thread producer = new Thread() {

      @Override
      public void run() {
        while (true) {
          container.add();
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    Thread producer2 = new Thread() {

      @Override
      public void run() {
        while (true) {
          container.add();
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    Thread consumer = new Thread() {

      @Override
      public void run() {
        while (true) {
          container.consumer();
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }

    };



    producer.start();
    consumer.start();

    try {
      Thread.sleep(1000 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  static class Container {

    private int size = 10;
    private ArrayList<Integer> list = new ArrayList<>(size);
    private Random random = new Random();

    public synchronized void add() {

      while (list.size() >= size) {
        try {
          wait();
        } catch (InterruptedException e) {
          System.out.println("Add: InterruptedException while adding");
        }
      }
      int nextInt = Math.abs(random.nextInt());
      System.out.println("Add: Adding int" + nextInt);
      list.add(nextInt);
      notifyAll();
    }

    public synchronized void consumer() {

      while (list.size() == 0) {
        /* We need while loop because of two reasons
        1. To handle the case of spurious wake-ups
        2. Imagine two consumers waiting. Say there is only one element in the queue.Say
        consumer-1 woken-up and removed element at 0. After that it called notify and consumer2
        wokem up this time. If there is no while loop to check, it will go ahead to remove
        element at 0 index when all the elements already have been removed leading to array index
         out of bounds exception.
         More info could be found at
         https://youtu.be/UOr9kMCCa5g?t=555
        */
        try {
          wait();
        } catch (InterruptedException e) {
          System.out.println("Consume: InterruptedException while consuming");
        }
      }

      Integer removed = list.remove(0);
      notifyAll();
      System.out.println("Consume: Consuming integer" + Math.abs(removed));

    }
  }
}
