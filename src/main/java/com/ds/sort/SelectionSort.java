
package com.ds.sort;

import java.util.Scanner;

//Not Stable
public class SelectionSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] unsorted = new String[n];
        for(int unsorted_i=0; unsorted_i < n; unsorted_i++){
            unsorted[unsorted_i] = in.next();
        }
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(unsorted[i]);
        }
        sortArray(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

    }

    private static void sortArray(int[] arr) {
        for(int i=0;i<arr.length;i++){
            int min = findMinIndex(i,arr);
            swap(i,min, arr);
        }
    }

    private static void swap(int index, int min, int[] arr){
        int temp = arr[index];
        arr[index ] = arr[min];
        arr[min] = temp;
    }

    private  static int findMinIndex(int start, int[] arr){
        int minIndex = start;
        int minValue= arr[start];
        for(int i=start+1;i<arr.length;i++){
            if(arr[i]<minValue){
                minValue = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
