package com.ds.arrays;
//https://www.geeksforgeeks.org/duplicates-array-using-o1-extra-space-set-2/
public class FindRepeatedNumbersInAnArray {

    //Works with 0s as well.. doesn't work with mix of positive & negative numbers.
    static void printRepeating( int arr[], int n)
    {
        // First check all the values that are
        // present in an array then go to that
        // values as indexes and increment by
        // the size of array
        for (int i = 0; i < n; i++)
        {
            int index = arr[i] % n;
            arr[index] += n;
        }

        // Now check which value exists more
        // than once by dividing with the size
        // of array
        for (int i = 0; i < n; i++)
        {
            if ((arr[i]/n) > 1){
                System.out.println(i);
            }

        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 2,1,1,1,2,2,2,3,3,3};
        //int arr[] = {0, 1, 2, 3, 4, 5, 0};
       printRepeating( arr, arr.length);
     //   printRepeats(arr);
    }

    //Doesn't work with 0s & mix of positive & negative numbers
    static void printRepeats(int [] arr){
        for(int i=0;i<arr.length;i++){
            int jump = Math.abs(arr[i]);
            if(arr[jump]<0){
                System.out.println(jump);
            }else{
                arr[jump] = -arr[jump];
            }
        }
    }

}
