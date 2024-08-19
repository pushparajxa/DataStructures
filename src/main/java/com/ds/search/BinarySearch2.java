
package com.ds.search;

public class BinarySearch2 {
    
    public static void main(String[] args) {
        
        
        int [] num = {0,1,2,3,4};
        System.out.println(search(num, 0));
        System.out.println(search(num, 1));
        System.out.println(search(num, 2));
        System.out.println(search(num, 3));
        
        System.out.println(search2(num, 0));
        System.out.println(search2(num, 1));
        System.out.println(search2(num, 2));
        System.out.println(search2(num, 3));
        
    }
    
    // Include the middle element in the lower half.
    static boolean search(int[] num, int val){
        
        int start  =0,  end = num.length-1;
        
        while(start!=end){
            int mid = (start + end)/2;
            
            if(val>num[mid]){
                start = mid+1;
            }
            else {
                end = mid;
            }
        }
        if(num[start] == val){
            return true;
        }
        else {
            return false;
        }
        
        
        
    }
    
    // This will not work.
    static boolean search2(int[] num, int val){
        
        int start  =0,  end = num.length-1;
        
        while(start!=end){
            int mid = (start + end)/2;
            
            if(val>=num[mid]){
                start = mid;
            }
            else {
                end = mid-1;
            }
        }
        if(num[start] == val){
            return true;
        }
        else {
            return false;
        }
        
        
        
    }
}
