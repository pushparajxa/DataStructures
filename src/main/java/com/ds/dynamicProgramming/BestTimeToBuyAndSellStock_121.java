/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

public class BestTimeToBuyAndSellStock_121 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        
        if(len == 0)
            return 0;
        if(len ==1){
        
        }

        /*
            At ith iteration, we compare minValue so far in the array from 0 to i-1.
            If ith value greater than the minValue, we calculate the profit and update the
            maxProfit if it is more than that.

            otherwise we update minValue to the ith value.


        */
        
        int minSoFar = prices[0];
        int result = 0;
        for(int i=1;i<len;i++){
            if(prices[i]>minSoFar){
                result = Math.max(result, prices[i]-minSoFar);
            }
            else{
                minSoFar = prices[i];
            }
        }
        
        return result;
        
    }
}