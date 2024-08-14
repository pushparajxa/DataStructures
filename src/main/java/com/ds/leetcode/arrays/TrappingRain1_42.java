
package com.ds.leetcode.arrays;

public class TrappingRain1_42 {
    public int trap(int[] height) {
        if(height.length == 0 || height.length ==1)
            return 0;
        
        int winS=0, winE=0, i=1;
        int len = height.length;
        boolean started = false;
        int sum = 0;
        
        while(winS < len && winE < len && i < len){
            if(!started){
                if(height[i] >= height[winS]){
                    i++;
                    winS++;
                }
                else {
                    winE = i;
                    i++;
                    started = true;
                }
            }
            else {
                if(height[i]>=height[winS]){
                    winE = i;
                    sum = sum + calculate(height, winS, winE);
                    started = false;
                    winS = i;
                    i++;
                }
                else {
                    winE=i;
                    i++;
                }
            }
        }
        if(started) {
            
            if(winS == len -1){
                // do nothing
            }
            
            else {
                int second;
                int temp = winS;
                do {
                    second =  findSecondFromEnd(height,temp);
                    sum = sum + calculate(height, temp, second);
                    temp = second;
                } while(second < len-1);
            }
            
        }
        
        return sum;
    }
    
    
    private int findSecondFromEnd(int [] height, int winS){
        int len = height.length;
        int index = len -1;
        int max = height[len-1];
        for(int i=len-1;i>winS;i--){
            if(height[i]> max){
                index = i;
                max = height[index];
            }
        }
        
        return index;
    }
    
    private int calculate(int []height, int winS, int winE){
        System.out.println("winS=" + winS + " , winE=" + winE);
        int sum = 0;
        int val = Math.min(height[winS], height[winE]);
        for(int i=winS+1; i< winE; i++){
            if(height[i] < val)
                sum = sum + val - height[i];
        }
        return sum;
    }
}
