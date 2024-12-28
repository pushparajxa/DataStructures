/*
 ** COPYRIGHT **
 */
package com.ds.binarySearch;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import javax.lang.model.util.ElementScanner14;


public class TransactionsData_Sofi {
    
    
    /**
     * The Transaction list captures events as they happened, with each transaction entry being
     * recorded at its respective timestamp. Each Transaction object includes: A timestamp in
     * seconds (int) A transaction ID (String) Given this list of transactions and a range [min,max]
     * (inclusive), write a function to determine the number of transactions that occurred within
     * the specified range.
     **/
    
    public static void main(String[] args) {
        // 2 6 7 9 15
        // 3 8
        // 6 7 ==>2
        
        int res = 0;
       /*     res =  getTransactionCount(Collections.emptyList(), 2,3); /// res =0
            
            res =  getTransactionCount(Collections.emptyList(),2,2); // 0
            
            res =  getTransactionCount(List.of(new Transaction(23, "id1")),2,3 ); // 0
            
            res =  getTransactionCount(List.of(new Transaction(23, "id1")),2,2 ); //0
            
            res =  getTransactionCount(List.of(new Transaction(23, "id1")),23,23 ); //1
            
            res =  getTransactionCount(List.of(new Transaction(23, "id1")),34,45 ); //0
            
            res =  getTransactionCount(List.of(new Transaction(23, "id1")),1,5 ); //0
            */
        
        List<Transaction> txs = List.of(new Transaction(23, "id1"), new Transaction(25, "id1"),
            new Transaction(26, "id1"));
           /*
            res =  getTransactionCount(txs,2,3 ); // 0
            
           // System.out.println(res);
            
            res =  getTransactionCount(txs,2,2 ); //0
            
            res =  getTransactionCount(txs,23,23 ); //1
            
            res =  getTransactionCount(txs,34,45 ); //0
        
            res =  getTransactionCount(txs,23,25 ); // 2
            
            res =  getTransactionCount(txs,23,26 );  //3
            
            res =  getTransactionCount(txs,26,26 ); //1
            
            res =  getTransactionCount(txs,25,25 ); //1
            
            res =  getTransactionCount(txs,22,24 ); //1
            
            res =  getTransactionCount(txs,25,27 ); //2
            
            res =  getTransactionCount(txs,21,27 ); //3
            
            */
        // 2 6 7 9 15
        
        txs = List.of(new Transaction(2, "id1"),
            new Transaction(6, "id2"),
            new Transaction(7, "id3"),
            new Transaction(9, "id2"),
            new Transaction(15, "id3"));
        
        res = getTransactionCount(txs, 3, 8); //2
        System.out.println(res);
        
    }
    
    
    static int getTransactionCount(List<Transaction> ts, int min, int max) {
        
        if (ts.isEmpty()) {
            return 0;
        }
        
        if (ts.size() == 1) {
            int t = ts.get(0).ts;
            
            if (min == t || max == t) {
                return 1;
            } else if (min < t && t < max) {
                return 1;
            } else {
                return 0;
            }
        }
        
        int minT = ts.get(0).ts;
        
        int maxT = ts.get(ts.size() - 1).ts;
        
        int minStart = 0, maxStart = 0;
        
        if (min <= minT) {
            minStart = 0;
        } else {
            // binary search to find greatest element that is smaller than min
            minStart = getMinIndex(ts, min);
            System.out.println("MinStart=" + minStart);
            
            
        }
        
        if (max >= maxT) {
            maxStart = maxT;
        } else {
            // binary search to find snallest element that is greater than max
            maxStart = getMaxIndex(ts, max);
            System.out.println("MaxStart=" + maxStart);
            
        }
        
        return maxStart - minStart + 1;
        
        
    }
    
    
    private static int getMinIndex(List<Transaction> ts, int num) {
        
        int s = 0;
        int e = ts.size();
        
        int mid = (s + e) / 2;
        
        boolean done = false;
        // 2 6 7 9 15
        // 3 8
        
        while (s < e) {
            System.out.println("Start = " + s + ", end=" + e);
            mid = (s + e) / 2;
            int t = ts.get(mid).ts;
            if (t == num) {
                return mid;
            }
            
            // 2 8 (1,5)
            if (t > num) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
            
        }
        
        if (ts.get(s).ts > num) {
            if (s != 0) {
                return s - 1;
            } else {
                return s;
            }
        } else {
            return s;
        }
        
    }
    
    private static int getMaxIndex(List<Transaction> ts, int num) {
        
        int s = 0;
        int e = ts.size();
        
        int mid = (s + e) / 2;
        
        boolean done = false;
        // 2 3 4 6 7 9 15
        // 5 8
        
        // 2 7 9 (9, 8)
        // 2 7  (3, 4)
        while (s < e) {
            mid = (s + e) / 2;
            int tMid = ts.get(mid).ts;
            if (tMid == num) {
                return mid;
            }
            
            // 2 8 (1,5)
            if (tMid < num) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
            
        }
        
        if (ts.get(e).ts < num) {
            if (e != 0) {
                return e + 1;
            } else {
                return e;
            }
        } else {
            return e;
        }
        
    }
    
    
    private static class Transaction {
        
        int ts;
        String id;
        
        Transaction(int ts, String id) {
            this.ts = ts;
            this.id = id;
        }
        
    }
    
    
}
