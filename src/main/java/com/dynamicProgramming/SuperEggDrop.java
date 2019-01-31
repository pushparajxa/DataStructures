
package com.dynamicProgramming;

import java.util.HashMap;

/*
https://leetcode.com/problems/super-egg-drop/
 */
public class SuperEggDrop {

  public static void main(String[] args) {

  }

  public int superEggDrop(int k, int n) {

    if(k==1 && n==1){
      return 1;
    }
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(3,2);
    map.put(2,2);
    calculate(1,n,map);




    return n;
  }

  private void calculate(int i, int n, HashMap<Integer, Integer> map) {


  }

}
