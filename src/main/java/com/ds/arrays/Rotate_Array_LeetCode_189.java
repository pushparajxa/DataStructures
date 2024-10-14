/*
 ** COPYRIGHT **
 */
package com.ds.arrays;

// Rotate Array, Leet Code 189
public class Rotate_Array_LeetCode_189 {
    
    public void rotate2(int[] nums, int k) {
        
        int start = 0;
        int end = nums.length-1;
        k = k% nums.length;
        
        reverserArray(nums, start, end);
        
        start = 0;  end = k-1;
        
        reverserArray(nums, start, end);
        
        start = k;  end = nums.length-1;
        reverserArray(nums, start, end);
        
        
        
    }
    
    private void reverserArray(int [] nums, int start, int end){
        int temp;
        while(start!=end && start < end){
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
