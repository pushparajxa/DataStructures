/*
 * Copyright 2022 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.lang.Date;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

public class ZonedDateTimeSample1_1 {
    
    public static void main(String[] args) {
        System.out.println("Hello");
    
        ZonedDateTime now = ZonedDateTime.now();
    
        System.out.println(now);
        
        ZonedDateTime nextDay = now.plusDays(1).toLocalDate().atStartOfDay(now.getZone());
    
        System.out.println(nextDay);
    }
}
