
package com.lang.utils;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;


/*

A phantom reference is a kind of reference in Java, where the memory can be reclaimed. The phantom reference is one of the strengths or levels of 'non strong' reference defined in the Java programming language;
the others being weak and soft.[1]
Phantom reference are the weakest level of reference in Java; in order from strongest to weakest, they are: strong, soft, weak, phantom.

An object is phantomly referenced after it has been finalized.

In Java 8 and earlier versions, the reference needs to be cleared before the memory for a finalized referent can be reclaimed.
A change in Java 9[2] will allow memory from a finalized referent to be reclaimable immediately.

Use[edit]
Phantom references are of limited use, primarily narrow technical uses.[3] First, it can be used instead of a finalize method, guaranteeing that the object is not resurrected during finalization.
 This allows the object to be garbage collected in a single cycle, rather than needing to wait for a second GC cycle to ensure that it has not been resurrected.
 A second use is to detect exactly when an object has been removed from memory (by using in combination with a ReferenceQueue object), ensuring that its memory is available,
for example deferring allocation of a large amount of memory (e.g., a large image) until previous memory is freed.

https://en.wikipedia.org/wiki/Phantom_reference

http://pawlan.com/monica/articles/refobjs/

https://www.baeldung.com/java-phantom-reference August-2023
 */

public class PhantomReferenceTest {

  public static void main(String[] args) throws InterruptedException {
    testPhantomReference();
  }

  static void testPhantomReference() throws InterruptedException {
    ReferenceQueue referenceQueue = new ReferenceQueue();
    PhantomReferenceTest phantomReferenceTest = new PhantomReferenceTest();
    PhantomReference<PhantomReferenceTest> phantomReference =
        new PhantomReference<>(phantomReferenceTest,referenceQueue);

    System.out.println(phantomReference.get());
    System.out.println(phantomReference.isEnqueued());
    System.out.println("Forcing manual gc");
    phantomReferenceTest = null;
    System.gc();

    boolean done=false;

    int count=0;
    while(!done){

      System.out.println("************ Iteration "+count+"***********");
      done = phantomReference.isEnqueued();
      System.out.println(phantomReference.isEnqueued());
      System.out.println(phantomReference.get());
      Reference poll = referenceQueue.poll();
      System.out.println("Reference ="+poll+". Value="+poll.get());

      try {
        Thread.sleep(3*1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      count++;
    }

  }
}
