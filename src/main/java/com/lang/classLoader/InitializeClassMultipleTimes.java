/*
 * Copyright 2020 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.lang.classLoader;

import java.net.URL;
import java.net.URLClassLoader;

public class InitializeClassMultipleTimes {

  static class Foo {

    static {
      System.out.format("  %s initialized by %s%n", Foo.class.getSimpleName(),
          Foo.class.getClassLoader());
    }

    public static void foo()
    {
    }
  }

  private static Class<Foo> loadClass()
  {
    System.out.println("Loading class.");
    // Load the .class file. This will fail if the class file is gone or has
    // the wrong file format.
    return Foo.class;
  }

  private static void initializeClass(Class<?> innerClass)
  {
    System.out.println("Initializing class");
    try {
      Class.forName(innerClass.getName(), true, innerClass.getClassLoader());
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException(e);
    }
  }

  public static void main(String... argv) throws ClassNotFoundException
  {
    Class<Foo> fooClass = loadClass();
    initializeClass(fooClass);

    URLClassLoader myClassLoader = ((URLClassLoader) InitializeClassMultipleTimes.class
        .getClassLoader());
    URL[] urls = myClassLoader.getURLs();
    for (int i = 0; i < 2; i++) {
      URLClassLoader newClassLoader = new URLClassLoader(urls,
          null);  // parent=bootstrap
      System.out.format("%nLoading class using another class loader%n",
          Foo.class.getSimpleName());
      Class<?> fooClassAgain = Class
          .forName(fooClass.getName(), false, newClassLoader);
      initializeClass(fooClassAgain);
    }
  }
}