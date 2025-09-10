/*
 ** COPYRIGHT **
 */
package com.ds.dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DeleteAndEarn {
    
    public static void main(String[] args) {
    
            DeleteAndEarn deleteAndEarn = new DeleteAndEarn();
            deleteAndEarn.deleteAndEarn(new int[]{8,10,4,9,1,3,5,9,4,10});
        
        
    }
    
    public int deleteAndEarn(int[] nums) {
        
        if(nums.length ==1)
            return nums[0];
        
        if(nums.length ==0 )
            return 0;
        
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if(treeMap.containsKey(nums[i])){
                int cnt = treeMap.get(nums[i]);
                treeMap.put(nums[i], cnt+1);
            }
            else {
                treeMap.put(nums[i], 1);
            }
        }
        
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        
        HashMap<Integer, Integer> pointsMap = new HashMap<>();
        
        ArrayList<Integer> list = new ArrayList<>();
        int res = 0;
        
        
        for(Map.Entry<Integer, Integer> entry: treeMap.entrySet()){
            System.out.println("Key is =" + entry.getKey() + " and value is " + entry.getValue());
            
            int key = entry.getKey();
            int cnt = entry.getValue();
            
            
            if(list.size() ==0){
                res = key*cnt;
                list.add(key);
                pointsMap.put(key, res);
            }
            else {
                int lSize = list.size();
                
                int lVal = list.get(lSize-1);
                
                if(lVal == key-1){
                    if(lSize==1){
                        int val = Math.max(0 + key*cnt,
                            pointsMap.get(list.get(lSize-1)));
                        res  = Math.max(res, val);
                        list.add(key);
                        pointsMap.put(key, res);
                        
                    }
                    else {
                        int val = Math.max(pointsMap.get(list.get(lSize-2)) + key*cnt,
                            pointsMap.get(list.get(lSize-1)));
                        res  = Math.max(res, val);
                        list.add(key);
                        pointsMap.put(key, res);
                    }
                }
                else {
                    //lVal < key -1
                    int val = key*cnt + pointsMap.get(lVal);
                    res = Math.max(res, val);
                    list.add(key);
                    pointsMap.put(key, res);
                }
                
                
            }
            
            System.out.println("\t List="+ list);
            System.out.println("\t pointsMap=" +  pointsMap);
            
            
        }
        
        
        return res;
        
    }
    
    
    
    
    public int deleteAndEarn2(int[] nums) {
        
        if(nums.length ==1)
            return nums[0];
        
        if(nums.length ==0 )
            return 0;
        
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if(treeMap.containsKey(nums[i])){
                int cnt = treeMap.get(nums[i]);
                treeMap.put(nums[i], cnt+1);
            }
            else {
                treeMap.put(nums[i], 1);
            }
        }
        
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        
        HashMap<Integer, Integer> pointsMap = new HashMap<>();
        
        ArrayList<Integer> list = new ArrayList<>();
        int res = 0;
        
        
        for(Map.Entry<Integer, Integer> entry: treeMap.entrySet()){
            System.out.println("Key is =" + entry.getKey() + " and value is " + entry.getValue());
            
            int key = entry.getKey();
            int cnt = entry.getValue();
            
            
            
            if(list.size() ==0){
                int points = key*cnt;
                pointsMap.put(key, points);
                indexMap.put(key,list.size());
                list.add(key);
            }else {
                
                if(indexMap.containsKey(key-1)){
                    
                    int index = indexMap.get(key-1);
                    int basePoints ;
                    if(index ==0){
                        basePoints = 0;
                    }
                    else {
                        basePoints = pointsMap.get(list.get(index-1));
                    }
                    
                    int val = Math.max(key*cnt + basePoints,
                        pointsMap.get(list.get(index)));
                    pointsMap.put(key, val);
                    
                    res = Math.max(res,val);
                    indexMap.put(key, list.size());
                    list.add(key);
                    
                    
                }else {
                    
                    // find the last key that is smaller than k-1.
                    for(int i = list.size()-1; i>=0; i--){
                        if(list.get(i) < key -1){
                            
                            indexMap.put(key, list.size());
                            list.add(key);
                            
                            int val =  Math.max(pointsMap.get(list.get(i))+ key*cnt,
                                pointsMap.get(list.get(list.size()-1)));
                            pointsMap.put(key, val);
                            
                            res = Math.max(res, val);
                            
                        }
                        else {
                            throw new RuntimeException("This should not happen");
                        }
                    }
                    
                }
                
            }
            
            
            System.out.println("\t List=" + list);
            System.out.println("\t PointsMap=" + pointsMap);
            System.out.println("\t IndexMap=" + indexMap);
            
            
        }
        
        
        return res;
        
    }
    
    
    

}
