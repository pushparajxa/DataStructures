
package com.ds.leetcode;

import java.util.LinkedHashMap;

public class LinkedHashMapTest {
    
    public static void main(String[] args) {

    
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(16, .75f, true);
        map.put(1, null);
        map.put(2, null);
        map.put(3, null);
    
        System.out.println(map.keySet().toString());
    
        map.get(2);
        System.out.println(map.keySet().toString());
    
        LinkedHashMap<String, Object> lruCache = new LinkedHashMap<>(16, 0.75f,true);
    
        // This is the least recently accessed one. 1 was put first into the map.
        lruCache.put("1", null);
        lruCache.put("2", null);
        lruCache.put("3", null);
    
        System.out.println(lruCache.keySet().toString());
    
        lruCache.get("2");
        System.out.println(lruCache.keySet().toString());
    
    }
}
