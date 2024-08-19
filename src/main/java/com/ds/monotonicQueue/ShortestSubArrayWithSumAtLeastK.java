
package com.ds.monotonicQueue;

import java.util.Deque;
import java.util.LinkedList;

// Reference: https://www.cnblogs.com/lz87/p/13090858.html
public class ShortestSubArrayWithSumAtLeastK {

    
    public int shortestSubarray(int[] nums, int k) {
        if(nums.length ==0)
            return -1;
        
        if(nums.length ==1)
            return nums[0]>=k ? 1: -1;
        
        int len = nums.length;
        long p[] = new long[len+1];
        p[0] = 0;
        for(int i=0; i<len ;i++){
            p[i+1] = p[i] + nums[i];
        }
        
        // Make monotonic queue.
        Deque<Integer> dequeue = new LinkedList();
        dequeue.add(0);
        int res = Integer.MAX_VALUE;
        
        
        for(int i=1;i<len+1;i++){
            boolean done = false;
            
            while(!done){
                Integer val = dequeue.peekLast();
                if(val == null){
                    dequeue.add(i);
                    done = true;
                }
                else {
                    if(p[val]<p[i]){
                        dequeue.addLast(i);
                        
                        boolean sDone = false;
                        while(!sDone){
                            Integer front = dequeue.peekFirst();
                            if(front!=null){
                                if(p[i] - p[front] >=k) {
                                    dequeue.pollFirst();
                                    res = Math.min(res, i- front);
                                }
                                else {
                                    sDone = true;
                                }
                            }
                            else {
                                sDone = true;
                            }
                        }
                        
                        done  = true;
                    }
                    else {
                        dequeue.pollLast();
                    }
                }
            }
            
        }
        
        
        return res==Integer.MAX_VALUE? -1: res;
        
    }
    
    
    public int shortestSubarray2(int[] nums, int k) {
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
                i++;
            }
            else if(s + nums[i] > k) {
                winE=i;
                s = s + nums[i];
                i++;
                c++;
                minC = Math.min(c,minC);
                
                int l = winS;
                while(l<winE){
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
            }
            else {
                if(s+ nums[i]<=0){
                    winS=i+1;
                    if(winS == nums.length){
                        return minC;
                    }
                    else{
                        s = nums[winS];
                        i= i+2;
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
}