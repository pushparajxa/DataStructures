
package com.ds.leetcode.slidingWindow;

public class LeetCode_1004_slower {
    public int longestOnes(int[] nums, int k) {
        int winS=0, winE=0;
        int cnt=0;
        int len = nums.length;
        int winC = 0;
        int max = Integer.MIN_VALUE;
        int firstZero=0;
        
        while(winS < len && winE <len) {
            
            if(nums[winE] ==1 ){
                winE ++;
                winC++;
            }
            else{
                if(k==0) {
                    winE++;
                    winS++;
                    if(max < winC) {
                        System.out.println("Value of winE=" + winE);
                        max = winC;
                    }
                    winC = 0;
                    
                }
                else if(cnt<k){
                    if(cnt ==0) {
                        firstZero = winE;
                    }
                    winC++;
                    winE++;
                    cnt++;
                }
                else {
                    if(max < winC) {
                        // System.out.println("Value of winE=" + winE);
                        max = winC;
                    }
                    winS = firstZero + 1;
                    cnt = 0;
                    winC=0;
                    winE = winS;
                    
                }
                
                
            }
            
        }
        
        if(winE>=len && winC!=0) {
            max = Math.max(max, winC);
        }
        
        return max;
    }
    
    public static void main(String[] args) {
        LeetCode_1004_slower leetCode_1004Slower = new LeetCode_1004_slower();
        int [] nums ;
        int k;
        int answer;
        
        nums = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        k = 3;
        answer = leetCode_1004Slower.longestOnes(nums, k);
        System.out.println(answer);
        //answer 10
    
        nums = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        k = 2;
        answer = leetCode_1004Slower.longestOnes(nums, k);
        System.out.println(answer);
        //answer 6
        
        nums = new int[]{0,0,0,1};
        k = 4;
        answer = leetCode_1004Slower.longestOnes(nums, k);
        System.out.println(answer);
        //answer 4;
        
        
    }
}
