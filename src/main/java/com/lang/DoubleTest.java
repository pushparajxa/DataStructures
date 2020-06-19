
package com.lang;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class DoubleTest {

  public static void main(String[] args) {
    Double d1 = 0d;
    Double d2 = 0.0d;

    Double positiveInfinity = Double.POSITIVE_INFINITY;
    double infinity = Math.abs(positiveInfinity.doubleValue());
    NumberFormat format = new DecimalFormat("0.###############E0");

    HashMap<Double,String> map = new HashMap<>();

    map.put(Double.POSITIVE_INFINITY, "inf");
    map.containsKey(Double.POSITIVE_INFINITY);
    System.out.println("hello");

   /* System.out.println(positiveInfinity);
    System.out.println(Double.NEGATIVE_INFINITY);
    System.out.println(format.format(positiveInfinity));

    System.out.println(Double.NaN);
    double nan = Double.NaN;
    System.out.println(Double.NaN);
    String result = format.format(Double.NaN);
*//*
    System.out.println(Double.valueOf("INFINITY"));
    System.out.println(Double.valueOf("-Infinity"));
    System.out.println(Double.valueOf("NaN"));*/

 /*   Double d3 = Double.valueOf(Double.POSITIVE_INFINITY);
    Double d4 = Double.valueOf(Double.POSITIVE_INFINITY);
    double d5 = Double.POSITIVE_INFINITY;
    System.out.println(d3.equals(d4));
    System.out.println(d3.equals(d5));*/


    Double d6 = Double.valueOf(Double.NaN);
    Double d7 = Double.valueOf(Double.NaN);
    double d8 = Double.NaN;
    System.out.println(d6.equals(d7));
    System.out.println(d6.equals(d8));
    System.out.println(Double.NaN);
  //  System.out.println(Double.);

/*
    System.out.println(Float.valueOf("Infinity"));
    System.out.println(Float.valueOf("-Infinity"));
    System.out.println(Float.valueOf("NaN"));

    System.out.println(Float.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY);
    System.out.println(Float.NaN == Double.NaN);*/
  /*System.out.println(result);
    System.out.println(infinity);
    System.out.println(1.0d/0.0d);
    System.out.println(0.0d<0);*/

  }
}
