package com.designPatterns.structural;

public class Singleton {

  private Singleton() {}

  public static Singleton getSingleton() {
    return Singleton_Retrieval.singleton;
  }

  private static class Singleton_Retrieval {
    // final is not required to be there.
    // This code is guaranteed to be correct because of the initialization guarantees for static fields;
    // if a field is set in a static initializer, it is guaranteed to be made visible, correctly, to any thread that accesses that class.
    // http://www.cs.umd.edu/~pugh/java/memoryModel/jsr-133-faq.html
    private static final Singleton singleton = new Singleton();
  }
}

class Singleton_instance {

  private static volatile Singleton_instance singleton_instance;

  public static Singleton_instance getSingleton() {
    if (singleton_instance == null) {
      synchronized (Singleton_instance.class) {
        if (singleton_instance == null) {
          singleton_instance = new Singleton_instance();
        }
      }
    }
    return singleton_instance;
  }
}

class Singleton_instance_high_performance {
  /*Note the local variable "localRef", which seems unnecessary. The effect of this is
  that in cases where helper is already initialized (i.e., most of the time), the
  volatile field is only accessed once (due to "return localRef;" instead of "return helper;"),
   which can improve the method's overall performance by as much as 25 percent.[7]
    */

  private static volatile Singleton_instance_high_performance singleton;

  public static Singleton_instance_high_performance getSingletion() {
    Singleton_instance_high_performance localRef = singleton;
    if (localRef == null) {
      synchronized (Singleton_instance_high_performance.class) {
        localRef = singleton;
        if (localRef == null) {
          localRef = singleton = new Singleton_instance_high_performance();
        }
      }
    }
    return localRef;
  }
}


