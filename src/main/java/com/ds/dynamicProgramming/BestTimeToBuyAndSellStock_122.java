/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

public class BestTimeToBuyAndSellStock_122 {
    
    public int maxProfit(int[] prices) {
        int len = prices.length;
        
        if(len == 0)
            return 0;
        if(len ==1){
        
        }
        
        
        /*
            at ith iteration, we check if ith value is greater than min,
                If yes,
                    then we add the difference to the profit and we buy the stock on the same
                    day ,i.e., set min to ith value.
                    
                Otherwise,
                    we set min to ith value.
        */
        
        int min = prices[0];
        int result = 0;
        for(int i=1;i<len;i++){
            if(prices[i]>min){
                result += prices[i]-min;
                min = prices[i];
            }
            else{
                min = prices[i];
            }
        }
        
        return result;
        
    }
}