
package com.dynamicProgramming;

import java.util.Arrays;

public class MinimumNumberOfJumps {

  public static void main(String[] args) {

   // int input[] = {1 ,4 ,3,2,6,7};
    int input[]= {1, 3, 5 ,8, 9, 2, 6, 7, 6, 8, 9};
    int minValues[] = new int[input.length];
    minValues[0]=0;
    int temp,min;
    for(int i=1;i<input.length;i++){
      min=Integer.MAX_VALUE;
      for(int j=i-1;j>=0;j--){
        if(input[j]+j>=i && minValues[j]!=-1){
          temp = minValues[j]+1;
          if(min>temp){
            min = temp;
          }
        }
      }
      if(min==Integer.MAX_VALUE){
        minValues[i]=-1;
      }else {
        minValues[i] = min;
      }
    }

    //System.out.println(Arrays.toString(minValues));
    System.out.println(minValues[input.length-1]);
  }

}
