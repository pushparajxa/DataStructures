
package com.lang.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamsTest {
  public static void main(String[] args) {
    List<String> strings = new ArrayList<>();
    strings.add("First");
    strings.add("Second");
    strings.add("Third");
    boolean res = strings
        .stream()
        .peek(x -> System.out.println("From peeking with x="+x))
        .map(String::isEmpty)
        .reduce(
            true,
            (val1, val2) -> {
              return val1 && val2;
            });

    try {
      Thread.sleep(1001);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(res);
    
   int answer =  Arrays.stream(new int[]{2,3,4,5}).reduce(0, (x,y)-> x+y);
    System.out.println(answer);
      
      System.out.println(Arrays.stream(new int[]{2,3,4,5}).max().getAsInt());
      System.out.println(Arrays.stream(new int[]{2,3,4,5}).min().getAsInt());
    
  }
  
  
/*  static int listSum(List<Integer> list) {
    
    return list.stream().filter(x-> x<10).sorted().red
    
  }*/
}
