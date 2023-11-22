
package com.ds.sort;

import java.util.Arrays;
//Stable
public class MergeSort {

    public static void sortArray(int[] arr) {
        //bottomUp_MergeSort(arr,Arrays.copyOf(arr,arr.length));
        if(arr.length!=0)
        topDownMergeSort(arr,0,arr.length-1,Arrays.copyOf(arr,arr.length));
    }

    //bottom-up .. Algorithm from wikipedia
    private static void bottomUp_MergeSort(int[] arr,int[] cpArr) {
        int numLoops = (int)Math.ceil(Math.log(arr.length)/Math.log(2));
        for(int i = 0;i<numLoops;i++) {
            int jumpBreadth = (int)Math.pow(2,i);
            for(int j = 0;j<arr.length;j = j+2*jumpBreadth) {
                merge(arr,j,Math.min(j+jumpBreadth-1,arr.length-1),Math.min(j+jumpBreadth,arr.length), Math.min(j+2*jumpBreadth-1,arr.length-1),cpArr);
            }
            System.arraycopy(cpArr,0,arr,0,arr.length);
        }
    }

    private static void merge(int[] arr,int sStart,int sEnd,int dStart,int dEnd,int[] b) {
       /* if(dStart>=arr.length){
           System.arraycopy(arr,sStart,b,sStart,sEnd-sStart+1);
            return;
        }*/
        int i,j,count;
        for( i=sStart, j=dStart,count=sStart; i<=sEnd && j<=dEnd ;){
            if(arr[i]<=arr[j]){
                b[count]=arr[i];
                i++;
            }else{
                b[count] = arr[j];
                j++;
            }
            count++;
        }
        if(i<=sEnd){
            System.arraycopy(arr,i,b,count,sEnd-i+1);
        }
        if(j<=dEnd && j<arr.length){ //j< arr.length because to handle the case where dStart == arr.length
            System.arraycopy(arr,j,b,count,dEnd-j+1);
        }
    }

    private static void topDownMergeSort(int[] arr,int start, int end ,int[] cpArr){
        if(start==end){
            cpArr[start] = arr[start];
            return;
        }
        int mid = (start+end)/2;
        topDownMergeSort(arr,start,mid,cpArr);
        topDownMergeSort(arr,mid+1,end,cpArr);
        merge(arr,start,mid,mid+1,end,cpArr);
        System.arraycopy(cpArr,start,arr,start,end-start+1);
    }

    public static void main(String[] args) {
        int src[] = new int[]{9, 6, 3, 1, 2, 5, 5, 5, 1, 5};
        sortArray(src);
        System.out.println(Arrays.toString(src));
    }

}
