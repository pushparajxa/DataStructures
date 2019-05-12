
package com.strings;

import java.util.Scanner;

public class LongestPalindromicSubString {

  public static void main(String[] args) {

    int res = longestPalindromic("a");

    System.out.println(res);
  }


  static  int longestPalindromic(String input){
    if(input.length()==0 || input.length()==1){
      System.out.println(input);
      return input.length();
    }

    int matrix[][] = new int [input.length()][input.length()];

    char[] chars = input.toCharArray();

    int start=0 ,  end=0;

    int n = input.length(),k;

    int max = 0;

    for (int i = 0; i <= n-1; i++) {
      for (int j = 0; j <=n -i-1  ; j++) {
        k = j+i;
        if(i==0){
          matrix[j][k] = 1;
        }else if(chars[j] == chars[k] && matrix[j+1][k-1]!=-1){
           matrix[j][k] = matrix[j+1][k-1] +2;
           if(max<matrix[j][k]){
             max = matrix[j][k];
             start = j;
             end = k;
           }
        }else{
          matrix[j][k]= -1;
        }
      }

    }
    System.out.println(input.substring(start,end+1));

    return max;
  }

  static void readInput(){

    Scanner scanner = new Scanner(System.in);
    int n= scanner.nextInt();
    String input;
    while(n-- >0){
      input = scanner.next();
      longestPalindromic(input);
    }

  }

}
