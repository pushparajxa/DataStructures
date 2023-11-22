
package com.ds.arrays;

import java.util.Scanner;

public class SubArrayWithGivenSum {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();

    while(n-- >0){
      int arraySize = scanner.nextInt();
      int sumToMatch = scanner.nextInt();

      int i=0;
      int array[] = new int[arraySize];
      while(i<arraySize){
        array[i] = scanner.nextInt();
        i++;
      }
      getSubArrayWithTheSum(array,sumToMatch);

    }


    int arr[] = {15, 2, 4, 8, 9, 5, 10, 23};
    getSubArrayWithTheSum(arr,23);
  }

  static void getSubArrayWithTheSum(int[] input,int toMatch){
    int start=0;
    int sum=0;

    for (int i = 0; i < input.length; i++) {
      sum = sum +input[i];
      if(sum<toMatch){
      }else if(sum == toMatch){
        System.out.println(start+" "+i);
        return;
      }else{

        do {
          sum = sum - input[start];
          start++;
        }while(sum>toMatch && start<=i);

        if(sum == toMatch){
          System.out.println(start+" "+i);
          return;
        }
      }

    }

    System.out.println(-1);
    
  }

}
