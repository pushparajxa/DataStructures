
package com.sort;

import java.util.Arrays;

import static  com.general.ArrayUtils.*;
//In-Place quicksort with O(1) is not stable
public class QuickSort {
    public static void main(String[] args) {
        int[] ints = {1,2,7,7,7,3};
        ints = new int[]{6,5,4,3,2,1};
        ints = new int[]{11,2,3,4,5,5,5,0,0,8,9};
       // ints = new int[]{11,2,8,9};
        sortArray(ints);
        System.out.println(Arrays.toString(ints));
    }
    public static void sortArray(int[] arr) {
        if(arr.length>0)
            quickSort(arr,0,arr.length-1);
    }

    private static void quickSort(int[] arr,int start,int end) {
        if(start>=end)
            return;

        int pivotIndex = partition(arr,start,end);
        quickSort(arr,start,pivotIndex-1);
        quickSort(arr,pivotIndex+1,end);
    }

    //Partition by selecting the end element as the pivot
    private static int partition(int[] arr,int start,int end) {
        int i, j, pivot = arr[end];
        for(i = start,j = end-1;i!=j;) {
            if(arr[i]<=pivot) {
                i++;
            }else {
                if(arr[j]<pivot) {
                    swap(i, j, arr);
                }
                j--;
            }
        }
        if(arr[i]>pivot) {
            swap(i,end,arr);
            return i;
        }else if(arr[i]<pivot){
            /*
              5,4,3,2,6
              6 is the pivot,start = 0 , end =3
              i=j=3 the loop terminates.
              We can't say 2 as the pivot since elements left to it are not smaller than 2(5,4,3)

              Similarly
              11,2,8,9 .. 9 is the pivot and start =0, end = 2
              loop will terminate at below where i==j==1 that is at element 2.
              8,2,11,9

              Considering above cases, it is safe to swap next to the index where loop terminated
               with the end and declare i+1 as the pivot.
             */
           swap(i+1,end,arr);
           return i+1;
        }else{
            /*
              consider the case 11,2,2,2 with 2 as pivot and start = 0 , end =2
              loop terminates with i=i=1
              2,2,11,2
              We can't declare i =1 as the index, we have to swap the next to i with the end and
              return i+1;
             */
            swap(i+1,end,arr);
            return i+1;
        }
    }
}
