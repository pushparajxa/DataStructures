
package com.ds.sort;

import static org.apache.commons.lang3.ArrayUtils.swap;

import java.util.Arrays;

public class Heap {
    
    
    public static void makeHeap(int [] a)
    {
        for (int i = a.length -1 ; i >0 ; i--) {
            if ( a[(i-1)/2] < a[i]) {
                
                swap(a, (i-1)/2, i);
            }
        }
        System.out.println(Arrays.toString(a));
    }
    
    public static void main(String[] args) {
        makeHeap(new int[]{13, 17, 0, 1, 3, 2, 16, 2, 5, 12, 3, 9, 6, 14, 5, 1, 14, 1, 11, 9});
    }
}
