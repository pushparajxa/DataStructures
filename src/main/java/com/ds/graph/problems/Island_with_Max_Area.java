/*
 ** COPYRIGHT **
 */
package com.ds.graph.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Island_with_Max_Area {
    
    int getMaxArea(int[][] input) {
        
        int[][] visited = new int[input.length][input[0].length];
        
        int rows = input.length;
        int cols = input[0].length;
        
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (input[i][j] == 1 && visited[i][j] == 0) {
                    // max = Math.max(max,doBfs(input, i,j,visited));
                    max = Math.max(max, doDfs(input, i, j, visited));
                }
            }
        }
        
        return max;
        
    }
    
    
    private int doBfs(int[][] input, int row, int col, int[][] visited) {
        
        int area = 0;
        
        visited[row][col] = 1;
        
        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        visited[row][col] = 1;
        
        int cnt;
        
        while (!queue.isEmpty()) {
            
            cnt = queue.size();
            
            while (cnt > 0) {
                Pair pair = queue.pop();
                //visited[pair.x][pair.y] = 1;
                area++;
                
                List<Pair> nghbrs = getNonVisitedNeighbors(pair, input, visited);
                queue.addAll(nghbrs);
                cnt--;
            }
            
            
        }
        
        return area;
    }
    
    /*

             1 1 1
             1 1 1
             1 1 1

     */
    private List<Pair> getNonVisitedNeighbors(Pair pair, int[][] input, int[][] visited) {
        int row = pair.x;
        int col = pair.y;
        
        List<Pair> neighbors = new ArrayList<>(4);
        
        int rows = input.length;
        int cols = input[0].length;
        
        if (row - 1 >= 0 && input[row - 1][col] == 1 && visited[row - 1][col] == 0) {
            
            neighbors.add(new Pair(row - 1, col));
            visited[row - 1][col] = 1;
            
            
        }
        
        if (row + 1 <= rows - 1 && input[row + 1][col] == 1 && visited[row + 1][col] == 0) {
            
            neighbors.add(new Pair(row + 1, col));
            visited[row + 1][col] = 1;
            
            
        }
        
        if (col - 1 >= 0 && input[row][col - 1] == 1 && visited[row][col - 1] == 0) {
            
            neighbors.add(new Pair(row, col - 1));
            visited[row][col - 1] = 1;
            
            
        }
        
        if (col + 1 <= cols - 1 && input[row][col + 1] == 1 && visited[row][col + 1] == 0) {
            
            neighbors.add(new Pair(row, col + 1));
            visited[row][col + 1] = 1;
            
        }
        
        return neighbors;
        
    }
    
    private int doDfs(int[][] input, int row, int col,
        int[][] visited) {
        
        visited[row][col] = 1;
        int rows = input.length;
        int cols = input[0].length;
        int cnt = 1;
        
        if (row - 1 >= 0 && input[row - 1][col] == 1 && visited[row - 1][col] == 0) {
            cnt = cnt + doBfs(input, row - 1, col, visited);
        }
        
        if (row + 1 <= rows - 1 && input[row + 1][col] == 1 && visited[row + 1][col] == 0) {
            
            cnt = cnt + doBfs(input, row + 1, col, visited);
            
        }
        
        if (col - 1 >= 0 && input[row][col - 1] == 1 && visited[row][col - 1] == 0) {
            
            cnt = cnt + doBfs(input, row, col - 1, visited);
            
        }
        
        if (col + 1 <= cols - 1 && input[row][col + 1] == 1 && visited[row][col + 1] == 0) {
            
            cnt = cnt + doBfs(input, row, col + 1, visited);
        }
        
        return cnt;
        
    }
    
    private static class Pair {
        
        int x; //row
        int y;// column
        boolean visited = false;
        
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) {
        
        Island_with_Max_Area islands = new Island_with_Max_Area();
        
        int[][] input = new int[][]{
            {1, 0},
            {0, 0}
            
        };
        
        input = new int[][]{
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        
        int res = islands.getMaxArea(input);
        
        System.out.println(res); // answer should be 6.
    }
    
    
}
