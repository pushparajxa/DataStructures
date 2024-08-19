
package com.ds.arrays;

class ShortestSubArrayWithSumAtLeastK {
    
    public int shortestSubarray(int[] nums, int k) {
        //handle when nums is empty
        if(nums.length == 0)
            return 0;
        
        if(nums.length ==1)
            return k >= nums[0]?1: -1;
        //handle when nums length is 1.
        
        int s=0;
        int winS=0, winE=0;
        int c=0; int minC=Integer.MAX_VALUE;;
        int i=0;
        
        while(winS<nums.length && winE<nums.length && i<nums.length) {
            if(s+nums[i]==k){
                c++;
                minC = Math.min(c,minC);
                s = s + nums[i];
                s = s - nums[winS];
                winS++;
                c--;
                winE = i;
                i = winS;
            }
            else if(s + nums[i] > k) {
                winE=i;
                s = s + nums[i];
                i++;
                c++;
                minC = Math.min(c,minC);
                
                int l = winS;
                while(l<=winE){
                    if(nums[l]<=0){
                        s = s - nums[l];
                        winS = l+1;
                        c--;
                        l++;
                        if(s>=k){
                            minC = Math.min(c,minC);
                            
                        }
                    }else {
                        if (s-nums[l] >=k){
                            c--;
                            minC = Math.min(c,minC);
                            s = s - nums[l];
                            l++;
                            winS = l;
                        }
                        else {
                            c--;
                            s = s - nums[l];
                            l++;
                            //winS++;
                        }
                    }
                }
                i = winS;
            }
            else {
                if(s+ nums[i]<=0){
                    winS=i+1;
                    if(winS == nums.length){
                        return minC;
                    }
                    else{
                        s = nums[winS];
                        i= winS + 1;
                        c=1;
                    }
                }
                else {
                    s = s + nums[i];
                    winE = i;
                    i++;
                    c++;
                }
            }
        }
        
        return minC == Integer.MAX_VALUE ? -1:minC;
    }
    
    public static void main(String[] args) {
        
        ShortestSubArrayWithSumAtLeastK se = new ShortestSubArrayWithSumAtLeastK();
        int arr[] = new int[]{11,47,97,35,-46,59,46,51,59,80,14,-6,2,20,96,1,18,74,-17,71};
        int n = se.shortestSubarray(arr, 282);
        System.out.println(n);
        
    }
}