
package com.ds.dynamicProgramming;

import java.util.Arrays;
//Taken from the book 6th Chapter http://algorithmics.lsi.upc.edu/docs/Dasgupta-Papadimitriou-Vazirani.pdf
public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        //int arr[] = {5,2,8,6,3,6,9,7,};
        int arr[]  = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        printLCS(arr);

    }
     static void printLCS(int arr[]){
        int len[]  = new int[arr.length];
        int prev[]  = new int[arr.length];
        for(int i = 0;i<arr.length;i++) {
            int max =0;
            int jInd=i;
            for(int j = 0;j<i;j++) {
                if(arr[j]<=arr[i]){
                   max =  (max < len[j])  ? len[j]:max;
                   jInd=j;
                }
            }
            len[i] = 1+ max;
            prev[i] = jInd;
        }

        System.out.println(Arrays.toString(len));
         System.out.println(Arrays.toString(prev));
    }

}
