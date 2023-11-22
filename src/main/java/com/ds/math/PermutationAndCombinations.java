package com.ds.math;

import java.util.Arrays;
import static com.general.ArrayUtils.*;

public class PermutationAndCombinations {

    /**
     * Select "select" number of elements from the array arr
     */
    static void printComibations(int select,int[] arr) {
        combinatiorics_helper(0,select,arr,new int[select]);
    }

    static void combinatiorics_helper(int start,int rank,int[] arr,int[] comb) {
        for(int i = start;i<arr.length;i++) {
            comb[rank-1] = arr[i];
            if(rank==1) {
                System.out.println(Arrays.toString(reverseArray(arr)));
            }else {
                 combinatiorics_helper(i+1,rank-1,arr,comb);
            }
        }
    }

    static void generatePermutations(int arr[]) {

        System.out.println(perm_helper(arr,0,new int[arr.length],0));

    }

    static int perm_helper(int [] arr, int pos, int [] val,int count){
        if(pos>=arr.length){
            System.out.println(Arrays.toString(val));
            return count+1;
        }
        for(int i=pos;i<arr.length;i++){
            val[pos]=arr[i];
            swap(pos,i,arr);
            count = perm_helper(arr,pos+1,val,count);
        }
        return count;
    }

    static void swap(int pos1,int pos2, int[] arr){
        if(pos1!=pos2){
            int temp = arr[pos1];
            arr[pos1] = arr[pos2];
            arr[pos2] = temp;
        }
    }

    public static void main(String[] args) {
        printComibations(3,new int[]{1,5,6,12});
       // generatePermutations(new int[]{2,3,4,5});
       // printSubSets(new int[]{2,3,4});
    }

    static void printSubSets(int arr[]){
        for(int i=1;i<=arr.length;i++){
            printComibations(i,arr);
        }

    }
}


