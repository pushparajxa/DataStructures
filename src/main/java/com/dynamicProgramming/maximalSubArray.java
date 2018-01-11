package com.dynamicProgramming;

import java.util.Arrays;

public class maximalSubArray {

    //  https://en.wikipedia.org/wiki/Maximum_subarray_problem
    // https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/

    static int printMaximumSum(int arr[]){

        int max_sum_so_far=0;
        int max_up_to_here=0;
        for(int i = 0;i<arr.length;i++) {
            max_up_to_here += arr[i];
            if(max_up_to_here<0){
                max_up_to_here =0;
            }
            if(max_sum_so_far<max_up_to_here){
                max_sum_so_far = max_up_to_here;
            }
        }

        return max_sum_so_far;

    }

    static int[] subArrayWithMaxSum(int[] arr){

        int start=0,end=0,s=0,max_up_to_here=0,max_sum_so_far=0;

        for(int i = 0;i<arr.length;i++) {
          max_up_to_here += arr[i];
          if(max_up_to_here<0){
              max_up_to_here = 0;
              s=i+1;
          }
          if(max_sum_so_far<max_up_to_here){
              max_sum_so_far = max_up_to_here;
              start = s;
              end = i;
          }
        }
        int[] subArray = new int[end-start+1];
        System.arraycopy(arr,start,subArray,0,end-start+1);
        return subArray;

    }

    //Handle the case where all of the array elements are negative numbers
    //Handled in GeeksForGeeks solution

    //TODO : Fix This. Not working correctly
     static int printMaximumSum2(int arr[]){

        int max_sum_so_far=0;
        int max_up_to_here=0;
        for(int i = 0;i<arr.length;i++) {
            max_up_to_here = Math.max(arr[i],max_up_to_here+arr[i]);
            max_sum_so_far = Math.max(max_up_to_here,max_sum_so_far);
        }

        return max_sum_so_far;

    }

    public static void main(String[] args) {

        int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
       System.out.println(printMaximumSum(arr));
        System.out.println(printMaximumSum2(arr));
        int[] subArrayWithMaxSum = subArrayWithMaxSum(arr);
        System.out.println(Arrays.toString(subArrayWithMaxSum));
        // System.out.println(subArrayWithMaxSum(arr));
    }

}
