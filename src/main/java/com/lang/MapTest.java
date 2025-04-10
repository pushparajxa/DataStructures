
package com.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;

public class MapTest {
  public static void main(String[] args) {
    Map<String,Integer> map = new HashMap<>();
    map.merge("First",1,(old,val)->old+val);
    System.out.println(map.get("First"));
    System.out.println(map);
  
    Set<String> set = new HashSet<>();
    set.add("Fisrt");
    set.add("second");
  
    System.out.println("Printing set=" + set);
    
/*    topKFrequent(new int[]{12,23,4,2,1,1,2,23,12,12}, 2);
    
    Random random = new Random();
    random.nextInt(2323,23);
    ArrayList<Integer> lst = new ArrayList<>();
    Integer[] array = lst.subList(0, 1).toArray(new Integer[23]);
    Arrays.stream(array).toArray();
   // lst.subList(0, 1).stream().mapToInt(x->x).toArray()*/
    
  }
  
  
  public static int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int len = nums.length;
    for(int i=0;i<len;i++){
      map.merge(nums[i], 1, (oldVal, newVal) -> oldVal + newVal);
    }
    System.out.println(map);
    return new int[10];
  }
}
