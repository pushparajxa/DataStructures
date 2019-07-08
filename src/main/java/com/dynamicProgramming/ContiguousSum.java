
package com.dynamicProgramming;

public class ContiguousSum {

  public static void main(String[] args) {

    boolean result = checkSubarraySum(new int[]{0,0},0);
    System.out.println(result);

  }


  public static boolean checkSubarraySum(int[] nums, int k) {

    int [] sum = new int[nums.length];

    sum[0]= nums[0];
    for(int i=1;i<nums.length;i++){
      sum[i] = sum[i-1]+nums[i];
    }

    int temp;
    for(int i=2;i<=nums.length;i++){
      for(int j=0; j+i-1<=nums.length-1;j++){
        temp = sum[j+i-1]-sum[j] + nums[j];
        if(k!=0 && temp%k ==0){
          return true;
        }else if(k==0 && temp ==0){
          return true;
        }

      }
    }
    return false;
  }

}
