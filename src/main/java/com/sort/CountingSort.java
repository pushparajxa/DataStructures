
package com.sort;

import static com.general.ArrayUtils.*;

//Stable
public class CountingSort {

    private static void sortArray(int[] input) {
        int min;
        int max;
        try {
            min = findMinimum(input);
            max = findMaximum(input);
        }catch(RuntimeException e){
            if(e.getMessage().equals(EMPTY_ARRAY))
                return;
            else
                throw e;
        }
        int range = (max-min+1);
        if(range>Integer.MAX_VALUE)
            throw new RuntimeException("Range can't be greater than Integer Maximum value");
        int[] rangeArray = new int[Math.max(range,input.length)];
        int[] output = new int[input.length];

        for(int i = 0;i<input.length;i++) {
            rangeArray[input[i]-min] = rangeArray[input[i]-min]+1;
        }

        for(int i = 1;i<rangeArray.length;i++) {
            rangeArray[i] = rangeArray[i]+rangeArray[i-1];
        }

        //Start from the end to maintain the stable property of Counting Sort
        for(int i = input.length-1;i>=0;i--) {
            output[rangeArray[input[i]-min]-1] = input[i];
            rangeArray[input[i]-min] = rangeArray[input[i]-min]-1;
        }

        System.arraycopy(output,0,input,0,output.length);
    }


    public static void main(String[] args) {
        sortArray(new int[]{3,7,1});
    }

}
