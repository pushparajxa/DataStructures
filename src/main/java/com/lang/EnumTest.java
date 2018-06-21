package com.lang;

public class EnumTest {
    public static void main(String[] args) {
    abc e = abc.MONDAY1;
    System.out.println(e.toString());
    if (!giveBoolean()) {
      System.out.println("No boolean");
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
