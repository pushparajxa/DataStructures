/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {
    
    
    public static void main(String[] args) {
        int [] input = new int[]{2,1,5,6,2,3};
        
        // result = [1,-1,4,4,-1,-1]
        
        LargestRectangleInHistogram largestRectangleInHistogram =
            new LargestRectangleInHistogram();
        largestRectangleInHistogram.largestRectangleArea(input);
    }
    
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if(len ==0)
            return 0;
        if(len ==1)
            return heights[0];
        if(len ==2)
            return 2* Math.min(heights[0], heights[1]);
        
        // Construct monotonic stack decreasing.
        
        int [] mStack = new int[heights.length];
        
        for (int i = 0; i < mStack.length; i++) {
            mStack[i] = -1;
        }
        
        record pair(int index, int value) {};
        
        Stack<pair> stack = new Stack<>();
        
        stack.push(new pair(0, heights[0]));
        
        // 2 1 5 6 2 3
        //
        int i=1;
        while(i<len){

            while(!stack.isEmpty() && stack.peek().value > heights[i]) {
                mStack[stack.peek().index] = i;
                stack.pop();
            }
            stack.push(new pair(i, heights[i]));
            i++;
        
        }
        
      //  System.out.println(Arrays.toString(heights));
      //  System.out.println(Arrays.toString(mStack));
        
        int [] areas = new int[len];
        int total_max = Integer.MIN_VALUE;
        
        for (int j = len-1; j >=0 ; j--) {
            
            int a,b, max;
            
            if(mStack[j]==-1){
                a = ((len -1) -j +1 ) * heights[j];
                total_max = Math.max(total_max, a);
                areas[j] = a;
            }else {
                int ind = j;
                int k = j;
                a = Integer.MIN_VALUE;
                while(mStack[k]!=-1 && k>=0 /*k>=0 may not be needed. */){
                    a = Math.max(a, ((mStack[k]-1) - j +1)*heights[k]);
                    k = mStack[k];
                }
                
                a = Math.max(a, ((len-1)-j+1)*heights[k]);
                areas[ind] = a;
                total_max = Math.max(total_max, a);
               
            }
           
        }
     //   System.out.println(Arrays.toString(areas));
       
        return total_max;
        
    }

}
