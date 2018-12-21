
package com.arrays;

import java.util.Arrays;

public class ToStringTest {

  enum ragas { ONE, TWO , THREE};

  public static void main(String[] args) {

    System.out.println(ragas.values().toString());

    System.out.println(Arrays.toString(ragas.values()));

    int [] vals = new int[]{1,2,3};

    System.out.println(Arrays.toString(vals));

    System.out.println(vals);

  }

}
