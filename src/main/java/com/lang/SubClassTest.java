package com.lang;

import java.io.Serializable;

public class SubClassTest {
  public static void main(String[] args) {

    SuperClass sc  = new SubClass();
    Serializable sb = new SubClass();
    System.out.println(sb instanceof Serializable);
  }

  private static class SuperClass implements Serializable {

  }

  private static class SubClass extends SuperClass{


  }
}
