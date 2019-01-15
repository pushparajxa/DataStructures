
package com.lang;

import java.util.HashMap;

public class MapDefaultValue {

  public static void main(String[] args) {
    HashMap<String, Object> map = new HashMap<>();
    Boolean value = (Boolean)map.getOrDefault("VaL",null);
    System.out.println(value);
    Object b = null;
    String s = (String)b;
    System.out.println(s);

  }

}
