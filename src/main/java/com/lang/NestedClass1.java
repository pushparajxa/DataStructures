
package com.lang;


//Non-static nested classes are called inner classes
//static nested classes are called as static nested class
public class NestedClass1 {

  private int a=10;
  private static int b=30;

  private int getMeInt(){
    return a;
  }

  private void accessStaticNestedClassPrivateMethodsAndVariables(){
    StaticNestedClass staticNestedClass = new StaticNestedClass();
    int d = staticNestedClass.d;// can access private members of nested static
    System.out.println("From Super clas accessing Nested Static Class private variable int d="+d);
    d = staticNestedClass.getMeStaticInt();
    System.out.println("From Super clas accessing Nested Static Class private method int getMeStaticInt="+d);
    int c = StaticNestedClass.c;
    c= StaticNestedClass.getNestedStaticInt();

  }

  private static int getStaticInt(){
    return b;
  }

  public static class StaticNestedClass{

    private static int c=30;
    private int d = 40;

    private int getMeStaticInt(){

      return d;
    }

    private static int getNestedStaticInt(){
      return c;
    }

    private void accessOuterClassPrivateVariablesAndMethods(){
      NestedClass1 nestedClass = new NestedClass1();
      int a = nestedClass.a;
      a = nestedClass.getMeInt();

      int b = NestedClass1.b;
      b = NestedClass1.getStaticInt();
    }

  }

  public static void main(String[] args) {
    NestedClass1 nestedClass = new NestedClass1();
    nestedClass.accessStaticNestedClassPrivateMethodsAndVariables();
  }

}
