
package com.lang.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericTypeCast {

  public static void main(String[] args) {

    GenericTypeCast  genericTypeCast = new GenericTypeCast();

    Map<String,List<Integer>> map =  (Map<String, List<Integer>>) genericTypeCast.getMap();
    List<Integer> first = map.get("First");
    System.out.println(first.size());
    /*Integer integer = first.get(0);
    System.out.println(first.get(0));*/

  }

  private Object getMap() {
    HashMap<String, List<String>> stringListHashMap = new HashMap<>();
    List<String> list = new ArrayList<>();
    list.add("hello");
    stringListHashMap.put("First",list);
    return stringListHashMap;
  }

}
