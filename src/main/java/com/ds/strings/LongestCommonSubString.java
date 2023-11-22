
package com.ds.strings;

import java.util.Scanner;

/*
 https://en.wikipedia.org/wiki/Longest_common_substring_problem
 */
public class LongestCommonSubString {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);


    int lcsLength = getLCS("ABCDGH","ACDGHR");
    System.out.println(lcsLength);
  }


  static int getLCS(String str1, String str2){
    if(str1.isEmpty()|| str2.isEmpty()){
      return 0;
    }

    char[] char1 = str1.toCharArray();
    char[] char2 = str2.toCharArray();
    int result=0;

    int [][] matrix = new int[str1.length()][str2.length()];

    for(int i=0;i<char1.length;i++){
      for (int j = 0; j < char2.length; j++) {
        if(char1[i] == char2[j]){
          if(i-1>=0 && j-1 >=0){
            matrix[i][j] = matrix[i-1][j-1] +1;
          }else{
            matrix[i][j] =1;
          }
          if(matrix[i][j]>result){
            result = matrix[i][j];
          }
        }else{
          matrix[i][j]=0;
        }

      }
    }

    return result;
  }

}
