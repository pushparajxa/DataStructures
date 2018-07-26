
package com.lang;

public  class ConstructorTest {
   int x = 11;

   static void mth(){
    System.out.println("hello from mth");
   }

  ConstructorTest() {
   // System.out.println("Super.. x=" + x);
  }

  public void test(){
    System.out.println("From SuperClass");
  }
}

class child extends ConstructorTest{
   int x=12;

   static void mth2(){
    System.out.println("hello from mth2");
  }

  child(){
    //System.out.println("Child x="+x+"... and super x = "+super.x);
  }


  public static void main(String[] args) {
    child c  =new child();
    c.test();
    c.mth2();
    c.mth();
  }


}

interface gh{
    void th();
}

