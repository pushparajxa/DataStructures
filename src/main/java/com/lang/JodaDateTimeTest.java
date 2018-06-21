package com.lang;
import org.joda.time.DateTime;

public class JodaDateTimeTest {
    public static void main(String[] args) {
        DateTime time = DateTime.now();
        System.out.println(time);
        System.out.println(time.getDayOfYear() + " "+ time.getHourOfDay());

    }
}
