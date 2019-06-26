
package com.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SubSetSumWithNegativeIntegers {

  public static void main(String[] args) {
    int[] input = {-4, -1, 3, 2};

    int negativeSum = 0;
    int positiveSum = 0;
    for (int i = 0; i < input.length; i++) {
      if (input[i] > 0) {
        positiveSum += input[i];
      } else {
        negativeSum += input[i];
      }
    }

    HashMap<Integer, boolean[]> dp = new HashMap<>();

    for (int i = negativeSum; i <= positiveSum; i++) {
      boolean array[] = new boolean[input.length + 1];
      array[0] = false;
      dp.put(i, array);
    }

    boolean[] zeroSum = new boolean[input.length + 1];
    for (int i = 0; i < input.length + 1; i++) {
      zeroSum[i] = true;
    }

    dp.put(0, zeroSum);

    for (int i = 1; i < input.length + 1; i++) {
      for (int j = negativeSum; j <= positiveSum; j++) {
        if (j != 0) {
          if (j - input[i - 1] < negativeSum
              || j - input[i - 1] > positiveSum) {
            dp.get(j)[i] = dp.get(j)[i - 1];
          } else {
            dp.get(j)[i] = dp.get(j)[i - 1] || dp.get(j - input[i - 1])[i - 1];
          }
        }
      }
    }

    List<Integer> keySet = new ArrayList(dp.keySet());
    Collections.sort(keySet);
    for (Integer key : keySet) {
      if (key >= 0) {

        System.out.println(key + "   " + Arrays.toString(dp.get(key)));
      } else {
        System.out.println(key + "  " + Arrays.toString(dp.get(key)));
      }
    }

  }

}
