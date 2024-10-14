/*
 ** COPYRIGHT **
 */
package com.ds.arrays;


// H index, H-Index
public class H_Index_Binary_Search_275 {
    
    public int hIndex(int[] citations) {
        int start = 0;
        int end = citations.length - 1;
        
        while (start < end) {
            int mid = (start + end) / 2;
            
            if (citations[mid] == end - mid + 1) {
                return end - mid + 1;
            } else if (citations[mid] > end - mid + 1) {
                int c = citations[mid];
                
                int i = 1;
                while (i <= c - (end - mid +1) && mid -i >= start) {
                    if (citations[mid - i] == c) {
                        i++;
                    } else if (citations[mid - i] > c) {
                        // this case won't occur. As the array is already sorted.
                        // elements while going back are less or equal to the
                        // end - mid +1;
                    } else {
                        if (citations[mid - i] > end - mid + 1 + i) {
                            i++;
                        }
                        
                        else if (citations[mid- i] == end - mid + 1 + i) {
                            return end - mid + 1 + i;
                        } else {
                            return end - mid + 1 + i - 1;
                        }
                        
                    }
                    
                }
                int delta = c - (end - mid +1);
                if(mid-delta>start)
                    return c; // end - (mid-delta) +1 is equal to c
                else
                    return end - start +1;
                
            } else {
                start = mid + 1;
            }
        }
        
        return citations[start] == 0 ? 0 : 1;
        
    }
    
    public static void main(String[] args) {
        int [] input = new int[]{1,1,1,1,3,3,4,4,5,6,7,7,8,9,10};
        input = new int[]{1,1,2,2,3,3,4,4,5,5};
        
        H_Index_Binary_Search_275 hIndex = new H_Index_Binary_Search_275();
        
        System.out.println(hIndex.hIndex(input));
    }
}