
package com.ds.dynamicProgramming;
//https://leetcode.com/problems/house-robber-ii/submissions/
public class Robberers {

  public static void main(String[] args) {

    int [] input = {1,2,1,1};
    int result = rob(input);
    System.out.println(result);

  }

  public static int rob(int[] nums) {
    if(nums.length==0 ){
      return 0;
    }
    if(nums.length==1){
      return nums[0];
    }
    if(nums.length==2){
      return Math.max(nums[0],nums[1]);
    }
    if(nums.length==3){
      return Math.max(nums[0],Math.max(nums[1],nums[2]));
    }

    return Math.max(nums[0] + getMax(nums,2,nums.length-2) , getMax(nums,1,nums.length-1));
  }

  static int getMax(int[] nums, int start, int end){
    if(start==end){
      return nums[start];
    }
    if(end == start+1){
      return Math.max(nums[start],nums[end]);
    }
    if(end == start+2){
      return Math.max(nums[start],Math.max(nums[start+1],nums[start+2]));
    }

    int temp[] = new int[end-start+1];
    System.arraycopy(nums,start,temp,0,end-start+1);
    temp[0] = nums[start];
    temp[1] = Math.max(temp[0],temp[1]);
    for(int i=2; i<temp.length; i++){
      temp[i] = Math.max(temp[i-1],temp[i]+temp[i-2]);
    }
    return temp[end-start];
  }

}
