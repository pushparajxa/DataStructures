
package com.ds.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache_LinkedHashSet {
    
    
    public static void main(String[] args) {
        
        LinkedHashMap<String, Object> lruCache = new LRUCache<>(100, 0.4f, 3);
    
        // This is the least recently accessed one. 1 was put first into the map.
        lruCache.put("1", "First");
        lruCache.put("2", "Second");
        lruCache.put("3", "Third"); // This is the most recently accessed one. 3 was put in at the last.
    
    
        System.out.println(lruCache.entrySet().toString());
        lruCache.get("2");
        System.out.println(lruCache.entrySet().toString());
        
        lruCache.put("4", "Fourth");
        System.out.println(lruCache.entrySet().toString());
        
    }
    
    private static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        
        private final int cacheSize;
        
        LRUCache(int initialCapacity, float loadFactor, int cache_size) {
            super(initialCapacity, loadFactor, true);
            this.cacheSize = cache_size;
            
        }
        
        @Override
        public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > cacheSize;
        }
        
    }
    

    

}
