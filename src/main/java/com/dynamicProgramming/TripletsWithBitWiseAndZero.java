
package com.dynamicProgramming;

import java.util.HashMap;

//https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/discuss?currentPage=1&orderBy=hot&query=
public class TripletsWithBitWiseAndZero {

  public int countTriplets(int [] A) {
    int temp[] = new int[1<<16];
    for(int a: A){
      for(int i=0;i<temp.length;i++){
        if((a&i)==0){
          temp[i] = temp[i]+1;
        }
      }

    }

    int result =0;
    for(int a : A){
      for(int b :A){
        result = result + temp[a&b];
      }
    }


    return result;
  }


  public int countTriplets2(int[] A) {
    HashMap<Integer,Integer> map = new HashMap<>();
    int temp;
    for(int a: A){
      for(int b: A){
        temp = a&b;
        map.put(temp, map.getOrDefault(temp,0)+1);
      }
    }

    int res =0;
    for(int a: A){
      for(int val : map.keySet()){
        if((a & val)==0){
          res += map.get(val);
        }
      }
    }
    return res;
  }

}
