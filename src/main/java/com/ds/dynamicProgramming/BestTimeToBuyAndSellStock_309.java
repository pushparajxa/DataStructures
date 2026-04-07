/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock_309 {
    
    
    public int maxProfit(int[] prices) {
        
        int len = prices.length;
        
        if(len  ==0 || len ==1)
            return 0;
        
        if(len == 2)
            return prices[1] - prices[0] >=0? prices[1] - prices[0] : 0;
        
        
        int [] max = new int[len];
        // 1 2 9 3 200
        // 0 1
        /*
           C B S
             m C
           B   S
        
         */
        
        max[0] = 0;
        max[1] = prices[1] - prices[0] > 0 ? prices[1] - prices[0] : 0;
        
        int temp;
        
        for (int i = 2; i < len ; i++) {
          //  System.out.println("i =" + i);
            int res = max[i-1];
            
            
            for (int j = 0; j <= i-1; j++) {
                temp = prices[i] - prices[j] + getMax(j-2, max);
                res = Math.max(res, temp);
             //   System.out.println("\tj=" + j + ", temp=" + temp);
            }
            
            max[i] = res;
            
        }
        
     //   System.out.println(Arrays.toString(max));
        return max[len-1];
    }
    
    int getMax(int x, int [] max){
        
        if(x<0)
            return  0;
        else
            return max[x];
        
    }
    
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock_309 buyAndSellStock309 = new BestTimeToBuyAndSellStock_309();
        
        int [] input = new int[]{1,2,3};
        System.out.println(buyAndSellStock309.maxProfit(input));
        input = new int[] {1,2,3,0,2};
        System.out.println(buyAndSellStock309.maxProfit(input));
        // 1,2,9,3,0,200
        input = new int[] {1,2,9,3,0,200};
        System.out.println(buyAndSellStock309.maxProfit(input));
        
    }
    

}
