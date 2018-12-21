//https://www.programcreek.com/2014/05/leetcode-longest-increasing-pathin-a-matrix-java/
package com.matrix;

/*
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary
 */

public class LongestIncreasingPath {

    int longestPath(int[][] mat, int rows, int cols){
        boolean [][] visited = new boolean[rows][cols];
        int max=0;
        for(int i=0;i<rows;i++){
            for(int j = 0;j<cols;j++) {
                visited = new boolean[rows][cols];
                int res = dfs(i,j,mat,visited);
                if(max<res){
                    max=res;
                }
            }
        }
        return max;
    }

    private int dfs(int i,int j,int[][] mat,boolean[][] visited) {

        visited[i][j] = true;
        int max1=0,max2=0,max3=0,max4=0;

        if(i+1< mat.length && mat[i+1][j] > mat[i][j]){
            max1 = dfs(i+1,j,mat,visited);
        }
        if(i-1>0 && mat[i-1][j] > mat[i][j]){
            max1 = dfs(i-1,j,mat,visited);
        }
        if(j+1< mat[0].length && mat[i][j+1] > mat[i][j]){
            max1 = dfs(i,j+1,mat,visited);
        }
        if(j-1>0 && mat[i][j-1] > mat[i][j]){
            max1 = dfs(i,j-1,mat,visited);
        }

        int max5 = Math.max(max1,max2);
        int max6 = Math.max(max3,max4);

        return 1 + Math.max(max5,max6);
    }

    public static void main(String[] args) {
        int rows=10,cols=10;
        int [][] mat = {{8},{9},{1},{2},{3}} ;
        LongestIncreasingPath longestIncreasingPath = new LongestIncreasingPath();
        int longestPath = longestIncreasingPath.longestPath(mat,mat.length,mat[0].length);
        System.out.println(longestPath);
    }

}
