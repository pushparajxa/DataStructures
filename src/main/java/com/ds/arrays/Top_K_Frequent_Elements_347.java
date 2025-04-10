/*
 ** COPYRIGHT **
 */
package com.ds.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Top_K_Frequent_Elements_347 {
    
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            map.merge(nums[i], 1, (oldVal, newVal) -> oldVal + newVal);
        }
        //  System.out.println(map);
        
        ArrayList<Integer> list = new ArrayList(map.keySet());
        
        int s = quickSelect(map, list, 0, list.size() - 1, k);
        // System.out.println("s is="+ s);
        
        return list.subList(s, list.size()).stream().mapToInt(x -> x).toArray();
        
        
    }
    
    
    private int quickSelect(HashMap<Integer, Integer> map,
                            ArrayList<Integer> nums,
                            int start,
                            int end,
                            int k) {
        // System.out.println(nums +" start=" + start +" end =" + end);
        Random random = new Random();
        
        int pivotIndex = random.nextInt(start, end + 1);
        //System.out.println("Random ==" + pivotIndex);
        
        int pivot = map.get(nums.get(pivotIndex));
        
        swap(nums, pivotIndex, end);
        
        int j = start; // j is the index in the array at which we swap the element at i that is
        // less than pivot.
        for (int i = start; i < end; i++) {
            if (map.get(nums.get(i)) <= pivot) {
                swap(nums, j, i);
                j++;
            } else {
                // do nothing. We move forward as this element is greater than the pivot.
            }
        }
        
        if (map.get(nums.get(j)) >= map.get(nums.get(end))) {
            /*
                nums[j] ==  nums[end] happens in the case of 1 1 2  (j is 2 and end is 2)
                nums[j] >  nums[end] happens in case of 7 7 2 (j is 0 and end is 2)
             */
            swap(nums, end, j);
        }
        
        // System.out.println("After main instructions"+ nums + " j ==" + j);
        
        if (end - j + 1 == k) {
            return j;
        } else if (end - j + 1 < k) {
            return quickSelect(map, nums, start, j - 1, k - (end - j + 1));
        } else {
            return quickSelect(map, nums, j, end, k);
        }
        
        
    }
    
    private void swap(ArrayList<Integer> list, int x, int y) {
        int temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }
    
}
