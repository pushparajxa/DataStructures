
package com.dynamicProgramming;

import java.util.Arrays;

public class CoinChange {

  public static void main(String[] args) {
    int [] input = {2,5,3,6};
    int sum = 10;

    int result = getResult(input,sum);

    System.out.println(result);


  }

  static int getResult(int[] input, int sum){
    int [][] dp = new int[sum+1][input.length+1];

    for (int i = 0; i < input.length+1; i++) {
      dp[0][i] = 1;
    }

    for (int i = 1; i < sum+1 ; i++) {
      dp[i][0] = 0;
    }

    for (int i = 1; i < input.length+1 ; i++) {
      for (int j = 1; j < sum+1; j++) {
        if(input[i-1]>sum){
          dp[j][i] = dp[j][i-1];
        }else{
          int l = j;
          while(l>=0){
            dp[j][i] += dp[l][i-1];
            l = l - input[i-1];
          }
        }

      }
    }

    for (int i = 0; i < sum+1; i++) {
      System.out.println(i +"  "+ Arrays.toString(dp[i]));
    }

    return dp[sum][input.length];
  }

}
