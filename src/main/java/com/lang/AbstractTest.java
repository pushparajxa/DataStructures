
package com.lang;

public class AbstractTest {
  public static void main(String[] args) {
    Child chld= new Child();
  }

  static abstract class abs{

    Prd prd = createPrd();

    abstract Prd createPrd();
  }

  static class Prd{

  }


  static class Child extends abs{

    @Override
    Prd createPrd() {
      System.out.println("hello from createPrd");
      return new Prd();
    }
  }

}
