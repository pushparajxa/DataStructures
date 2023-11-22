
package com.ds.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static com.general.ArrayUtils.findMaximum;

//Stable
public class RadixSort {

    //Using queues
    private static void sortArray1(int[] arr) {
        if(arr.length==0)
            return;
        int max = findMaximum(arr);
        int rounds = (int)Math.log10(max) +1 ;

        Queue<Integer> queues[] = new LinkedList[10];
        for(int i=0;i<10;i++){
            queues[i] = new LinkedList<>();
        }

        int index;

        for(int i=0;i<rounds;i++){
            for(int j=0;j<arr.length;j++){
                index = (arr[j]/(int)Math.pow(10,i))%10;
                queues[index].add(arr[j]);
            }
            int count=0;
            for(Queue<Integer> queue:queues){
                while(!queue.isEmpty()){
                    arr[count++] = queue.poll();
                }
            }
        }
    }

    //Using recursive (Lowest Significant Digit) Counting sort
    private static void sortArray(int[] arr) {
        if(arr.length==0)
            return;
        int max = findMaximum(arr);
        int rounds = (int)Math.log10(max) +1 ;

        for(int i=0;i<rounds;i++){
            toCountSort(arr,i);
        }

    }


    private static void toCountSort(int[] arr, int decimalPlace){
        int index;
        int rArray[] = new int[10];

        for(int j=0;j<arr.length;j++){
            index = (arr[j]/(int)Math.pow(10,decimalPlace))%10;
            rArray[index]++;
        }

        for(int k=1;k<rArray.length;k++){
            rArray[k] += rArray[k-1];
        }

        int output[] = new int[arr.length];

        for(int l=arr.length-1;l>=0;l--){
            index = (arr[l]/(int)Math.pow(10,decimalPlace))%10;
            output[rArray[index]-1]=arr[l];
            rArray[index] = rArray[index]-1;
        }

        System.arraycopy(output,0,arr,0,output.length);
    }


    //Using MSD (Most Significant Sort).. for Strings
    /*private static void sortArray(String[] arr) {
        if(arr.length==0)
            return;
        char[][]  chars = new char[arr.length][];
        int count =0;
        for(String str:arr){
            chars[count]= arr[0].toCharArray();
        }
        sortArrays(chars);
    }

    private static void sortArrays(char[][] chars){
        int [] range = new int[26];
        for(int i=0;i<chars.length;i++){
        //    range[i] =
            if(chars[i][])
        }


    }
*/

    public static void main(String[] args) {
        int[] arr = {33,33,1,2,1};
        //RadixSort.sortArray(arr);
        System.out.println(Arrays.toString(arr));
        char c = 'z';
        System.out.println((int)'A' + " "+ (int)'Z');
    }
}
