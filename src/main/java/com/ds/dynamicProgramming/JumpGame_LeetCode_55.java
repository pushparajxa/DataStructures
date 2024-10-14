/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

public class JumpGame_LeetCode_55 {
    
    public boolean canJump2(int[] nums) {
        int len = nums.length;
        if(len==1)
            return true;
        
        int dest[]= new int[len];
        dest[len-1] = len-1;
        for(int i=len-2;i>=0;i--){
            int max = i;
            for(int k =1;k<=nums[i];k++){
                if(k+i<len){
                    max = Math.max(dest[k+i],max);
                }
                if(max == len -1)
                    break;
            }
            dest[i] = max;
        }
        
        //System.out.println(Arrays.toString(dest));
        if(dest[0]!=len-1)
            return false;
        else
            return true;
        
    }
    
    public boolean canJump(int[] nums){
        int len = nums.length;
        int last = len-1;
        
        for(int i=len-1;i>=0;i--){
            if(nums[i] + i >=last)
                last = i;
            
        }
        
        return last==0;
        
        
        
    }
}
