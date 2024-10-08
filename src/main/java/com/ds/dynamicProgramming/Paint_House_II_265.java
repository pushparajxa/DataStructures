/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

import java.util.Arrays;

public class Paint_House_II_265 {
    
    public int minCostII(int[][] costs) {
        System.out.println(Arrays.deepToString(costs));
        int k = costs[0].length;
        int[] cur = new int[k];
        int[] prev = new int[k];
        int[] temp;
        
        int minPrev1 = 0, minPrev2 = 0, minPrev;
        int curPrev1 = Integer.MAX_VALUE, curPrev2 = Integer.MAX_VALUE;
        
        int i = 0;
        
        while (i < costs.length) {
            
            curPrev1 = Integer.MAX_VALUE;
            curPrev2 = Integer.MAX_VALUE;
            
            for (int j = 0; j < k; j++) {
                if (prev[j] == minPrev1) {
                    minPrev = minPrev2;
                } else {
                    minPrev = minPrev1;
                }
                
                cur[j] = costs[i][j] + minPrev;
              //  System.out.println("cost["+i+"]["+j+"]=" + costs[i][j]+", minPrev="+minPrev);
                if (cur[j] <= curPrev1) {
                    curPrev2 = curPrev1;
                    curPrev1 = cur[j];
                } else if (cur[j] <= curPrev2) {
                    curPrev2 = cur[j];
                }
            }
           // System.out.println(Arrays.toString(prev) + "----" + Arrays.toString(cur));
            
            minPrev2 = curPrev2;
            minPrev1 = curPrev1;
            
            temp = prev;
            prev = cur;
            cur = temp;
            i++;
           // System.out.println(i);
        }
        
        int min = Integer.MAX_VALUE;
        for (i = 0; i < prev.length; i++) {
            min = Math.min(min, prev[i]);
        }
        return min;
        
    }
    
    public static void main(String[] args) {
        Paint_House_II_265 paintHouse2 = new Paint_House_II_265();
        
        int [][] input = new int[][]{
            {1,5,3},
            {2,9,4}
        };
        
        int res = paintHouse2.minCostII(input);
        System.out.println(res);
    }
}