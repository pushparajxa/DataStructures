
package com.ds.leetcode.slidingWindow;


import java.util.LinkedList;
import java.util.List;

public class LeetCode_1004_Faster {
    public int longestOnes(int[] nums, int k) {
        int winS=0, winE=0;
        int cnt=0;
        int len = nums.length;
        int winC = 0;
        int max = Integer.MIN_VALUE;
        
        
        List<Integer> zeros = new LinkedList();
        
        
        while(winS < len && winE <len) {
            
            if(nums[winE] ==1 ){
                winE ++;
                winC++;
            }
            else{
                if(k==0) {
                    winE++;
                    winS = winE;
                    if(max < winC) {
                        // System.out.println("Value of winE=" + winE);
                        max = winC;
                    }
                    winC = 0;
                    
                }
                else if(cnt<k){
                    zeros.add(winE);
                    winC++;
                    winE++;
                    cnt++;
                }
                else {
                    if(max < winC) {
                        //System.out.println("Value of winC=" + winC + ". Zeros are=" + zeros);
                        
                        max = winC;
                    }
                    zeros.add(winE);
                    int firstZeroIndex = zeros.get(0);
                    winC = winC - (firstZeroIndex  - winS + 1);
                    winS = firstZeroIndex + 1;
                    zeros.remove(0);
                    
                    
                    // System.out.println("WinC=" + winC);
                    winE = winE + 1;
                    winC = winC + 1;
                    
                }
                
                
            }
            
        }
        
        max = Math.max(max, (winE -1) - winS + 1);

  /*      if(winE>=len && winC!=0) {
             max = Math.max(max, winC);
        } */
        
        return max;
    }
    public static void main(String[] args) {
        LeetCode_1004_Faster leetCode_1004_faster = new LeetCode_1004_Faster();
        int [] nums ;
        int k;
        int answer;
        
        nums = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        k = 3;
        answer = leetCode_1004_faster.longestOnes(nums, k);
        System.out.println(answer);
        //answer 10
        
        nums = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        k = 2;
        answer = leetCode_1004_faster.longestOnes(nums, k);
        System.out.println(answer);
        //answer 6
        
        nums = new int[]{0,0,0,1};
        k = 4;
        answer = leetCode_1004_faster.longestOnes(nums, k);
        System.out.println(answer);
        //answer 4;
        
        
    }
}
