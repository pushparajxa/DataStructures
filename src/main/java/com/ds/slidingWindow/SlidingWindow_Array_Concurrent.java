/*
 ** COPYRIGHT **
 */
package com.ds.slidingWindow;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SlidingWindow_Array_Concurrent {
    
    int s, e;
    int arr[] = new int[10];
    
    int wSize;
    
    
    private synchronized boolean add(){
        if(wSize==10)
            return false;
        else {
            
            wSize++;
            return true;
        }
    }
    
    private synchronized void move(){
    
    }
    
    public static void main(String[] args) {
        
        SlidingWindow_Array_Concurrent sArray = new SlidingWindow_Array_Concurrent();
        
        
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                sArray.move();
            }
            
        },1, TimeUnit.SECONDS);

    }
}
