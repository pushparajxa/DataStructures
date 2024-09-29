/*
 ** COPYRIGHT **
 */
package com.ds.graph.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import scala.Int;

public class BobAliceMaze_Goldcoins_DP {
    
    /*
     * Complete the 'minMoves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY maze
     *  2. INTEGER x
     *  3. INTEGER y
     */
    // 2 Means gold coins
    // 1 Means block
    // 0 means path with no gold coins
    
    // https://leetcode.com/discuss/interview-question/708638/sap-labs-oa-bob-navigates-a-maze
    
    private static int minMoves(List<List<Integer>> maze, int x, int y) {
        // Write your code here
        int r = maze.size();
        int c = maze.get(0).size();
        
        int gc = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maze.get(i).get(j) == 2) {
                    gc++;
                }
                
            }
        }
        
        int[][] dp = new int[r * c][gc+1];
        HashSet<pair> visited = new HashSet<>();
        int res = dfs(0,0, x, y, gc, dp, maze, visited);
        
        System.out.println(Arrays.deepToString(dp));
        
        return res == Integer.MAX_VALUE ? -1 : res;
        
    }
    
    
    private static int dfs(int src_x, int src_y, int dst_x, int dst_y, int gc, int [][] dp,
        List<List<Integer>> maze, HashSet<pair> visited){
        System.out.println(src_x +"," + src_y +"," + dst_x +"," + dst_y +",gc=" + gc);
        int srcGC = gc;
        
        if(src_x == dst_x && src_y == dst_y) {
            if(gc ==0){
                return 0;
            }
            else {
                return Integer.MAX_VALUE;
            }
        }
        
        
        if(gc<0)
            return Integer.MAX_VALUE;
        
        int r = maze.size();
        int c = maze.get(0).size();
        
        int cn = src_x*c + src_y;
        
        if(dp[cn][gc]!=0)
            return dp[cn][gc];
        
        
        pair srcPair = new pair(src_x, src_y);
        visited.add(srcPair);
        List<pair> ngs =  getNgs(src_x, src_y, r, c);
        //filter blocked cells which are marked with 1.
        ngs =
            ngs.stream().filter(p-> maze.get(p.x).get(p.y)!=1 && !visited.contains(p)).collect(Collectors.toList());
        System.out.println(ngs);
        
        if(ngs.size() ==0){
            dp[cn][gc] = Integer.MAX_VALUE;
            visited.remove(srcPair);
            return Integer.MAX_VALUE;
        }
        
        int temp, local = Integer.MAX_VALUE;
        gc = gc - (maze.get(src_x).get(src_y)==2?1:0);
       
        
        for(pair p: ngs){
           
           if(visited.contains(p))
               continue;
           
           temp =  dfs(p.x, p.y, dst_x,dst_y, gc, dp, maze, visited);
           
           if(temp!=Integer.MAX_VALUE)
               local = Math.min(local,temp+1);
            
        }
        visited.remove(srcPair);
        
        dp[cn][srcGC] = local;
        return local == Integer.MAX_VALUE ? Integer.MAX_VALUE : local;
        
        
    }
    
    
    static List<pair> getNgs(int x, int y, int r, int c) {
        List<pair> ps = new ArrayList<>();
        
        
        if (y + 1 < c) {
            
                ps.add(new pair(x, y + 1));
        }
        if (y - 1 >= 0) {
            
                ps.add(new pair(x, y - 1));
        }
        
        if (x + 1 < c) {
           
                ps.add(new pair(x + 1, y));
            
        }
        if (x - 1 >= 0) {
                ps.add(new pair(x - 1, y));
        }
        
       
        
        return ps;
        
    }
    
 
    
    private static class pair {
        
        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        int x, y;
        
        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
        
        
        @Override
        public boolean equals(Object other) {
            pair o = (pair) other;
            return x == o.x && y == o.y;
        }
        
        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }
    
    public static void main(String[] args) {
        
        List<List<Integer>> maze = List.of(
            List.of(0, 2, 1),
            List.of(1, 2, 0),
            List.of(1, 0, 0)
        
        );
        // int res = minMoves(maze, 2,2);
        // res should be 4
        
        maze = List.of(
            List.of(0, 2, 0),
            List.of(1, 1, 2),
            List.of(1, 0, 0)
        
        ); // res should be 5
        
        
        int res = minMoves(maze, 2,1);
        
        System.out.println(res);
        
        
    }
    
    
}