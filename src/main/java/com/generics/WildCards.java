
package com.generics;

import java.util.ArrayList;
import java.util.List;


//http://tutorials.jenkov.com/java-generics/wildcards.html
//http://www.programmr.com/blogs/using-bounds-java-generics

public class WildCards {
/*
  public static void main(String[] args) {
    *//**** UnKnown wildcard **//*
    List<?> listAny = new ArrayList<Integer>();
    listAny.add(23); //can't  add elements can only read
    listAny.add(null); //only  null can be added.. since null doesn't have any type
    Object object = listAny.get(0);

    *//** Extends Wildcard **//*
    List<? extends A> extendsA = new ArrayList<B>(); //extends means that object in this case
    // extendsA is a producer, from which objetc are taken
    //http://www.programmr.com/blogs/using-bounds-java-generics

    extendsA.add(new B()); //can't add elements can only read

    extendsA = new ArrayList<A>();

    extendsA.add(new C());

    extendsA.add(null);//only nulls can be added .

    A a = extendsA.get(0);

    *//** Super wildcard **//*

    List<? super A> superA = new ArrayList<Z>();
    superA.add(new B());
    superA.add(new C());
    superA.add(new A());
    superA.add(new Z()); //Illegal since we can't know exact type of List object created

    Object object1 = superA.get(0);
    A a2 =  superA.get(1);// Can't read unless assigned to Object, since we don't know with which
    // super type does the ArrayList was created. Since Object is superclass of all, we can
    // assign it to Object

  }*/

  private static class Z{

  }
  private static class A extends Z{

  }

  private static class B extends A{

  }

  private static  class C extends A{

  }

}
