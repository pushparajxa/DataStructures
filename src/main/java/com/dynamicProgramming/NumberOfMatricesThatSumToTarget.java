
package com.dynamicProgramming;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/submissions/
 *
 * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.
 *
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 *
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 * Example 2:
 *
 * Input: matrix = [[1,-1],[-1,1]], target = 0
 * Output: 5
 * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 *
 */
public class NumberOfMatricesThatSumToTarget {

  public int numSubmatrixSumTarget(int[][] matrix, int target) {
    for(int i=0;i<matrix.length;i++){
      for(int j=1;j<matrix[0].length;j++){
        matrix[i][j] += matrix[i][j-1];
      }
    }

    int res=0,sum;HashMap<Integer,Integer> map = new HashMap<>();;
    for(int c1=0;c1< matrix[0].length;c1++){
      for(int c2=c1;c2<matrix[0].length;c2++){
        //Use the logic to find number of subArrays with sum equals to K.
        sum=0;
        map.clear();
        map.put(0,1);
        for(int k=0;k<matrix.length;k++){
          if(c1>0){
            sum = sum + matrix[k][c2] - matrix[k][c1-1];
          }else{
            sum = sum + matrix[k][c2];
          }
          if(map.containsKey(sum-target)){
            res = res+ map.get(sum-target);
          }
          map.put(sum, map.getOrDefault(sum,0)+1);
        }
      }
    }
    return res;

  }

}
