
package com.lang.Date;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Java8DateTimeTest {

  public static void main(String[] args) {

    Clock clock = Clock.system(ZoneId.of("Asia/Kolkata"));
    Instant instant = clock.instant();
    System.out.println(clock.millis()+"  "+System.currentTimeMillis());
  }


}
