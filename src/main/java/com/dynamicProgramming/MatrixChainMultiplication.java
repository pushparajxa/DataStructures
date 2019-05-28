package com.dynamicProgramming;

public class MatrixChainMultiplication {

  public static void main(String[] args) {
    int dimensions[] = {10,30,5,60};// 3 matrices

    int minSum = calculateMinSum(dimensions);
    System.out.println(minSum);

  }

  private static int calculateMinSum(int[] dimensions) {

    int n = dimensions.length-1;

    if(n==1){
      return 0;
    }else if(n==2){
      return dimensions[0]*dimensions[1]*dimensions[2];
    }

    int [][] matrix = new int[n][n];
    for (int i = 0; i <n; i++) {
      matrix[i][i] = 0;

    }

    for (int width = 2; width <=n; width++) {
      for (int j = 0; j <= n-width; j=j+1) {
        int max =Integer.MAX_VALUE;
        for (int k = j; k <= j+width-2 ; k++) {
            if(max> matrix[j][k] + matrix[k+1][j+width-1]+dimensions[j]*dimensions[k+1]*dimensions[j+width-1+1]){
              max = matrix[j][k] + matrix[k+1][j+width-1]+dimensions[j]*dimensions[k+1]*dimensions[j+width-1+1];
            }
        }
        matrix[j][j+width-1] =max;
      }

    }
    return matrix[0][n-1];
  }

}
