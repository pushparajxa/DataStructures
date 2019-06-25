
package com.dynamicProgramming;

import java.util.Arrays;

//Find a subset in a given set with given sum
public class SubSetSumProblem {

  public static void main(String[] args) {
    int input[] = {4, 1, 10, 12, 5, 2};

    int sumToFind = 7;

    int sum=0;
    for (int i = 0; i <input.length ; i++) {
      sum += input[i];
    }

    boolean dp[][] = new boolean[sum+1][input.length+1];

    for (int i = 0; i < input.length+1; i++) {
      dp[0][i] = true;
    }

    for (int i = 1; i < sum+1; i++) {
      dp[i][0] = false;
    }

    for (int i = 1; i <sum+1 ; i++) {
      for (int j = 1; j < input.length+1; j++) {
        if(input[j-1]>i){
          dp[i][j] = dp[i][j-1];
        }else {
          dp[i][j] = dp[i][j - 1] || dp[i - input[j - 1]][j - 1];
        }
      }
    }

    for (int i = 0; i < sum+1; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }

    for (int i = 1; i < input.length+1; i++) {
      if(dp[sumToFind][i]==true){
        System.out.println("Sum is possible with elements at index="+i);
        break;
      }

    }

  }

}
