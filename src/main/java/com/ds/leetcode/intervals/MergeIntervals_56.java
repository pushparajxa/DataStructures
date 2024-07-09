
package com.ds.leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeIntervals_56 {
    
    
    public static void main(String[] args) {
        int [][] arr = {
            
            {1,2,3},
            {4,5,6}
            
        };
    
     //   System.out.println(Arrays.deepToString(arr));
        
        int [][] input = {
                            {1, 3},
                            {2, 6},
                            {8, 10},
                            {15, 18}
                         };
    
        int[][] merge = merge(input);
    
        System.out.println(Arrays.deepToString(merge));
    }
    
    public static  int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])
                    return 0;
                else if (o1[0] >o2[0])
                    return 1;
                else
                    return -1;
            }
        });
    
        PriorityQueue<int []> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1])
                    return 0;
                else if (o1[1] > o2[1])
                    return 1;
                else
                    return -1;
            }
        });
    
        List<int []> res = new ArrayList<>();
        
        queue.add(intervals[0]);
      //  int count = 0;
    
        for (int i = 1; i < intervals.length ; i++) {
            int start = intervals[i][0];
            int end   = intervals[i][1];
    
            int [] top  = queue.peek();
            
            if (top[1] >= start) {
                if(top[1] >= end){
                    // do nothing
                }
                else{
                    
                    queue.poll();
                    queue.add(new int[]{top[0], end});
                }
            }
            else {
              //  count++;
                res.add(queue.poll());
                queue.add(intervals[i]);
                
            }
        }
        
        res.add(queue.poll());
        
        return res.toArray(new int[res.size()][]);
    }
}
