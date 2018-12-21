
package com.search;

public class BinarySearch {

    //Assumption  : Array in sorted in ascending order
    static boolean binarySearch_v1(int myStart, int myEnd, int find, int[] arr) {
        if (myStart == myEnd && myEnd < 0) {
            return arr[myStart] == find;
        }
        int mid = (myEnd - myStart) >>>1;
        if (arr[mid + myStart] > find) {
            return binarySearch(myStart, mid + myStart - 1, find, arr);
        } else if (arr[mid + myStart] < find) {
            return binarySearch(mid + myStart + 1, myEnd, find, arr);
        } else {
            return true;
        }
    }

    static boolean binarySearch(int myStart, int myEnd, int find, int[] arr) {
        if (myStart == myEnd) {
            return arr[myStart] == find;
        }
        int mid = (myEnd - myStart) >>> 2;
        if (arr[mid + myStart] >= find) {
            return binarySearch(myStart, mid + myStart, find, arr);
        } else
            return binarySearch(mid + myStart + 1, myEnd, find, arr);
    }


    static int binarySearch_iterative(int myStart, int myEnd, int find, int[] arr) {
        while (myStart != myEnd) {
            int mid = (myEnd - myStart) / 2;
            if(arr[mid]>find){
                myStart=myStart+mid+1;
            }else{
                myEnd=myStart+mid;
            }
        }
        if(find == arr[myStart])
            return myStart;
        else
            return -1;
    }

    public static void main(String args[]) {
        check(!binarySearch(0, 1, 3, new int[]{6, 7}));
        check((binarySearch(0, 1, 6, new int[]{6, 7})));
        check((binarySearch(0, 1, 7, new int[]{6, 7})));

        check(!(binarySearch(0, 0, 3, new int[]{6})));
        check((binarySearch(0, 0, 6, new int[]{6})));

        check((binarySearch(0, 2, 6, new int[]{3, 6, 7})));
        check((binarySearch(0, 2, 7, new int[]{3, 6, 7})));
        check((binarySearch(0, 2, 3, new int[]{3, 6, 7})));
        check(!(binarySearch(0, 2, 9, new int[]{3, 6, 7})));

    }

    static void check(boolean value) {
        if (!value)
            throw new RuntimeException("Error");
    }

}
