package com.redisson;

import org.joda.time.DateTimeZone;

public class TimeUtils {
  public static final DateTimeZone IST = DateTimeZone.forOffsetHoursMinutes(5, 30);
  public static final long MILLIS_IN_DAY = 24 * 60 * 60 * 1000l;
}
