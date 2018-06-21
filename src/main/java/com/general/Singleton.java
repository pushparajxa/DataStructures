package com.general;

public class Singleton {

  private Singleton() {}

  public static Singleton getSingleton() {
    return Singleton_Retrieval.singleton;
  }

  private static class Singleton_Retrieval {
    static final Singleton singleton = new Singleton();
  }
}

class Singleton_instance {

  private volatile Singleton_instance singleton_instance;

  public Singleton_instance getSingletion() {
    if (singleton_instance == null) {
      synchronized (this) {
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

  private volatile Singleton_instance_high_performance singleton;

  public Singleton_instance_high_performance getSingletion() {
    Singleton_instance_high_performance localRef = singleton;
    if (localRef == null) {
      synchronized (this) {
        localRef = singleton;
        if (localRef == null) {
          localRef = singleton = new Singleton_instance_high_performance();
        }
      }
    }
    return localRef;
  }
}


