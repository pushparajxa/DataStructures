/*
 ** COPYRIGHT **
 */
package com.ds.graph.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BobAliceMaze_Goldcoins {
    
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
    
    public static int minMoves(List<List<Integer>> maze, int x, int y) {
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
        
        int[][] visited = new int[r][c];
        int[][] coins = new int[r][c];
        int[][] moves = new int[r][c];
        
        visited[0][0] = 1;
        moves[0][0] = 1;
        
        Stack<pair> stack = new Stack<>();
        
        stack.push(new pair(0, 0));
        
        // main moves so far
        int ms = Integer.MAX_VALUE;
        int cnt = 0;
        coins[0][0] = 0;
        
        while (!stack.isEmpty()) {
            System.out.println(stack);
            pair p = stack.peek();
            
            List<pair> ps = getNgs(p, r, c, visited);
            System.out.println("Ngh=" + ps);
            if (ps.isEmpty()) {
                cnt = 0;
                stack.pop();
                moves[p.x][p.y] = 0;
                coins[p.x][p.y] = 0;
                visited[p.x][p.y] = 1;
                
            }
            
            for (pair pr : ps) {
                if (pr.x == x && pr.y == y) {
                    if (coins[p.x][p.y] == gc) {
                        ms = Math.min(ms, moves[p.x][p.y] + 1);
                    }
                    visited[p.x][p.y] = 1;
                    break;
                } else if (maze.get(pr.x).get(pr.y) == 2) {
                    cnt = cnt + 1;
                    stack.push(pr);
                    visited[pr.x][pr.y] = 1;
                    coins[pr.x][pr.y] = coins[p.x][p.y] + 1;
                    moves[pr.x][pr.y] = coins[p.x][p.y] + 1;
                    break;
                } else if (maze.get(pr.x).get(pr.y) == 0) {
                    stack.push(pr);
                    coins[pr.x][pr.y] = coins[p.x][p.y];
                    moves[pr.x][pr.y] = coins[p.x][p.y] + 1;
                    visited[pr.x][pr.y] = 1;
                    break;
                } else {
                    //blocked neighbour
                }
                
            }
            
            
        }
        
        return ms;
        
    }
    
    
    static List<pair> getNgs(pair p, int r, int c,
        int [][] visited) {
        List<pair> ps = new ArrayList<>();
        
        int x = p.x;
        int y = p.y;
        
        if (y + 1 < c) {
            if (visited[x][y + 1] == 0) {
                ps.add(new pair(x, y + 1));
            }
        }
        if (y - 1 >= 0) {
            if (visited[x][y - 1] == 0) {
                ps.add(new pair(x, y - 1));
            }
        }
        
        if (x + 1 < c) {
            if (visited[x + 1][y] == 0) {
                ps.add(new pair(x + 1, y));
            }
        }
        if (x - 1 >= 0) {
            if (visited[x - 1][y] == 0) {
                ps.add(new pair(x - 1, y));
            }
        }
        
        return ps;
        
    }
    
    // Get non-visted negighbours
    static List<pair> getNgs2(pair p, int r, int c,
        int[][] visited) {
        List<pair> ps = new ArrayList<>();
        
        int x = p.x;
        int y = p.y;
        
        if (y + 1 < c) {
            if (visited[x][y + 1] == 0) {
                ps.add(new pair(x, y + 1));
            }
        }
        if (y - 1 >= 0) {
            if (visited[x][y - 1] == 0) {
                ps.add(new pair(x, y - 1));
            }
        }
        
        if (x + 1 < c) {
            if (visited[x + 1][y] == 0) {
                ps.add(new pair(x + 1, y));
            }
        }
        if (x - 1 >= 0) {
            if (visited[x - 1][y] == 0) {
                ps.add(new pair(x - 1, y));
            }
        }
        
        return ps;
        
    }
    
    static class pair {
        
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
        public boolean equals(Object other){
            pair o = (pair)other;
            return x== o.x && y==o.y;
        }
        
        @Override
        public int hashCode(){
            return x*31 + y;
        }
    }
    
    
}