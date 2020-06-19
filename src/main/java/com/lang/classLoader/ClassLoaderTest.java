/*
 * Copyright 2020 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.lang.classLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 *  https://stackoverflow.com/a/25298212/1171533
 *  https://stackoverflow.com/questions/25187446/if-a-class-is-loaded-multiple-times-do-its-static-members-get-initialized-multi
 *
 * The following actions do not cause a class to be loaded:
 *
 *     Referring to a static final primitive field that is known at compile time.
 *     classLoader.getResource(className.replace('.', '/') + ".class")
 *
 * The following cause a class to be loaded (i.e., the .class file is parsed, and a Class<?> object is created):
 *
 *     Any line of code that refers to the class symbolically e.g. Foo.class
 *     Class.forName(String, false, ClassLoader)
 *     ClassLoader.loadClass(String)
 *     Loading a subclass or array of the class, or initializing a class whose code or method signatures refer to the class.
 *
 * The following cause a class to be initialized (i.e., the static blocks are executed):
 *
 *     Constructing a new instance
 *     Calling a static method
 *     Getting or setting a static field that is not a compile-time constant.
 *     Class.forName(String, true, ClassLoader) and Class.forName(String)
 *
 *
 */
public class ClassLoaderTest {

  static {
    System.out.format("  %s initialized by %s%n",
        ClassLoaderTest.class.getSimpleName(), ClassLoaderTest.class.getClassLoader());
  }

  static class TestClassLoader1 extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
      if (!name.equals("ClassLoaderTest")) {
        return super.loadClass(name);
      }
      try {
        InputStream in = ClassLoader.getSystemResourceAsStream("ClassLoaderTest.class");
        byte[] a = new byte[10000];
        int len  = in.read(a);
        in.close();
        return defineClass(name, a, 0, len);
      } catch (IOException e) {
        throw new ClassNotFoundException();
      }
    }
  }


  public static void main(String[] args) throws Exception {
    System.out.println("Executing Main Method");
    TestClassLoader1 testClassLoader1 = new TestClassLoader1();
    TestClassLoader1 testClassLoader11 = new TestClassLoader1();
    Class<?> c1 = testClassLoader1.loadClass("ClassLoaderTest");
    Class<?> c2 = testClassLoader11.loadClass("ClassLoaderTest");
    Class.forName("ClassLoaderTest", true, testClassLoader1);
    Class.forName("ClassLoaderTest", true, testClassLoader11);
    System.out.println(c1);
    System.out.println(c2);
    System.out.println(c1 == c2);
  }
}
