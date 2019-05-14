
package com.arrays;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

//https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
public class SumOfMaximumOfAllContinguousSubArraysOfSizeK {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int n =  scanner.nextInt();

    while(n-- >0 ){
      int size = scanner.nextInt();
      int k = scanner.nextInt();
      int [] arr = new int[size];

      int i=0;
      while(i<size){
        arr[i] = scanner.nextInt();
        i++;
      }
      getSum(arr,k);
    }

    int input[] = new int[]{1, 2, 3 ,1 ,4, 5 ,2, 3, 6};
    getSum(input,3);
  }

  //O(nlogk) solutions can be made by using max-heap, balanced-bst etc..

  //O(n) solution
  static void getSum(int [] input,int k){

    Deque<Integer> deque = new LinkedList<>();

    for (int i = 0; i < k; i++) {

      while(!deque.isEmpty() && input[deque.peekLast()]<=input[i]){
        deque.removeLast();
      }

      deque.addLast(i);

    }
    System.out.print(input[deque.peekFirst()]);

    for(int i=k;i<input.length;i++){

      //Remove elements from previous window

      while(!deque.isEmpty() && deque.peekFirst()<=i-k){
        deque.removeFirst();
      }


      while(!deque.isEmpty() && input[deque.peekLast()]<=input[i]){
        deque.removeLast();
      }

      deque.addLast(i);

      System.out.print(" "+input[deque.peekFirst()]);

    }
    System.out.println();


  }

}
