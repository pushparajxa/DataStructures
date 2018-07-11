
package com.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListTest {
  public static void main(String[] args) {
    List<String> list = new ArrayList();
    list.add("First");
    list.add("Two");
    String collect = list.stream().collect(Collectors.joining(","));
    System.out.println("Collected = "+collect);
    System.out.println(list.toString());
  }
}
