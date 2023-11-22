
package com.ds.arrays;

import java.util.Scanner;

public class TrappingRainWater {

  public static void main(String[] args) {
    int input[] = new int[]{3,0,0,2,0,4};

   /* Scanner scanner = new Scanner(System.in);
    int n  = scanner.nextInt();
    while(n-- >0){
      int length = scanner.nextInt();
      int inpt[] = new int[length];
      int i=0;
      while(i<length){
        inpt[i] = scanner.nextInt();
        i++;
      }
      computeWater(inpt);
    }*/

    computeWater(input);

    computeAmount(input);
  }

//O(n) solution with O(n) Space
  static void computeWater(int [] input){

    int [] left = new int[input.length];
    int [] right = new int[input.length];

    int maxLeft =input[0];
    for (int i = 1; i < input.length-1; i++) {
      if(maxLeft<input[i]){
        maxLeft = input[i];
        left[i]=input[i];
      }else{
        left[i] = maxLeft;
      }
    }

    int maxRight=input[input.length-1];

    for (int i = input.length-2; i >=1; i--) {
      if(maxRight<input[i]){
        maxRight = input[i];
        right[i]=input[i];
      }else{
        right[i] = maxRight;
      }
    }

    int count=0;
    for (int i = 1; i < input.length-1; i++) {
      int lft = left[i];
      int rght = right[i];
      if(input[i]> lft || input[i] > rght){
        continue;
      }else{
        count = count + (Math.min(lft,rght)-input[i]);
      }
    }

    System.out.println(count);

  }

  //O(n) with O(1) space

  static void computeAmount(int[] input){

    int maxLeft=0, maxRight=0, count=0;

    int start=0, end = input.length-1;

    while(start<=end){
      if(input[start]<=input[end]){
        if(maxLeft<input[start]){
          maxLeft = input[start];
        }else{
          count = count+ maxLeft-input[start];
        }
        start++;
      }else{
        if(maxRight<input[end]){
          maxRight = input[end];
        }else{
          count = count +maxRight-input[end];
        }
        end--;
      }

    }
    System.out.println(count);

  }
}
