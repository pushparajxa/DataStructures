package com.lang;

public class LongTest {
  public static void main(String[] args) {
    Long l1 = 127l;
    Long l2 = 127l;
    System.out.println(l1==l2);

    Long l3 = 256l;
    Long l4= 256l;
    System.out.println(l3==l4);
    
    Integer i1 = 10;
    Integer i2 = 30;
  
    System.out.println(i1 > i2);
    
  }
}
