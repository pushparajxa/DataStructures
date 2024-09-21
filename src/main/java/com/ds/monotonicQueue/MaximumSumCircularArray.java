/*
 ** COPYRIGHT **
 */
package com.ds.monotonicQueue;

public class MaximumSumCircularArray {
    
    public int maxSubarraySumCircular(int[] arr) {
        int len = arr.length;
        if (arr.length == 1) {
            return arr[0];
        }
        int max_sum_so_far = Integer.MIN_VALUE;
        int max_up_to_here = 0;
        int i = 0, j = 0;
        while (i < len) {
            if (j >= len && i == j % len ) {
                    i++;
                    if(i>=len) {
                        return max_sum_so_far;
                    }
                    else {
                        max_up_to_here = 0;
                        j = i;
                    }
            }
            
            max_up_to_here += arr[j % len];
            
            if (max_sum_so_far < max_up_to_here) {
                max_sum_so_far = max_up_to_here;
            }
            if (max_up_to_here < 0) {
                max_up_to_here = 0;
                if(j%len +1 >i) {
                    i = j % len + 1;
                    j = i;
                }
                else{
                    j++;
                }
            }
            else {
                j++;
            }
        }
        
        return max_sum_so_far;
    }
    
    public static void main(String[] args) {
        MaximumSumCircularArray maximumSumCircularArray = new MaximumSumCircularArray();
       int res =  maximumSumCircularArray.maxSubarraySumCircular(new int[]{1, -6, 7,4});
        System.out.println(res);
    }
}