package com.ds.search;

import java.util.Arrays;

public class KokoEatingBananas_875 {

    // This one is not faster through we start from average of total bananas divided by hours.
    // Binary search from average to maxium of piles would be faster.
    public int minEatingSpeed2(int[] piles, int h) {
        
        long sum=0;
        for(int i=0;i<piles.length;i++){
            sum = sum + piles[i];
        }
        
        // System.out.println(sum);
        long start = sum/h;
        
        long cnt = start;
        boolean done = false;
        
        while(!done){
            // System.out.println(cnt);
            long s=0;
            for(int i=0;i<piles.length;i++){
                s = s + (long) Math.ceil((double)piles[i]/cnt);
                if(s>h)
                    break;
            }
            if(s<=h)
                return (int)cnt;
            else
                cnt++;
            
        }
        return (int)cnt;
        
    }
    
    // Based on binary search.
    public int minEatingSpeed(int[] piles, int h){
        
        int max = Arrays.stream(piles).max().getAsInt();
        
        boolean done = false;
        int start = 1; int end = max; int mid=0;
        int cnt =0;
        
        while(start!=end){
            mid = (start + end)/2;
            
            cnt = getHours(piles, mid);
            //     System.out.println("Rate="+ mid + ", hours=" + cnt);
            if(cnt >h){
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return start;
    }
    
    int getHours(int[] piles, int rate){
        int s = 0;
        for(int i=0;i<piles.length;i++){
            s = s +  (int)Math.ceil((double)piles[i]/rate);
        }
        
        return s;
    }
}