/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

import java.util.Arrays;
import java.util.Collections;

/*

    It is explained here https://pushparaj89.wordpress.com/datastructures/

 */
public class MaximumScoreFromMultiplcation_1770 {
    
    public static void main(String[] args) {
        
        
        MaximumScoreFromMultiplcation_1770 sol = new MaximumScoreFromMultiplcation_1770();
        
/*        sol.fun2(new int[]{23,23});
        
        sol.fun2(new int[][]{
            {23,23}
            
        });
        
        sol.fun2(new Object[]{});*/
        
       int [] nums = new int[]{1,2,3};
       int [] multipliers = new int[]{1, 2,3};
        
       /*
        nums = new int[]{-5,-3,-3,-2,7,1};
        multipliers = new int[] {-10,-5,3,4,6};
        */
        
       
      // int result = sol.maximumScore(nums, multipliers);
        
       //System.out.println(result);
        sol.fun3(nums, multipliers);
       
       /*
        for (int i = 1; i <= nums.length-1; i++) {
            for (int j = 0; j + i <=nums.length-1 ; j++) {
                int x = j;
                int y = i+j;
                System.out.print("(["+x+","+y+"]-->" + "[" + j +"," + (y-1) + "]" + "[" +(x+1) +
                    ","+ (y) + "]" +") --->");
                System.out.println();
              
                
            }
            System.out.println();
        }
        */
    }
    
    
    int fun2(Object [] arr){
        System.out.println("From  Object array");
        return 2;
    }
    
    int fun2(Object  arr){
        System.out.println("From  Object ");
        return 3;
    }
    
    
    
    public int maximumScore(int[] nums, int[] multipliers) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        if (nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
      
        /*
        int[][] arr = new int[len][len];
        for(int i=0;i<len;i++){
            for(int j=0; j<len;j++){
                arr[i][j] = -1;
            }
        }
        
        
      */
        int [] arr2 = new int[(len*(len+1))/2];
        
        for(int i=0;i<arr2.length  ;i++){
            arr2[i] = -1;
        }
       
        
      //  int res =  fun(0, len - 1, nums, arr, multipliers, 0);
       // System.out.println(res);
       int   res =  fun2(0, len - 1, nums, arr2, multipliers, 0);
       // System.out.println(res);
        //System.out.println( Arrays.deepToString(arr).replace("],", "],\n"));
      //  System.out.println( Arrays.deepToString(arr).replace("-23", "hre\n"));
      //  System.out.println(Arrays.toString(arr2));
       
        return res;
        
    }
    /*
    int fun3(int start, int end, int[] nums, int[][] arr, int [] multipliers, int index) {
        int len = arr.length;
        
        for (int k = 0; k < len; k++) {
        
        }
        for (int i = 0; i < len-1 ; i++) {
            for (int j = 0; j <=i; j++) {
                System.out.print(i+","+j+" ");
                
            }
            System.out.println();
        }
    }
    */
    
    
    /*
    
       0,0  1,1 2,2 3,3 4,4
       0,1 1,2 2,3, 3,4
       0,2 1,3 2,4
       0,3 1,4
       0,4
       
    
    
     */
    int fun3( int[] nums, int [] multipliers) {
        
        int mul_len = multipliers.length;
        int num_len = nums.length;
        int arr[];
        
        if(num_len == mul_len)
            arr = new int[mul_len+1];
        else
            arr = new int[num_len -(num_len-mul_len) + 1];
        
        int inc2 = num_len-mul_len;
        
       int cnt = mul_len-1;
       
       int start, end, inc;
       
       while(cnt>=0){
           /*
                [7 9 8 2] [11]
                
                0 0
                start = 0, end = 2
                start = 1, end = 3
                
                start = 0, end  = 3
                
                
                [7 9 8 2] [11 12]
                
                start = 0 , end = 1
                start = 1, end = 2
                start = 2, end = 3
                
                start = 0, end = 2
                start =1 , end =  3
                
                start = 0, end = 3
                
                0      0     0
                (s,e,
                
                
            */
           
          
           start = 0;
           end = start + inc2;
           int idx = 0;
           while(end<nums.length){
              
              int val = Math.max((nums[end]*multipliers[cnt] + arr[idx]),
                  (nums[start]*multipliers[cnt] + arr[idx+1])
                  );
               
              arr[idx] = val;
               System.out.println("start="+ start +", end ="+ end+", cnt="+ cnt+". Arr="+Arrays.toString(arr));
                start++ ;
                end = start + inc2;
                idx ++;
           }
           
           inc2++;
           
           cnt--;
       }
        
        System.out.println("Final Array contents=" + Arrays.toString(arr));
        return arr[0];
    
    }
    
    
    int fun2(int start, int end, int[] nums, int[] arr, int [] multipliers, int index) {
      //  System.out.println("(["+start+ "," + end +"], " + index+")" + arr.length + "," + nums
       //  .length);
        System.out.println("(["+start+ "," + end +"], " + index+")");
        int pointer = getIndex(start, end, nums.length);
        
        if(index>= multipliers.length)
            return 0;
        
        if(start == end) {
            arr[pointer] = nums[start];
            return nums[start]*multipliers[index];
        }
        
        if(arr[pointer]!=-1)
            return arr[pointer];
        
        //  System.out.println("(["+start+ "," + end +"], " + index+")");
        
        int val1 =
            nums[start] * multipliers[index] + fun2(start+1, end, nums, arr, multipliers,
                index+1);
        
        int val2 =  nums[end] * multipliers[index] + fun2(start, end-1, nums, arr, multipliers,
            index+1);
        
        
        arr[pointer] = Math.max(val1, val2);
        
        return arr[pointer];
    }
    
    int fun(int start, int end, int[] nums, int[][] arr, int [] multipliers, int index) {
        if(index>= multipliers.length)
            return 0;
       
        if(start == end) {
            arr[start][end] = nums[start];
            return nums[start]*multipliers[index];
        }
        
        if(arr[start][end]!=-1)
            return arr[start][end];
        
      //  System.out.println("(["+start+ "," + end +"], " + index+")");
        
        int val1 =
            nums[start] * multipliers[index] + fun(start+1, end, nums, arr, multipliers,
                index+1);
        
        int val2 =  nums[end] * multipliers[index] + fun(start, end-1, nums, arr, multipliers,
            index+1);
            
        
        arr[start][end] = Math.max(val1, val2);
        
      return arr[start][end];
    }
    
    
    int getIndex(int start, int end, int len){

        
        int result = 0;
        int i=0;
        while(i <= start-1){
            result += len - i;
            i++;
            
        }
        
        return result + (end - start);
        
        
    }
    
}
