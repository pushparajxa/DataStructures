
package com.lang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
  }
}
