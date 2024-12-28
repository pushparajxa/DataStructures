/*
 ** COPYRIGHT **
 */
package com.ds.slidingWindow;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
//import  org.apache.logging.log4j.Level;


// Based on burstFilter of lOg4j which allows given number of messages in a given duration.

// Window Interval = maxRate/rate.

// Reference:
    // 1. https://logging.apache.org/log4j/2.x/manual/filters.html#BurstFilter
    // 2. https://github.com/apache/logging-log4j2/blob/f7c26cd7710fdace463a370e85d0971107c91d8b/log4j-core/src/main/java/org/apache/logging/log4j/core/filter/BurstFilter.java#L86
public class SlidingWindow_BurstFilter {
    
    
    private static class Level{
        
        private int intLevel;
        /**
         * Compares this level against the level passed as an argument and returns true if this level is the same or is more
         * specific.
         * <p>
         * Concretely, {@link #FATAL} is more specific than {@link #ERROR}, which is more specific than {@link #WARN},
         * etc., until {@link #TRACE}, and finally {@link #ALL}, which is the least specific standard level.
         * The most specific level is {@link #OFF}.
         * </p>
         *
         * Log levels and their int values.
         * OFF --> 0
         * FATAL --> 100
         * ERROR --> 200
         * WARN --> 300
         * INFO --> 400
         * DEBUG --> 500
         * TRACE --> 600
         *
         * @param level The level to test.
         * @return True if this level Level is more specific or the same as the given Level.
         */
        public boolean isMoreSpecificThan(final Level level) {
            return this.intLevel <= level.intLevel;
        }
    }
    
    private static final long NANOS_IN_SECONDS = 1000000000;
    
    private static final int DEFAULT_RATE = 10;
    
    private static final int DEFAULT_RATE_MULTIPLE = 100;
    
    private static final int HASH_SHIFT = 32;
    
    private static final String onMatch = "Match";
    
    private static final String onMismatch = "MisMatch";
    
    /**
     * Level of messages to be filtered. Anything at or below this level will be filtered out if
     * <code>maxBurst</code> has been exceeded. The default is WARN meaning any messages that are
     * higher than warn will be logged regardless of the size of a burst.
     */
    private final Level level;
    
    private final long windowInterval;
    
    private final DelayQueue<LogDelay> history = new DelayQueue<>();
    
    private final Queue<LogDelay> available = new ConcurrentLinkedQueue<>();
    
    static LogDelay createLogDelay(final long expireTime) {
        return new LogDelay(expireTime);
    }
    
    private SlidingWindow_BurstFilter(
        final Level level, final float rate, final long maxBurst) {
        this.level = level;
        this.windowInterval = (long) (NANOS_IN_SECONDS * (maxBurst / rate));
        for (int i = 0; i < maxBurst; ++i) {
            available.add(createLogDelay(0));
        }
    }
    
    
    /**
     * Decide if we're going to log <code>event</code> based on whether the maximum burst of log
     * statements has been exceeded.
     *
     * @param level The log level.
     * @return The onMatch value if the filter passes, onMismatch otherwise.
     */
    private String filter(final Level level) {
        if (this.level.isMoreSpecificThan(level)) {
            LogDelay delay = history.poll();
            while (delay != null) {
                available.add(delay);
                delay = history.poll();
            }
            delay = available.poll();
            if (delay != null) {
                delay.setDelay(windowInterval);
                history.add(delay);
                return onMatch;
            }
            return onMismatch;
        }
        return onMatch;
    }
    
    /**
     * Returns the number of available slots. Used for unit testing.
     *
     * @return The number of available slots.
     */
    public int getAvailable() {
        return available.size();
    }
    
    /**
     * Clear the history. Used for unit testing.
     */
    public void clear() {
        for (final LogDelay delay : history) {
            history.remove(delay);
            available.add(delay);
        }
    }
    
    @Override
    public String toString() {
        return "level=" + level.toString() + ", interval=" + windowInterval + ", max="
            + history.size();
    }
    
    /**
     * Delay object to represent each log event that has occurred within the timespan.
     *
     * Consider this class private, package visibility for testing.
     */
    private static class LogDelay implements Delayed {
        
        LogDelay(final long expireTime) {
            this.expireTime = expireTime;
        }
        
        private long expireTime;
        
        public void setDelay(final long delay) {
            this.expireTime = delay + System.nanoTime();
        }
        
        @Override
        public long getDelay(final TimeUnit timeUnit) {
            return timeUnit.convert(expireTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }
        
        @Override
        public int compareTo(final Delayed delayed) {
            final long diff = this.expireTime - ((LogDelay) delayed).expireTime;
            return Long.signum(diff);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            
            final LogDelay logDelay = (LogDelay) o;
            
            if (expireTime != logDelay.expireTime) {
                return false;
            }
            
            return true;
        }
        
        @Override
        public int hashCode() {
            return (int) (expireTime ^ (expireTime >>> HASH_SHIFT));
        }
    }
    
    
}
