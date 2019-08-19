
package com.lang.execution;

//https://docs.oracle.com/javase/specs/jls/se7/html/jls-12.html


public class InitializationTest {

  public static void main(String[] args) {
    //sub_clas subClas = new sub_clas();
    System.out.println(sub_clas.val); // Doesn't initialize sub_clas only super clas
  }


  private static class super_clas{

    static String val="VALUE";

    private int a=20;

    private int b =30;

     {
      System.out.println("Initializer from super class"+"a="+a+"and b="+b);
    }

  }

  private static class sub_clas extends super_clas{

    static {
      System.out.println("This is a static initializer from sub class");
    }

    {
      System.out.println("Intializer from sub class");
    }

  }



}

