
package com.ds.dynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

public class LongestCommonSubSequence {

  public static void main(String[] args) {

    LongestCommonSubSequence longestCommonSubSequence = new LongestCommonSubSequence();

  /*  char [] str1 = "AGGTAB".toCharArray();
    char [] str2 = "GXTXAYB".toCharArray();*/


    char [] str1 = "cab".toCharArray();
    char [] str2 = "abac".toCharArray();

   /* int lcs1 = longestCommonSubSequence.longestCommonSubSequence(str1,str2);
    System.out.println(lcs1);

    int lcs = longestCommonSubSequence.longestCommonSubSequence2(str1,str2);
   System.out.println(lcs);
*/
    longestCommonSubSequence.printLongestCommonSubSequence(str1,str2);
    longestCommonSubSequence.printLongestCommonSubSequence2(str1,str2);

  }

  int longestCommonSubSequence(char [] str1, char [] str2){
    if(str1.length==0 || str2.length==0){
      return 0;
    }

    int [][] dp = new int[str1.length][str2.length];

    for(int i=0;i<str1.length;i++){
      for(int j=0;j<str2.length;j++){

        if(str1[i] == str2[j]){

          if(isOutOfBound(i)||isOutOfBound(j)){
            dp[i][j] = 1;
          }else{
            dp[i][j] = dp[i-1][j-1] +1;
          }

        }else{

           if(isOutOfBound(i) && isOutOfBound(j)){
             dp[i][i] =0;
           }else if(isOutOfBound(i)){
             dp[i][j] = dp[i][j-1];
           }
           else  if(isOutOfBound(j)){
             dp[i][j] = dp[i-1][j];
           }else{
             dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
           }

        }

      }
    }

    for (int i = 0; i < str1.length; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }
    return dp[str1.length-1][str2.length-1];
  }


  int longestCommonSubSequence2(char [] str1, char [] str2){
    if(str1.length==0 || str2.length==0){
      return 0;
    }

    int [][] dp = new int[str1.length+1][str2.length+1];

    for(int i=1;i<=str1.length;i++){
      for(int j=1;j<=str2.length;j++){

        if(str1[i-1] == str2[j-1]){
            dp[i][j] = dp[i-1][j-1] +1;
        }else{
            dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }

      }
    }

    for (int i = 0; i <= str1.length; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }
    return dp[str1.length][str2.length];
  }

  private   void  printLongestCommonSubSequence(char [] str1, char [] str2){
    if(str1.length==0 || str2.length==0){
      System.out.println("");
    }

    int [][] dp = new int[str1.length][str2.length];

    for(int i=0;i<str1.length;i++){
      for(int j=0;j<str2.length;j++){

        if(str1[i] == str2[j]){

          if(isOutOfBound(i)||isOutOfBound(j)){
            dp[i][j] = 1;
          }else{
            dp[i][j] = dp[i-1][j-1] +1;
          }

        }else{

          if(isOutOfBound(i) && isOutOfBound(j)){
            dp[i][i] =0;
          }else if(isOutOfBound(i)){
            dp[i][j] = dp[i][j-1];
          }
          else  if(isOutOfBound(j)){
            dp[i][j] = dp[i-1][j];
          }else{
            dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
          }

        }

      }
    }

    Stack<Character> lcs = new Stack<>();
    int i=str1.length-1, j=str2.length-1;
    while(i>=0 && j>=0){

      if(str1[i]==str2[j]){
        lcs.add(str1[i]);
        i=i-1;j=j-1;
      }else if(i>0 && j>0){
        if(dp[i-1][j]>=dp[i][j-1]){
          i=i-1;
        }else{
          j=j-1;
        }
      }
    }

    StringBuilder lcsString = new StringBuilder();
    while(!lcs.isEmpty()){
      lcsString.append(lcs.pop());
    }

    System.out.println("LCS String = "+lcsString.reverse().toString());

  }

  private   void  printLongestCommonSubSequence2(char [] str1, char [] str2){
    if(str1.length==0 || str2.length==0){
      System.out.println("");;
    }

    int [][] dp = new int[str1.length+1][str2.length+1];

    for(int i=1;i<=str1.length;i++){
      for(int j=1;j<=str2.length;j++){

        if(str1[i-1] == str2[j-1]){
          dp[i][j] = dp[i-1][j-1] +1;
        }else{
          dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }

      }
    }
    Stack<Character> lcs = new Stack<>();
    int i=str1.length, j=str2.length;
    while(i>0 && j>0){

      if(str1[i-1]==str2[j-1]){
        lcs.add(str1[i-1]);
        i=i-1;j=j-1;
      }else{
        if(dp[i-1][j]>=dp[i][j-1]){
          i=i-1;
        }else{
          j=j-1;
        }
      }
    }

    StringBuilder lcsString = new StringBuilder();
    while(!lcs.isEmpty()){
      lcsString.append(lcs.pop());
    }

    System.out.println("LCS String = "+lcsString.reverse().toString());

  }

  private boolean isOutOfBound(int index) {
    return index<=0 ;

  }


}
