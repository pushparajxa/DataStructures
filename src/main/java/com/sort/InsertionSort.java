package com.sort;

public class InsertionSort {

    private static void sortArray(int[] arr) {
        for(int i = 0;i<arr.length;++i) {
            int value = arr[i];
            boolean done = false;
            int j = i-1;
            while(!done && j>=0) {
                if(arr[j]>value) {
                    arr[j+1] = arr[j];
                }else {
                    arr[j+1] = value;
                    done = true;
                }
                j = j-1;
            }

            if(!done) {
                arr[0] = value;
            }
        }

    }

    private static void sortArray2(int[] arr) {
        for(int i = 0;i<arr.length;++i) {
            int index = findTheIndex(i,arr);
            shiftRight(index,i,arr);
        }

    }

    static int findTheIndex(int valIndex,int[] arr) {
        int value = arr[valIndex];
        int i;
        for(i = 0;i<valIndex;++i) {
            if(arr[i]>value) {
                return i;
            }
        }
        return 0;
    }

    static void shiftRight(int start,int end,int[] arr) {
        int val = arr[end];

        for(int i = end;i>start;--i) {
            arr[i] = arr[i-1];
        }

        arr[start] = val;
    }

    static void swap(int pos1,int pos2,int[] arr) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}
