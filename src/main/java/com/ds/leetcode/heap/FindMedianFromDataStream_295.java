
package com.ds.leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

//Submitted
public class FindMedianFromDataStream_295 {
    
    public FindMedianFromDataStream_295() {
    
    }
    
   static  PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1>=o2 ? -1 : 1;
        }
    
    });
    
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1>=o2 ? 1 : -1;
        }
        
    });
    
    
    
    public static void addNum(int num) {
 
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        
        int min, max;
        
        if(minHeapSize > maxHeapSize) {
            min = minHeap.peek();
            if(num > min) {
                int elem = minHeap.poll();
                maxHeap.add(elem);
                minHeap.add(num);
            }
            else if(num == min){
                maxHeap.add(num);
            }
            else{
                maxHeap.add(num);
            }
        }
        else if(minHeapSize < maxHeapSize) {
            max = maxHeap.peek();
            if(num > max) {
                minHeap.add(num);
            }
            else if(num == max){
                minHeap.add(num);
            }
            else{
                int elem = maxHeap.poll();
                minHeap.add(elem);
                maxHeap.add(num);
            }
        }
        else {
            
            if(minHeapSize ==0) {
                minHeap.add(num);
                return;
            }
    
            min = minHeap.peek();
            if (num <= min) {
                maxHeap.add(num);
            }
            else {
                minHeap.add(num);
            }
        }
        
    }
    
    public static double findMedian() {
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        
        double median;
    
        if(minHeapSize == maxHeapSize) {
            median =  (minHeap.peek() + maxHeap.peek())/2d;
        }
        else if(minHeapSize < maxHeapSize) {
            median = maxHeap.peek();
        }
        else {
            median = minHeap.peek();
        }
        
        return median;
    }
    
    public static void main(String[] args) {
        addNum(1);
        System.out.println(findMedian());
        addNum(2);
        System.out.println(findMedian());
        addNum(3);
        System.out.println(findMedian());
    }
    
}
