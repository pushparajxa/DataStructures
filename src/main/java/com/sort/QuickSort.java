
package com.sort;

public class QuickSort {
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
                SortUtils.swap(i,j,arr);
                j--;
            }
        }
        if(arr[i]>pivot) {
            SortUtils.swap(i,end,arr);
            return i;
        }else {
            SortUtils.swap(i+1,end,arr);
            return i+1;
        }
    }
}
