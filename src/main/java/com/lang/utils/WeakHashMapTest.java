
package com.lang.utils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/*

https://www.baeldung.com/java-weak-reference
https://www.baeldung.com/java-weakhashmap
https://community.oracle.com/blogs/enicholas/2006/05/04/understanding-weak-references
https://community.oracle.com/blogs/enicholas/2006/05/04/understanding-weak-references
 */

public class WeakHashMapTest {

  public static void main(String[] args) {
  //testWithOutReferenceQueue();
  testWithReferenceQueue();
  }

  static void testWithReferenceQueue(){
    ReferenceQueue referenceQueue = new ReferenceQueue();
    WeakHashMapTest weakHashMapTest = new WeakHashMapTest();
    WeakReference<WeakHashMapTest> weakReference = new WeakReference<>(weakHashMapTest,referenceQueue);
    System.out.println(weakReference.isEnqueued());
    System.out.println(weakReference.get());
    System.out.println(weakReference);
    weakHashMapTest = null;
    System.out.println("Forcing garbage collection");
    System.gc();
    boolean done = false;
    int count=0;
    while(!done) {
      System.out.println("***************Iterarion "+count+"*************");
      done = weakReference.isEnqueued();
      System.out.println(weakReference.isEnqueued());
      System.out.println(weakReference.get());
      System.out.println(referenceQueue.poll());
      try {
        Thread.sleep(3*1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      count++;
    }
  }

  static void testWithOutReferenceQueue(){
    WeakHashMap<WeakHashMapTest,String> weakHashMap = new WeakHashMap<>();
    WeakHashMapTest weakHashMapTest = new WeakHashMapTest();
    weakHashMap.put(weakHashMapTest,"value1");


    while(true){
      if(!weakHashMap.isEmpty()){
        System.out.println(weakHashMap);
        weakHashMapTest=null; // remove the strong reference to object created on line 10.
      }else{
        System.out.println("Garbage collected");
        return;
      }
      try {
        Thread.sleep(3*1000);
        System.gc();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

}
