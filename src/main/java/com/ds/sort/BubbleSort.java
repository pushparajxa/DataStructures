package com.ds.sort;

//Stable
public class BubbleSort {
    private static void sortArray(int[] arr) {
        for(int i = 0;i<arr.length-1;i++) {
            for(int j = 0;j<arr.length-1-i;j++) {
                if(arr[j]>arr[j+1]){
                    swap(j,j+1,arr);
                }
            }
        }
    }

    static void swap(int pos1,int pos2,int[] arr) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}


