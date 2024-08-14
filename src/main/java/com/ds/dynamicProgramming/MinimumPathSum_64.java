
package com.ds.dynamicProgramming;

//Related Goldman sachs:: https://leetcode.com/discuss/interview-question/1531556/coder-pad-round-goldman-sachs
public class MinimumPathSum_64 {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int i, j;
        int [] prev = new int[cols+1], cur = new int[cols+1],temp;

       /* System.out.println("Previous = " + Arrays.toString(prev));
        System.out.println("Current = " + Arrays.toString(cur)); */
        for( i=rows-1; i>=0; i--){
            
            temp = cur;
            cur = prev;
            prev = temp;
            
            for( j=cols-1; j>=0; j--) {
                
                if(j==cols-1){
                    cur[j] = prev[j] + grid[i][j];
                }
                else {
                    if(i == rows -1) {
                        cur[j] = grid[i][j] + cur[j+1];
                    }
                    else {
                        cur[j] = grid[i][j] + Math.min(prev[j], cur[j+1]);
                    }
                    
                }
            }

           /* System.out.println("Previous = " + Arrays.toString(prev));
            System.out.println("Current = " + Arrays.toString(cur)); */
        }
        return cur[0];
    }
}
