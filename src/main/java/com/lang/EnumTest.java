package com.lang;

public class EnumTest {
    public static void main(String[] args) {
    abc e = abc.MONDAY1;
    System.out.println(e.toString());
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

enum abc {
    SUNDDAY1,
    MONDAY1
  }
}
