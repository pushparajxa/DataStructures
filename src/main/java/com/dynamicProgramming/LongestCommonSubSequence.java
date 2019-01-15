
package com.dynamicProgramming;

public class LongestCommonSubSequence {

  public static void main(String[] args) {

    LongestCommonSubSequence longestCommonSubSequence = new LongestCommonSubSequence();

    char [] str1 = "AGGTAB".toCharArray();
    char [] str2 = "GXTXAYB".toCharArray();
    int lcs = longestCommonSubSequence.longestCommonSubSequence(str1,str2);
    System.out.println(lcs);

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
    return dp[str1.length-1][str2.length-1];
  }


  private boolean isOutOfBound(int index) {
    return index<=0 ;

  }

  void printLCSStrings(char [] str1, char[] str2){

  }

}
