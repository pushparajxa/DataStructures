
package com.dynamicProgramming;

import java.util.Arrays;

//https://www.geeksforgeeks.org/edit-distance-dp-using-memoization/
public class EditDistance {

  public static void main(String[] args) {
    String from = "saturday";
    String to = "sunday";
    char[] fChar = from.toCharArray();
    char[] tChar = to.toCharArray();

    int dp[][] = new int[fChar.length + 1][tChar.length + 1];

    dp[0][0] = 0;
    for (int i = 1; i < tChar.length + 1; i++) {
      dp[0][i] = i;
    }

    for (int i = 1; i < fChar.length + 1; i++) {
      dp[i][0] = i;
    }

    for (int i = 1; i < fChar.length + 1; i++) {
      for (int j = 1; j < tChar.length + 1; j++) {
        if (fChar[i - 1] == tChar[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(Math.min(1 + dp[i][j - 1], 1 + dp[i - 1][j - 1]), 1 + dp[i - 1][j]);
        }
      }
    }

    for (int i = 0; i < fChar.length + 1; i++) {
      if (i == 0) {
        System.out.println("\"\"" + " " + Arrays.toString(dp[0]));
      } else {
        System.out.println(fChar[i - 1] + "  " + Arrays.toString(dp[i]));
      }
    }

    System.out.println(dp[fChar.length][tChar.length]);
  }

}
