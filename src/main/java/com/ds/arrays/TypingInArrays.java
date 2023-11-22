
package com.ds.arrays;

import java.util.Arrays;

public class TypingInArrays {

  public static void main(String[] args) {
    String[] strings = {"hello", "first"};
    Object [] objects = strings;
    takeArrays(strings);
    takeArrays(strings);
  }

  static void takeArrays(Object[] objects){
    System.out.println(Arrays.toString(objects));
  }

}
