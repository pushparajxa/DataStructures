
package com.ds.dynamicProgramming;

import java.util.Arrays;

//https://leetcode.com/problems/knight-probability-in-chessboard/solution/

public class KnightProbablityInChessBoard {

  public static void main(String[] args) {

    knightProbability(8,30,6,4);

  }


  public static void knightProbability(int N, int K, int r, int c) {
    double [][][] prob = new double[2][N][N];
    prob[0][r][c] = 1;

    int x[] = {1,1,-1,-1,2,2,-2,-2};
    int y[] = {-2,2,2,-2,1,-1,1,-1};

    for(int i=1;i<K+1;i++){

      for (int g = 0; g < N; g++) {
        for (int h = 0; h < N; h++) {
          prob[i%2][g][h] = 0;
        }
      }


      for(int j=0;j<N;j++){
        for(int l=0;l<N;l++){
          for(int p=0;p<8;p++){
            if(j+x[p] >=0 && j+x[p] <N && l+y[p] >=0 && l+y[p] <N){
              if(prob[(i-1)%2][j+x[p]][l+y[p]]>0){
                prob[i%2][j][l] =prob[i%2][j][l] + (prob[(i-1)%2][j+x[p]][l+y[p]])/8;
              }
            }
          }
        }
      }
    }

    for (int i = 0; i <N; i++) {
      System.out.println(Arrays.toString(prob[K%2][i]));
    }

    double sum = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <N; j++) {
        sum = sum + prob[K%2][i][j];
      }
    }

    //double probability = sum/Math.pow(8,K);
    System.out.println(sum);

  }


  public static void knightProbabilityWithMoreMemory(int N, int K, int r, int c) {
    double [][][] prob = new double[K+1][N][N];
    prob[0][r][c] = 1;

    int x[] = {1,1,-1,-1,2,2,-2,-2};
    int y[] = {-2,2,2,-2,1,-1,1,-1};

    for(int i=1;i<K+1;i++){
      for(int j=0;j<N;j++){
        for(int l=0;l<N;l++){
          for(int p=0;p<8;p++){
            if(j+x[p] >=0 && j+x[p] <N && l+y[p] >=0 && l+y[p] <N){
              if(prob[i-1][j+x[p]][l+y[p]]>0){
                prob[i][j][l] =prob[i][j][l] + (prob[i-1][j+x[p]][l+y[p]])/8;
              }
            }
          }
        }
      }
    }

    for (int i = 0; i <N; i++) {
      System.out.println(Arrays.toString(prob[K][i]));
    }

    double sum = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <N; j++) {
        sum = sum + prob[K][i][j];
      }
    }

    //double probability = sum/Math.pow(8,K);
    System.out.println(sum);

  }


  //This approach leads to long overflow.
  public static void knightProbability2(int N, int K, int r, int c) {
    long [][][] prob = new long[K+1][N][N];
    prob[0][r][c] = 1;

    int x[] = {1,1,-1,-1,2,2,-2,-2};
    int y[] = {-2,2,2,-2,1,-1,1,-1};

    for(int i=1;i<K+1;i++){
      for(int j=0;j<N;j++){
        for(int l=0;l<N;l++){
          for(int p=0;p<8;p++){
            if(j+x[p] >=0 && j+x[p] <N && l+y[p] >=0 && l+y[p] <N){
              if(prob[i-1][j+x[p]][l+y[p]]>=1){
                prob[i][j][l] =prob[i][j][l] + prob[i-1][j+x[p]][l+y[p]];
              }
            }
          }
        }
      }
    }

    for (int i = 0; i <N; i++) {
      System.out.println(Arrays.toString(prob[K][i]));
    }

    double sum = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <N; j++) {
        sum = sum + prob[K][i][j];
      }
    }

    double probability = sum/Math.pow(8,K);
    System.out.println(probability);

  }


}
