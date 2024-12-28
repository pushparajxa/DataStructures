/*
 ** COPYRIGHT **
 */
package com.ds.slidingWindow;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class SlidingWindow_Concurrent {
    
    // The length of windos span in time(nano seconds).
    private long windowSpan;
    // Maximum allowed entries with in a window.
    private int count;
    
    DelayQueue<ItemDelay> dQueue = new DelayQueue<ItemDelay>();
    
    ConcurrentLinkedQueue<ItemDelay> slots ;
    
    SlidingWindow_Concurrent(int windowSpan, int count){
        this.windowSpan = TimeUnit.SECONDS.toNanos(windowSpan);
        this.count = count;
        this.slots = new ConcurrentLinkedQueue<>();
        
        for (int i = 0; i < count; i++) {
            slots.add(new ItemDelay(0));
        }
    }
    
    boolean add(int ele){
        ItemDelay delay = dQueue.poll();
        
        if(delay==null){
            ItemDelay slot = slots.poll();
            if(slot == null){
                return false;
            }
            else {
                delay.setExpiryTime(windowSpan);
                dQueue.add(slot);
                return true;
            }
            
        }
        else {
            delay.setExpiryTime(windowSpan);
            dQueue.add(delay);
            return true;
        }
    }
    
    
    
    
    private static class ItemDelay implements Delayed {
        
        public ItemDelay setExpiryTime(long expiryTime) {
            this.expiryTime = expiryTime + System.nanoTime();
            return this;
        }
        
        public long getExpiryTime() {
            return expiryTime;
        }
        
        
        private long expiryTime;
        
        ItemDelay(long expiryTime){
            this.expiryTime = expiryTime;
        }
        
        @Override
        public long getDelay(TimeUnit unit) {
            return  unit.convert((expiryTime - System.nanoTime()), TimeUnit.NANOSECONDS);
        }
        
        @Override
        public int compareTo(Delayed d2) {
            return Long.signum(expiryTime - ((ItemDelay)d2).expiryTime);
        }
    }
}
