package com.lang.Native;

import java.util.Arrays;

public class IncrementTest {
  public static void main(String[] args) {

    int [] arr = new int[]{2,2};
    System.out.println(Arrays.toString(arr));
    int i=0;
    arr[i++] += i;
  //  arr[0] = arr[1] +
    System.out.println(Arrays.toString(arr));
  }
}
