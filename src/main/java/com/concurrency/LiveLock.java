
package com.concurrency;

/**
As part of heinz kabutz's Februaru multi thread course
 */

public class LiveLock {

  public static void main(String[] args) {
  LiveLock liveLock = new LiveLock();
  liveLock.execute();
  }

  public void execute(){

    Object lock = new Object();

    int i=10;

    synchronized (lock){
      boolean done = false;

      do{

        while(!done){

          try {
            lock.wait();
            /**
             * If the present thread is interrupted while it is waiting. Then it will acquire the
             * lock to execute the catch block. Once exception is thrown, its interrupy flag is
             * reset. Now, in the catch block we interrupt the thread again. After this, it will
             * start executing from the while(!done) condition, then it tries to execute lock
             * .wait(). This will throw InterrupedException because the current thread's
             * interrupt flag is set. This is explained in wait(long timeout) method description
             * like below.
             *
             * If the current thread is interrupted by any thread BEFORE or while it is waiting,
             * then an InterruptedException is thrown.
             * This exception is not thrown until the lock status of this object has been restored as described above.
             *
             * So, in this example,the thread keeps on throwing InterruptedException forever
             * without releasing the lock. This is called livelock.
             */
            done=true;
          } catch (InterruptedException e) {
            /**
             * This exception block is executed only after the thread is able to restore the monitor
             * of "lock" object, as explained above
             */
            System.out.println("hello");
            Thread.currentThread().interrupt();
          }

        }
        i++;

      }while(i<20);


      lock.notify();

    }


  }


}
