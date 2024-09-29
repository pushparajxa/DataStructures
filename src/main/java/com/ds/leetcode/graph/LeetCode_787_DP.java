/*
 ** COPYRIGHT **
 */
package com.ds.leetcode.graph;

import java.util.HashMap;

// Cheapest flight with k stops
public class LeetCode_787_DP {
    
    public static void main(String[] args) {
        
        int [][] flights = new int[][]{
            {0,4,2},
            {1,5,4}, {1,0,4},{1,7,6},{1,2,6},
            {2,5,6},
            {3,4,4},
            {4,7,10},{4,1,5},{4,0,9},
            {5,9,1},{5,2,4},
            {6,2,10},{6,8,6},{6,5,8},
            {8,7,3},
            {7,4,4},{7,9,4}, {7,0,5},{7,8,10},{7,2,8},
            {9,7,3},{9,6,5},{1,9,1}
        };
        
        
        HashMap<Integer, HashMap<Integer, Integer>> adjList = new HashMap<>();
        
        
        for (int i = 0; i < flights.length; i++) {
            adjList.computeIfAbsent(flights[i][0],
                l -> new HashMap<>()).put(flights[i][1],
                flights[i][2]);
        }
        
        System.out.println(adjList);
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        
        int [][] dp = new int[n][k+1];
        
        HashMap<Integer, HashMap<Integer, Integer>> adjList = new HashMap<>();
        
        
        
        for (int i = 0; i < flights.length; i++) {
            adjList.computeIfAbsent(flights[i][0],
                l -> new HashMap<>()).put(flights[i][1],
                flights[i][2]);
        }
        
        System.out.println(adjList);
        
        int res =  dfs(src, dst, dp, adjList, k);
        
        return res == Integer.MAX_VALUE ? -1 : res;
        
    }
    
    private int dfs(int src, int dst, int[][] dp,
        HashMap<Integer, HashMap<Integer, Integer>> adjList, int k) {
        
        System.out.println("dfs("+src+","+dst+","+k+")");
        if(src == dst) {
            
            return 0;
        }
        
        if(k<0) {
            return Integer.MAX_VALUE;
        }
        
        if(!adjList.containsKey(src)) // Case where thre are no flights from this vertex.
            return Integer.MAX_VALUE;
        
        
        if(dp[src][k] == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        
        if(dp[src][k]!=0){
            return dp[src][k];
        }
        
        int temp, local = Integer.MAX_VALUE;
        for (int out: adjList.get(src).keySet()) {
            
            temp = dfs(out, dst, dp, adjList, k-1 );
            System.out.println("dfs("+out+","+dst+","+(k-1)+") == " + temp);
            if(temp!=Integer.MAX_VALUE)
                local = Math.min(local, temp+adjList.get(src).get(out));
            
        }
        System.out.println("dfs("+src+","+dst+","+k+") == " + local);
        
        return dp[src][k] = local;
    }
    
    
}
