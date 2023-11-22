package com.ds.math;

import java.util.TreeMap;

public class PrimeFactorization {
    public static void main(String[] args) {

        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        int n=121;

        int count=0;
        while(n%2==0){
            n=n/2;
            count++;
        }

        if(count!=0)
            treeMap.put(2,count);

        treeMap.put(2,count);
        for(int i = 3;i<=n;i=i+2) {
                count=0;
                while(n%i==0){
                    n = n/i;
                    count++;
                }
                if(count!=0)
                    treeMap.put(i,count);
        }
        //treeMap.keySet gives the keys in order fashion
        System.out.println(treeMap.toString());
    }
}
