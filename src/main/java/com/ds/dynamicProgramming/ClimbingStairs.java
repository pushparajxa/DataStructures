/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

import java.util.HashMap;

public class ClimbingStairs {
    
    public int climbStairs2(int n) {
        if (n == 1)
            return 1;
        
        if (n == 2)
            return 2;
        
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        
        return res[n];
    }
    
    HashMap<Integer, Integer> sol = new HashMap<>();
    
    
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 0)
            return 0;
        
        if(sol.containsKey(n))
            return sol.get(n);
        else{
            var val =  climbStairs(n - 1) + climbStairs(n - 2);
            sol.put(n, val);
            return val;
        }
        
        
        // This one throws concurrent modification exception as it doesn't allow modification of
        // the map while executing the compute function.
        // sol.computeIfAbsent(n, key -> climbStairs(key - 1) + climbStairs(key - 2));
    }
    
    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs(4));
    }
}
