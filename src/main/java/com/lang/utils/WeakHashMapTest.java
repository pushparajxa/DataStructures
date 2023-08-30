
package com.lang.utils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/*

https://www.baeldung.com/java-weak-reference
https://www.baeldung.com/java-weakhashmap
https://community.oracle.com/blogs/enicholas/2006/05/04/understanding-weak-references
https://web.archive.org/web/20061130103858/http://weblogs.java.net/blog/enicholas/archive/2006/05/understanding_w.html
 */

/*
Soft references

A soft reference is exactly like a weak reference, except that it is less eager to throw away the object to which it refers.
An object which is only weakly reachable (the strongest references to it are WeakReferences) will be discarded at the
next garbage collection cycle, but an object which is softly reachable will generally stick around for a while.

SoftReferences aren't required to behave any differently than WeakReferences, but in practice softly reachable objects are generally
 retained as long as memory is in plentiful supply. This makes them an excellent foundation for a cache, such as the image cache described above,
  since you can let the garbage collector worry about both how reachable the
objects are (a strongly reachable object will never be removed from the cache) and how badly it needs the memory they are consuming.
 */

/*
Weak references

A weak reference, simply put, is a reference that isn't strong enough to force an object to remain in memory. Weak references allow you to leverage the garbage collector's ability
 to determine reachability for you, so you don't have to do it yourself. You create a weak reference like this:

WeakReference weakWidget = new WeakReference(widget);
and then elsewhere in the code you can use weakWidget.get() to get the actual Widget object. Of course the weak reference isn't strong enough to prevent
garbage collection, so you may find (if there are no strong references to the widget) that weakWidget.get() suddenly starts returning null.

To solve the "widget serial number" problem above, the easiest thing to do is use the built-in WeakHashMap class.
WeakHashMap works exactly like HashMap, except that the keys (not the values!) are referred to using weak references.
If a WeakHashMap key becomes garbage, its entry is removed automatically. This avoids the pitfalls I described and requires no changes other than the
switch from HashMap to a WeakHashMap. If you're following the standard convention of referring to
 your maps via the Map interface, no other code needs to even be aware of the change.
 */

/*
Use of WeakReference in Java
A third common source of memory leaks is listeners and other callbacks.
If you implement an API where clients register callbacks but don’t deregister them explicitly, they will accumulate unless you take some action.
 The best way to ensure that callbacks are garbage collected promptly is to store only weak references to them,
  for instance, by storing them only as keys in a WeakHashMap.
*/




/* August 2023


  "Weak references are  most often used to implement canonicalizing mappings. "
  https://wiki.c2.com/?CanonicalizedMapping
  https://www.artima.com/insidejvm/ed2/gcP.html
  https://www.baeldung.com/java-weak-reference#:~:text=As%20stated%20by%20Java%20documentation,the%20mapping%20and%20uses%20it.



  2.1. Weak vs Soft References
      --------------------------
      
    Sometimes the difference between weak and soft references is unclear. Soft references are basically a big LRU cache.
    That is, we use soft references when the referent has a good chance of being reused in the near future.
    
    Since a soft reference acts as a cache, it may continue to be reachable even if the referent itself is not.
    As a matter of fact, a soft reference is eligible for collection if and only if:
    
    The referent is not strongly reachable
    The soft reference is not being accessed recently
    So a soft reference may be available for minutes or even hours after the referent becomes unreachable.
    On the other hand, a weak reference will be available only for as long as its referent is still around.

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
      System.out.println("***************Iteration "+count+"*************");
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
