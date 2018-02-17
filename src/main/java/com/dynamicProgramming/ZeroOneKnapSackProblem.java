package com.dynamicProgramming;

import java.util.Arrays;

public class ZeroOneKnapSackProblem {
    public static void main(String[] args) {
        int wt[] = {10,20,30};
        int values[] = {60,100,120};
        int max_weight = 50;
        int max = findMax2(wt,values,max_weight);
        System.out.println(max);

    }

    private static int findMax(int[] wgts,int[] values,int max_weight) {
        int max[][] = new int[wgts.length][max_weight+1];

        for(int i = 0;i<wgts.length;i++) {
            int wgt = wgts[i];
            for(int j = 0;j<=max_weight;j++) {
                if(wgt>max_weight) {
                    if(i==0) {
                        max[i][j] = 0;
                    }else {
                        max[i][j] = max[i-1][j];
                    }
                }else {
                    if(j<wgt) {
                        if(i==0) {
                            max[i][j] = 0;
                        }else {
                            max[i][j] = max[i-1][j];
                        }
                    }else {
                        if(i==0) {
                            max[i][j] = values[i];
                        }else {
                            max[i][j] = Math.max(max[i-1][j-wgt]+values[i],max[i-1][j]);
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(max[0]));
        System.out.println(Arrays.toString(max[1]));
        System.out.println(Arrays.toString(max[2]));
        return max[wgts.length-1][max_weight];
    }

    private static int findMax2(int[] wgts,int[] values,int max_weight) {
        int max[][] = new int[wgts.length+1][max_weight+1];

        for(int i = 0;i<=max_weight;i++) {
            max[0][i] = 0;
        }

        for(int i = 1;i<=wgts.length;i++) {
            int wgt = wgts[i-1];
            int val = values[i-1];
            for(int j = 0;j<=max_weight;j++) {
                if(wgt>max_weight) {
                    max[i][j] = max[i-1][j];
                }else {
                    if(j<wgt) {
                        max[i][j] = max[i-1][j];
                    }else {
                        max[i][j] = Math.max(max[i-1][j-wgt]+val,max[i-1][j]);
                    }
                }
            }
        }

        return max[wgts.length][max_weight];
    }

}
