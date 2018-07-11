
package com.lang.streams;

import java.util.ArrayList;
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
  }
}
