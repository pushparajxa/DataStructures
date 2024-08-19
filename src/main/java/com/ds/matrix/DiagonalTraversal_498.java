
package com.ds.matrix;

public class DiagonalTraversal_498 {
    
    // Idea: sum of (i,j) of cells in diagonal order will be increasing from 0,1,2,3, ..etc.
    class Solution {
        public int[] findDiagonalOrder(int[][] mat) {
            if(mat.length ==0){
                return new int[]{};
            }
            
            
            int rows = mat.length;
            int cols = mat[0].length;
            
            int total = rows + cols -2;
            
            int[] result = new int[rows * cols];
            
            boolean down = true;
            int k=1;
            result[0] = mat[0][0];
            
            for(int n=1;n<=total; n++){
                // System.out.println(n);
                if(down){
                    for(int i=0;i<=n;i++){
                        // System.out.println("Down+i=" +i);
                        if(i<rows && ((n-i) < cols)){
                            result[k] = mat[i][n-i];
                            k++;
                        }
                    }
                    down = false;
                }
                
                else {
                    for(int i=n;i>=0;i--){
                        if(i<rows && ((n-i) < cols)){
                            result[k] = mat[i][n-i];
                            k++;
                        }
                        // System.out.println("k="+ k);
                    }
                    down = true;
                }
                //System.out.println(Arrays.toString(result));
            }
            
            return result;
        }
    }
}
