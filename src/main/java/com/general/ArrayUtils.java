
package com.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayUtils {

    public static String EMPTY_ARRAY = "Array can't be empty";

    public static int findMinimum(int[] arr) {
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

    public static int findMaximum(int[] arr) {
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

    public static void swap(int from,int to,int[] arr) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    public static int[] reverseArray(int[] arr) {
        int i = 0, j = arr.length-1;
        for(;i<arr.length/2;) {
            swap(i,j,arr);
            i++;
            j--;
        }
        if(i!=arr.length/2) {
            swap(i,arr.length/2,arr);
        }
        return arr;
    }

    public static Set<Integer> toSet(int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i : arr) {
            hashSet.add(i);
        }
        return hashSet;
    }

    public static ArrayList<Integer> toList(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : arr) {
            list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3};
        //reverseArray(arr);
        // System.out.println(Arrays.toString(arr));
        Arrays.asList(arr);
    }

    public static boolean contains(int k, int[] arr){
        if(arr==null)
            throw new RuntimeException("Given array is null");
        for(int i = 0;i<arr.length;i++) {
            if(arr[i] == k){
                return true;
            }
        }
        return false;
    }
}
