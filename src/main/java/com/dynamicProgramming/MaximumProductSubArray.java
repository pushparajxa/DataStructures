
package com.dynamicProgramming;

import java.util.ArrayList;

public class MaximumProductSubArray {

  public static void main(String[] args) {
   int result =  maxProduct(new int[]{3,-1,4});
    System.out.println(result);
  }

  /*

  Runtime: 1 ms, faster than 99.23% of Java online submissions for Maximum Product Subarray.
  Memory Usage: 37.5 MB, less than 99.86% of Java online submissions for Maximum Product Subarray.

   */
  //O(N) extra space O(1)
  public static int maxProduct(int[] nums) {
    int maxComplete=nums[0];
    int max=nums[0], min=nums[0],tempMax;

    for(int i=1;i<nums.length;i++){
      if(nums[i]==0){
        max=0;min=0;
        maxComplete=Math.max(maxComplete,0);
      }else if(nums[i]>0){
        if(max==0){
          max = nums[i];
        }else if(max>0){
          max = max*nums[i];
        }else {
          max = nums[i];
        }

        if(min==0){
          min = nums[i];
        }else if(min>0){
          min = nums[i];
        }else{
          min = min*nums[i];
        }

        maxComplete=Math.max(maxComplete,max);
      }else{
        tempMax = max;
        if(max==0){
          max = nums[i];
        }else if(max>0){
          if(min<0){
            max = min*nums[i];
          } else if(min==0){
            max = nums[i];
          }else{
            max = nums[i];
          }
        }else{
          max = Math.max(min*nums[i],max*nums[i]);
        }

        if(min==0){
          min= nums[i];
        }else if(min>0){
          if(tempMax>0){
            if(tempMax>min){
              min = tempMax*nums[i];
            }else{
              min = min *nums[i];
            }

          }else{
            min = min*nums[i];
          }

        }else{
          if(tempMax>0){
            min = tempMax*nums[i];
          }else{
            min = nums[i];
          }

        }
        maxComplete=Math.max(maxComplete,max);
      }

    }
    return maxComplete;
  }

  //O(N^2)
  public static int maxProduct2(int[] nums) {
    int [] sum = new int[nums.length];
    ArrayList<Integer>  zeroIndices = new ArrayList<>();

    sum[0]= nums[0];
    int max=nums[0];
    if(sum[0]==0){
      zeroIndices.add(0);
    }
    for(int i=1;i<nums.length;i++){
      if(nums[i]==0){
        sum[i]=0;
        zeroIndices.add(i);
      }else if(nums[i-1]==0){
        sum[i]=nums[i];
      }else{
        sum[i] = sum[i-1]*nums[i];
      }

      if(max<nums[i]){
        max= nums[i];
      }

    }

    int temp;
    for(int i=2;i<=nums.length;i++){
      for(int j=0; j+i-1<=nums.length-1;j++){
        if(sum[j+i-1]==0 || sum[j]==0|| Math.abs(sum[j+i-1])<Math.abs(sum[j]) || anyZeroInBetween(j+i-1, j, zeroIndices)){
          temp=0;
        }else{
          temp = sum[j+i-1]/sum[j] * nums[j];
        }

        if(max<temp){
          max = temp;
        }
      }
    }
    return max;
  }

  private static boolean anyZeroInBetween(int end, int start, ArrayList<Integer> zeroIndices) {
      for(int index : zeroIndices){
        if(index>=start && index<=end){
          return true;
        }
      }
      return false;
  }

}
