package com.ds.dynamicProgramming;

import java.util.Arrays;
// Kadane's Algorithm

public class maximalSubArray {
  
    //  https://en.wikipedia.org/wiki/Maximum_subarray_problem
    // https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/

    // This handles the case where all the elements are negative as well.
    static int printMaximumSum(int arr[]){
      if(arr.length==1){
        return arr[0];
      }
      int max_sum_so_far=Integer.MIN_VALUE;
      int max_up_to_here=0;
      for(int i = 0;i<arr.length;i++) {
        max_up_to_here += arr[i];

        if(max_sum_so_far<max_up_to_here){
          max_sum_so_far = max_up_to_here;
        }
        if(max_up_to_here<0){
          max_up_to_here =0;
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
        System.arraycopy(arr,start,subArray,0,arr.length);
        return subArray;

    }
    

    public static void main(String[] args) {

        int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
     //  System.out.println(printMaximumSum(arr));
    
        arr = new int[]{-22, -35, -4};
        
        System.out.println(printMaximumSum(arr));
      //  int[] subArrayWithMaxSum = subArrayWithMaxSum(arr);
        //System.out.println(Arrays.toString(subArrayWithMaxSum));
        // System.out.println(subArrayWithMaxSum(arr));
    }

}
