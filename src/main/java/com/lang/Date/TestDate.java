
package com.lang.Date;

import java.util.HashMap;
import java.util.Map;

public class TestDate {
  private static Map<Integer,String> timeSlots= new HashMap<>();

  static {
    for(int i=0;i<23;i++){
      timeSlots.put(i,String.format("%d:00 - %d:00",i,i+1));
    }
    timeSlots.put(23,String.format("23:00 - 0:00"));
  }
  public static void main(String[] args) {
    System.out.println(timeSlots.toString());
  }
}
