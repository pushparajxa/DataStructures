/*
 ** COPYRIGHT **
 */
package com.ds.strings;

import java.util.HashMap;

public class Longest_Substring_Without_Repeating_Characters_3 {
    
    public int lengthOfLongestSubstring(String s) {
        char [] arr = s.toCharArray();
        
        
        // a b c p q b
        int i=0, len = arr.length,j=1;
        HashMap<Character, Integer> map = new HashMap<>();
        
        if(len ==0 || len ==1){
            return len;
        }
        
        // abcabcbb
        
        map.put(arr[i],0);
        
        int max=1;j=1;
        while(i<len && j<len){
            
            if(map.containsKey(arr[j])){
                
                if(map.get(arr[j]) < i){
                    map.put(arr[j],j);
                    max = Math.max(max, j-i+1);
                    j++;
                }
                else {
                    i = map.get(arr[j]) + 1;
                    map.put(arr[j], j);
                    max = Math.max(max, j-i+1);
                    j++;
                }
            }
            else {
                map.put(arr[j], j);
                max = Math.max(max, j-i+1);
                j++;
            }
            
        }
        return Math.max(max, j-i);
    }
    
    public int lengthOfLongestSubstring2(String s) {
        char [] arr = s.toCharArray();
        
        
        // a b c p q b
        int i=0, len = arr.length,j=1;
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Integer, Character> rev = new HashMap<>();
        
        if(len ==0 || len ==1){
            return len;
        }
        
        // abcabcbb
        
        map.put(arr[i],0);
        rev.put(0, arr[i]);
        int max=1;j=1;
        while(i<len && j<len){
            if(map.containsKey(arr[j])){
                max = Math.max(max, map.size());
                int k=i;
                i = map.get(arr[j]) + 1;
                
                while(k<i){
                    char c = rev.get(k);
                    map.remove(c);
                    rev.remove(k);
                    k++;
                }
                map.put(arr[j],j);
                rev.put(j, arr[j]);
                
                j++;
                
            }
            else {
                map.put(arr[j], j);
                rev.put(j, arr[j]);
                j++;
            }
            
        }
        return Math.max(max, map.size());
    }
}