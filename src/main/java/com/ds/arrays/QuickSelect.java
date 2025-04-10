
package com.ds.arrays;


import static com.general.ArrayUtils.swap;

import java.util.Random;

//Select kth smallest/largest element in an array
//http://www.cs.yale.edu/homes/aspnes/pinewiki/QuickSelect.html
public class QuickSelect {
    
    public static void main(String[] args) {
        
        QuickSelect quickSelect = new QuickSelect();
        int[] arr = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        // arr = new int[]{3,2,1,5,6,4};
        
        int res = quickSelect.findKthLargest(arr, 1);
        System.out.println(res);
        
    }
    
    public int findKthLargest(int[] nums, int k) {
        
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        
        if (len == 0) {
            return -1;
        }
        
        int start = 0, end = len - 1;
        
        int answer = quickSelect(nums, start, end, k);
        //System.out.println(answer);
        
        return answer;
        
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        Random random = new Random();
        
        int pivotIndex = random.nextInt(start, end + 1);
        // System.out.println("Random ==" + pivotIndex);
        
        int pivot = nums[pivotIndex];
        
        swap(pivotIndex, end, nums);
        
        int j = start; // j is the index in the array at which we swap the element at i that is
        // less than pivot.
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(j, i, nums);
                j++;
            } else {
                // do nothing. We move forward as this element is greater than the pivot.
            }
        }
        
        if (nums[j] >= nums[end]) {
            /*
                nums[j] ==  nums[end] happens in the case of 1 1 2  (j is 2 and end is 2)
                 nums[j] >  nums[end] happens in case of 7 7 2 (j is 0 and end is 2)
             */
            swap(end, j, nums);
        }
        
        if (end - j + 1 == k) {
            return nums[j];
        } else if (end - j + 1 < k) {
            return quickSelect(nums, start, j - 1, k - (end - j + 1));
        } else {
            return quickSelect(nums, j, end, k);
        }
        
        
    }
    
    
    public int findKthLargestUsingCountingSort(int[] nums, int k) {
        
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        
        for (int i : nums) {
            minVal = Math.min(minVal, i);
            maxVal = Math.max(maxVal, i);
        }
        
        int count[] = new int[maxVal - minVal + 1];
        
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - minVal]++;
        }
        
        //System.out.println(Arrays.toString(count));
        
        //System.out.println("minVal=" + minVal +" and maxVal=" + maxVal);
        
        int val = k;
        
        for(int i=count.length-1; i>=0;i--){
            val = val - count[i];
            //  System.out.println("val ==" + val +" and i="+ i + " and count[i]="+ count[i]);
            if(val<=0){
                return minVal+ i;
            }
        }
        
        return -1;
        
    }
    
    
    
}
