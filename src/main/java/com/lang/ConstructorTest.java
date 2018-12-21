
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
   /* child c  =new child();
    c.test();
    c.mth2();
    c.mth();*/
   test1 te = test1.getTest1();
  }


}

interface gh{
    void th();
}


class test1 {
  private test1(){
    System.out.println("test1 class constructor is called");
  }

  public static test1 getTest1(){
    test1 te = new test1();
    return te;
  }
}

