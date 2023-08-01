
package com.concurrency.cache;

/*
    Cache line size: On x86 it is 64bytes.
    http://igoro.com/archive/gallery-of-processor-cache-effects/
 */


public class FalseCacheLineSharing {
    
    
    private static int[] s_counter = new int[1024];
    
    private static  void UpdateCounter(int position)
    {
        for (int j = 0; j < 100000000; j++)
        {
            s_counter[position] = s_counter[position] + 3;
        }
    }
    
    
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyFalseLineSharingTest(16));
        Thread t2 = new Thread(new MyFalseLineSharingTest(32));
        Thread t3 = new Thread(new MyFalseLineSharingTest(48));
        Thread t4 = new Thread(new MyFalseLineSharingTest(64));
    
/*
        Since in this case each thread access the same cacheline and updates it, it has to be
        flushed at other threads as well. That is why this take more time than the above one.
        
        In th above case, each thread is accessing different cacheline. So if one thread updates
        a cacheline, other threads do need to update their cacheline of the same range from the
        memory since it has been invalidated(due to update by the first thread).
        
        When one processor modifies a value in its cache, other processors cannot use the old value anymore.
        That memory location will be invalidated in all of the caches. Furthermore, since caches
        operate on the granularity of cache lines and not individual bytes, the entire cache line will be invalidated in all caches!
        
        Each core has its own L1 cache. Cores share L2 Cache.
        
        For L1 cache there are two,
        1 for instruction and other for data. Both are generally 32KB in size.
        
        L2 cache is of 4MB size.
        
        Thread t1 = new Thread(new MyFalseLineSharingTest(0));
        Thread t2 = new Thread(new MyFalseLineSharingTest(1));
        Thread t3 = new Thread(new MyFalseLineSharingTest(2));
        Thread t4 = new Thread(new MyFalseLineSharingTest(3));*/
    
    
    
        long tStart =  System.currentTimeMillis();
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        t1.join();
        t2.join();
        t3.join();
        t4.join();
    
        long tEnd =  System.currentTimeMillis();
    
        System.out.println("Time taken=" + (tEnd - tStart));
    }
    
    
    private static class MyFalseLineSharingTest implements  Runnable
    {
        private int myPosition;
        public MyFalseLineSharingTest(int position)
        {
            myPosition = position;
        }
        
        @Override
        public void run()
        {
            UpdateCounter(myPosition);
        }
    }

}
