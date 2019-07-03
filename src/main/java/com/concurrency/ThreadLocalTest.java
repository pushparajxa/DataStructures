
package com.concurrency;

public class ThreadLocalTest {

  private ThreadLocal<String> name = new ThreadLocal<>();


   public void printName(){
     System.out.println(Thread.currentThread().getName()+"::"+name.get()+"-Thread");
   }



  public static void main(String[] args) throws InterruptedException {

    ThreadLocalTest threadLocalTest = new ThreadLocalTest();


    Thread t1 = new Thread(){

      @Override
      public void run(){
        threadLocalTest.name.set("From_Thread_T1");

        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        threadLocalTest.printName();
      }
    };

    Thread t2 = new Thread(){

      @Override
      public void run(){
        threadLocalTest.name.set("From_Thread_T2");

        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        threadLocalTest.printName();
      }
    };


    t1.start();
    t2.start();

    t1.join();
    t2.join();
  }

}
