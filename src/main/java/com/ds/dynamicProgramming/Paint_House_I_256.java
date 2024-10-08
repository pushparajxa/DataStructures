/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

public class Paint_House_I_256 {
    
    public int minCost(int[][] costs) {
        int[] cur = new int[3];
        int[] prev = new int[3];
        int[] temp;
        
        int i = 0;
        
        while (i < costs.length) {
            cur[0] = costs[i][0] + Math.min(prev[1], prev[2]);
            cur[1] = costs[i][1] + Math.min(prev[2], prev[0]);
            // System.out.println("costs[i][2]="+ costs[i][2]+ ", prev[0]=" + prev[0]+", prev[1]="+ prev[1]);
            cur[2] = costs[i][2] + Math.min(prev[0], prev[1]);
            
            //System.out.println(Arrays.toString(prev) + "----" + Arrays.toString(cur));
            
            temp = prev;
            prev = cur;
            cur = temp;
            i++;
            
        }
        
        return Math.min(Math.min(prev[0], prev[1]), prev[2]);
        
    }
    
    public int minCost2(int[][] costs) {
        int dp[][] = new int[costs.length][3];
        int res = Math.min(Math.min(getCost(costs, 0, 0, dp), getCost(costs, 1, 0, dp)),
            getCost(costs, 2, 0, dp));
        return res;
    }
    
    int getCost(int[][] costs, int color, int n, int dp[][]) {
        if (dp[n][color] != 0) {
            return dp[n][color];
        }
        
        if (n == costs.length - 1) {
            return costs[costs.length - 1][color];
        } else {
            System.out.println("getCost(" + n + "," + color + ")");
            int cost;
            if (color == 0) {
                cost = Math.min(getCost(costs, 1, n + 1, dp), getCost(costs, 2, n + 1, dp));
            } else if (color == 1) {
                cost = Math.min(getCost(costs, 0, n + 1, dp), getCost(costs, 2, n + 1, dp));
            } else {
                cost = Math.min(getCost(costs, 0, n + 1, dp), getCost(costs, 1, n + 1, dp));
            }
            dp[n][color] = cost + costs[n][color];
            return cost + costs[n][color];
        }
    }
    
}