package com.math;

import java.util.Arrays;

public class PermutationAndCombinations {

    /**
        Select select number of elements from the array arr
     */
    static void printComibations(int select,int[] arr){
        combinatiorics_helper(0,select,arr,new int[select]);
    }

    static void combinatiorics_helper(int start,int rank,int [] arr,int [] comb){
        for(int i=start;i<arr.length;i++){
            comb[rank-1] = arr[i];
            if(rank==1){
                System.out.println(Arrays.toString(comb));
            }else{
                combinatiorics_helper(i+1,rank-1,arr,comb);
            }
        }
    }

    public static void main(String[] args) {
        printComibations(4,new int[]{2,3,4,5,6});
    }

}
