package com.ds.sort;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
//Stable
public class BucketSort {
    // array : Assuming all the input elements in the array are greater than or equal to zero.
    private static void sortArray(int[] arr) {
        int numberOfBuckets = 10;
        LinkedList<Integer> buckets[] = new LinkedList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        //Distribute elements into the buckets
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]/numberOfBuckets >= numberOfBuckets)
                buckets[numberOfBuckets-1].add(arr[i]);
            else
                buckets[arr[i]/numberOfBuckets].add(arr[i]);
        }
        //Sort the buckets
        for (int i = 0; i < numberOfBuckets; i++) {
            sort(buckets[i]);
        }
        //Collect the elements in the buckets
        int j = 0;
        for (int i = 0; i < numberOfBuckets; i++) {
            LinkedList<Integer> bucket = buckets[i];
            ListIterator<Integer> listIterator = bucket.listIterator();
            while (listIterator.hasNext()) {
                arr[j] = listIterator.next();
                j++;
            }
        }
    }

    private static void sort(LinkedList<Integer> list) {
        //Insert sort the list
        for(int i=0;i<list.size();i++){
            int indexToInsert = getTheIndexToInsert(list,i);
            if(indexToInsert!=i){
                int val = list.get(i);
                list.remove(i);
                list.add(indexToInsert,val);
            }
        }
    }

    private static int getTheIndexToInsert(LinkedList<Integer> list,int valIndex) {
        int value = list.get(valIndex);
        int toReturn=valIndex;
        for(int i = valIndex-1;i>=0;i-- ){
            if(list.get(i)<=value){
                return i+1;
            }else{
                toReturn = i;
            }
        }
        return toReturn;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int[] arr = new int[]{3,2,2};
        BucketSort.sortArray(arr);
        System.out.println(Arrays.toString(arr));
    }

}
