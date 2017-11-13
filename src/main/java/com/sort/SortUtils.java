
package com.sort;

public class SortUtils {
    public static String EMPTY_ARRAY="Array can't be empty";
    static int findMinimum(int[] arr) {
        if(arr.length==0)
            throw new RuntimeException(EMPTY_ARRAY);
        int min = arr[0];
        for(int i = 0;i<arr.length;i++) {
            if(arr[i]<min) {
                min = arr[i];
            }
        }
        return min;
    }

    static int findMaximum(int[] arr) {
        if(arr.length==0)
            throw new RuntimeException(EMPTY_ARRAY);
        int max = arr[0];
        for(int i = 0;i<arr.length;i++) {
            if(arr[i]>max) {
                max = arr[i];
            }
        }
        return max;
    }

    static void swap(int from,int to,int[] arr) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

}
