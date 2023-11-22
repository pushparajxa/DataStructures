
package com.ds.strings;

//https://www.geeksforgeeks.org/?p=19155/
public class LongestPalindromicSubSequence {

  public static void main(String[] args) {
    int res = longestPalindromicSubSequence("GEEKS FOR GEEKS"); // asnwer should be 7,
    //For input "GEEKSFORGEEKS";  answer is 5
    System.out.println(res);
  }

  static int longestPalindromicSubSequence(String input){
    if(input.isEmpty()|| input.length()==1){
      System.out.println(input);
      return input.length();
    }

    char[] chars = input.toCharArray();
    int n = input.length(),k;
    String maxString ="";

    int [][] matrix = new int[n][n];
    String [][] strings = new String[n][n];
    int max=0;

    for (int i = 0; i <= n - 1; i++) {
      for (int j = 0; j <= n-i-1; j++) {
        k = j+i;
        if(i==0){
          matrix[j][k] = 1;
          strings[j][k] = String.valueOf(chars[k]);
        }else if(chars[j]==chars[k]){
          if(k>j+1){
            if(matrix[j+1][k-1]==0){
              //Handle the case of EKGE
              matrix[j][k] = 1 +2;
              strings[j][k] = chars[j]+""+chars[j+1]+""+chars[k];
            }else{
              matrix[j][k] = matrix[j+1][k-1] +2;
              strings[j][k] = chars[j]+strings[j+1][k-1]+chars[k];
            }
          }else{
            matrix[j][k] = 2;
            strings[j][k] = chars[j]+""+chars[k];
          }
          if(max<matrix[j][k]){
            max = matrix[j][k];
            maxString = strings[j][k];
          }
        }else{
          if(k>j+1){
            matrix[j][k] = Math.max(matrix[j][k-1], matrix[j+1][k]);
            if(matrix[j][k] == matrix[j][k-1]){
                strings[j][k] =strings[j][k-1];
            }else{
              strings[j][k] = strings[j + 1][k];
            }
          }else{
            matrix[j][k] = 0;
            strings[j][k] ="";
          }
        }

      }
    }

    System.out.println(maxString);

    return max;

  }
}
