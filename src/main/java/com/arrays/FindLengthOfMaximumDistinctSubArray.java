
package com.arrays;

import java.util.HashMap;

public class FindLengthOfMaximumDistinctSubArray {

  public static void main(String[] args) {
    int input[] = {1, 3, 4, 1, 5, 6, 4, 3, 1, 2, 3};
    input= new int[]{4,4,4,4};
    System.out.println(getMaxIncreasigLength(input));
  }


  static int getMaxIncreasigLength(int arr[]) {

    if (arr.length == 0) {
      return 0;
    }
    if (arr.length == 1) {
      return 1;
    }

    int soFarMax = 0;
    int soFarMaxWindowStart=0;
    int soFarMaxWindowEnd=0;
    int windowStart = 0;
    int windowEnd = 0;


    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      int val = arr[i];

      if (map.containsKey(val)) {
        int existingIndex = map.get(val);
        if (existingIndex < windowStart) {
          windowEnd = i;

          map.put(val, i);
        } else {
          //Window should end
          map.put(val, i);
          if (soFarMax < windowEnd - windowStart + 1) {
            soFarMax = windowEnd - windowStart + 1;
            soFarMaxWindowStart = windowStart;
            soFarMaxWindowEnd = windowEnd;
          }

          windowStart = existingIndex + 1;
          windowEnd = i;

        }
      } else {
        map.put(val, i);
        windowEnd = i;
      }

    }
    if (soFarMax < windowEnd - windowStart + 1) {
      soFarMax = windowEnd - windowStart + 1;
      soFarMaxWindowStart = windowStart;
      soFarMaxWindowEnd = windowEnd;
    }

    System.out.println("Start="+soFarMaxWindowStart+"(Inclusive)  -- End="+soFarMaxWindowEnd+
        "(Inclusive)");
    return soFarMax;

  }
}
