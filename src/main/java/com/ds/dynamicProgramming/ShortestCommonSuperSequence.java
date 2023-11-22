
package com.ds.dynamicProgramming;

import java.util.Stack;

/*https://leetcode.com/problems/shortest-common-supersequence/ */
public class ShortestCommonSuperSequence {


  public static void main(String[] args) {

    ShortestCommonSuperSequence shortestCommonSuperSequence = new ShortestCommonSuperSequence();
    String result = shortestCommonSuperSequence.getLongestCommonSubSequence("abac".toCharArray(),
        "cab".toCharArray());
    System.out.println(result);
    System.out.println(shortestCommonSuperSequence.getShortestSuperSequence2("abac","cab"));

  }

  public String getShortestSuperSequence2(String string1, String string2){

    char[] str1 = string1.toCharArray();
    char[] str2 = string2.toCharArray();

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

   StringBuilder lcs = new StringBuilder();
    int i=str1.length, j=str2.length;
    while(i>0 && j>0){

      if(str1[i-1]==str2[j-1]){
        lcs.append(str1[i-1]);
        i=i-1;j=j-1;
      }else{
        if(dp[i-1][j]>=dp[i][j-1]){
          lcs.append(str1[i-1]);
          i=i-1;
        }else{
          lcs.append(str2[j-1]);
          j=j-1;
        }
      }
    }

    while(i>0){
      lcs.append(str1[i-1]);
      i--;
    }

    while(j>0){
      lcs.append(str2[j-1]);
      j--;
    }

    return lcs.reverse().toString();

  }

  public String getShortestSuperSequence(String string1, String string2){

    char[] str1 = string1.toCharArray();

    char[] str2= string2.toCharArray();


    String lcs = getLongestCommonSubSequence(str1, str2);

    int k=0;
    char [] lcsArray = lcs.toCharArray();
    StringBuilder[] builder = new StringBuilder[lcsArray.length+1];

    for (int i = 0; i < lcsArray.length+1; i++) {
      builder[i] = new StringBuilder();
    }

    for (int i = 0; i < str1.length; i++) {
     if(k< lcsArray.length && lcsArray[k]==str1[i]){
      k++;
     }else{
       builder[k].append(str1[i]);
     }

    }

    k=0;
    for (int i = 0; i < str2.length; i++) {
      if(k< lcsArray.length && lcsArray[k]==str2[i]){
        k++;
      }else{
        builder[k].append(str2[i]);
      }

    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < lcsArray.length; i++) {
      result.append(builder[i]).append(lcsArray[i]);
    }
    result.append(builder[lcsArray.length]);

    return result.toString();

  }


  private   String  getLongestCommonSubSequence(char [] str1, char [] str2){
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
          lcs.push(str1[i]);
          i=i-1;
        }else{
          lcs.push(str2[j]);
          j=j-1;
        }
      }
    }
    while(i>=0){
      lcs.push(str1[i]);
      i--;
    }

    while(j>=0){
      lcs.push(str2[j]);
      j--;
    }

    StringBuilder lcsString = new StringBuilder();
    while(!lcs.isEmpty()){
      lcsString.append(lcs.pop());
    }


   return lcsString.toString();

  }

  private boolean isOutOfBound(int index) {
    return index<=0 ;

  }

}
