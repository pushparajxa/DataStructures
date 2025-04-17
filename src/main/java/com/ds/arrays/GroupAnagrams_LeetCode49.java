/*
 ** COPYRIGHT **
 */
package com.ds.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams_LeetCode49 {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        
        int  [] cnt = new int[26];
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String s: strs){
            Arrays.fill(cnt, 0);
            char[] c = s.toCharArray();
            for(int i=0;i<c.length;i++){
                cnt[c[i] - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<26;i++){
                /**
                 * We need to append with # otherwise we will get wrong result when the count
                 * increases to more than 10
                 * 01010  bbbbbbbbbbc
                 * 01010  bdddddddddd
                 *
                 * In the above example both the strings result in the same value 01010 which is
                 * incorrect.
                 *
                 * By appending with # we get
                 * 0#10#1#0
                 * 0#1#0#10
                 *
                 * which are different.
                 *
                 */
                sb.append(cnt[i]).append("#");
                
            }
            System.out.println(s +". and its sb " + sb.toString());
            map.computeIfAbsent(sb.toString(),  x-> new ArrayList<String>()).add(s);
            
        }
        
        return new ArrayList(map.values());
    }
}