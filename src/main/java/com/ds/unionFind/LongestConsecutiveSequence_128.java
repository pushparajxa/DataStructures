/*
 ** COPYRIGHT **
 */
package com.ds.unionFind;

import java.util.HashMap;

public class LongestConsecutiveSequence_128 {
    
    
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if(len == 0 || len ==1)
            return len;
        if(len ==2){
            return Math.abs(nums[0]-nums[1]) ==1 ? 1:0;
            
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int temp=0;
        
        for(int i:  nums){
            System.out.println("Processing--i--" + i);
            if(map.containsKey(i+1)){
                int k = i+1;
                int cnt=1;
                while(map.containsKey(k)){
                    map.put(k,i);
                    k = k+1;
                    cnt++;
                }
                result = Math.max(result, cnt);
                System.out.println(i+ "--" + result);
            }
            
            if(map.containsKey(i-1)){
                int k=i;
                int cnt =0;
                while(map.containsKey(k)){
                    map.put(k,i-1);
                    k++;
                    cnt++;
                }
                
                k = i-1;
                while(map.containsKey(k)){
                    cnt++;
                    k = k -1;
                }
                result = Math.max(cnt, result);
            }
            
            
            if(!map.containsKey(i-1) && !map.containsKey(i+1))
                map.put(i,null);
            
            System.out.println(map);
            
        }
        
        return result;
        
        
    }
    
    public static void main(String[] args) {
        LongestConsecutiveSequence_128 longestConsecutiveSequence128 =
            new LongestConsecutiveSequence_128();
        
        
        int res = longestConsecutiveSequence128.longestConsecutive(new int[]{100,4,200,1,3,2});
        
        System.out.println(res);
        
    }
}