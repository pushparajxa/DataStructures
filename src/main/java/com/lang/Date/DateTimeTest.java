
package com.lang.Date;

import java.sql.Time;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

public class DateTimeTest {
  public static void main(String[] args) {

    long l1 = 1527235200000l;
    long l2 = 1527181465793l;

    /*DateTime d1 = new DateTime(l1,DateTimeZone.UTC);
    System.out.println(d1);

    DateTime d2 = new DateTime(l2,DateTimeZone.UTC);
    System.out.println(d2);

    LocalTime localTime = LocalTime.now();
    System.out.println(DateTime.now());
    System.out.println(localTime);
    System.out.println(localTime.getHourOfDay());
    System.out.println(localTime.getMinuteOfHour());
    System.out.println(localTime.getSecondOfMinute());
    System.out.println(localTime.getMillisOfSecond());

    System.out.println((double)localTime.getMillisOfDay() / (1000*60*60));

    DateTime d3 = new DateTime(1527222916908l);
    int deliveryCutOffTime = 46860000;
    DateTime CPD = new DateTime(1527337800000l);

    //CN id = 1600511
    System.out.println("Arrival Time "+d3);
    System.out.println("DeliveryCutOffTime "+LocalTime.fromMillisOfDay(46860000l));
    System.out.println("CPD="+CPD);
    System.out.println("CPD_DeliveryCutoff="+CPD.withMillisOfDay(deliveryCutOffTime));

    //CN with SpillOver or Dispatch Cooling = 1594279

    //CN with status DELIVERED 1526891203955
    DateTime pod = new DateTime(1526891203955l);
    DateTime cpD = new DateTime(1526992200000l);

    System.out.println(pod);
    System.out.println(cpD);

    //CN Delivered but CPB Reason not found
    DateTime pod2 = new DateTime(1527237311837l,DateTimeZone.UTC);
    System.out.println( new DateTime(1527237311837l));
    DateTime cpd2 = new DateTime(1527078600000l,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Kolkata")));

    LocalTime l4 = new LocalTime(32460000,DateTimeZone.UTC);
    System.out.println(l4);

    LocalTime localTime2AM = LocalTime.fromMillisOfDay(2 * 60 * 60 * 1000);
    System.out.println(localTime2AM);

    DateTime arrivalTime = new DateTime(1527243900000l,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Kolkata")));

    System.out.println(arrivalTime);*/
    //1527072875893
    //32460000
    //1527251400000

   /* DateTime dt = new DateTime(1527072875893l,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Kolkata")));
    System.out.println(dt);

    System.out.println(dt.withMillisOfDay(32460000));

    System.out.println(new DateTime(1527178889745l,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Kolkata"))));

    System.out.println("LhDelay 1605573"); //1527240900000
    System.out.println("LH InTime "+ new DateTime(1527172566628l,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Kolkata"))));
    System.out.println("Arrival  "+new DateTime(1527246009494l,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Kolkata"))));
    //2018-05-25 18:00:00.0000
    System.out.println("CPD  "+new DateTime(1527251400000l,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Kolkata"))));
    System.out.println("CPD_Mod  "+new DateTime(1527251400000l,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Kolkata"))).withMillisOfDay(30600000));

    //1528120657841

    DateTime dt5 = new DateTime(1528120657841l);
    System.out.println(dt5);

    LocalTime lt = new LocalTime(46860000l);
    System.out.println(lt);
    System.out.println(lt.getMillisOfDay());
    System.out.println(System.getProperty("user.timezone"));
    System.out.println(TimeZone.getDefault());
    //System.out.println(lt.getChronology());
    System.out.println(new DateTime(lt.getMillisOfDay()));
    System.out.println(dt5.withMillisOfDay(lt.getMillisOfDay()));

    System.out.println(new DateTime(1527433260000l));*/
/*
   DateTime dt = new DateTime(1530791110737l,DateTimeZone.forTimeZone(TimeZone.getTimeZone
       ("Asia/Kolkata")));

    System.out.println(dt);

    DateTime dt2 = new DateTime(1530729000000l,DateTimeZone.forTimeZone(TimeZone.getTimeZone
        ("Asia/Kolkata")));

    System.out.println("NextDay="+new DateTime(1530729000000l+ 86400000,DateTimeZone.forTimeZone
        (TimeZone.getTimeZone
        ("Asia/Kolkata"))));

    System.out.println(dt2);*/

    System.out.println(new DateTime(1530999000000l,DateTimeZone.forTimeZone
        (TimeZone.getTimeZone("Asia/Kolkata"))));
  }
}
//25/05/2018 12:09:13