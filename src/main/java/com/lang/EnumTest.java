package com.lang;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class EnumTest<Integer> {

  public  interface Constnat{

  }
    public static void main(String[] args)
        throws ClassNotFoundException, UnsupportedEncodingException
    {
      System.out.println("£".getBytes("UTF-8"));
    abc e = abc.MONDAY1;
    System.out.println(e.toString());

    Class cls = abc.class;

      try {
        final Method valueOf = Enum.class.getMethod("valueOf");
        Object obje  = (Object)valueOf.invoke("SUNDAY1");
        System.out.println(obje);
      }
      // These can happen when users concoct enum-like classes
      // that don't comply with the enum spec.
      catch (InvocationTargetException |
          NoSuchMethodException     |
          IllegalAccessException ex )
      {
        System.out.println("Exception was thrown");
      }

    Enum.valueOf(abc.class, "MONDAY1");

    Class<? extends List> c1 =
          (Class<? extends List>)(Class.forName(Class.forName("com.lang.EnumTest").getName()+"$abc"));

    Class<? extends  String> cStr = (Class<? extends String>) Class.forName("com.lang.EnumTest");

      final Class<? extends String> aClass1 = (Class<? extends String>) Class
          .forName("java.lang.Integer");

      c1.getTypeName();
      TypeVariable<? extends Class<? extends List>>[] typeParameters = c1
          .getTypeParameters();

      List<String> ls = new ArrayList<>();

  /*  Class<List<? extends Integer>> cl=
        (Class<List<? extends Integer>>)(Class.forName(Class.forName("com.lang.EnumTest").getName()+"$abc"));*/

    Class<?> aClass = Class.forName("com.lang.EnumTest.Constnat");
    if (!giveBoolean()) {
      System.out.println("No boolean");
    }
    e = null;
    if(e == null){
      System.out.println("it is null");
    }else {
      System.out.println("it is not null");
    }
  };

  static Boolean giveBoolean() {
    return Boolean.FALSE;
  }


enum abc implements Constnat {
  SUNDDAY1(){

  },
  MONDAY1;

     abc(){

    };

  }
}
