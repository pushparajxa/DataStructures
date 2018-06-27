package com.lang.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsTest {
  public static void main(String[] args) {
    List<Integer> integers = new ArrayList<>();
    integers.add(23);
    integers.add(45);

    List<Integer> integers1 = new ArrayList<>();
    integers1.add(23);
    System.out.println("Before"+integers1);
    integers1.removeAll(integers);
    System.out.println("After"+integers1);
  }
}
