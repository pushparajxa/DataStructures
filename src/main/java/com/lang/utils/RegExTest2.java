
package com.lang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest2 {

  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("c\\\\at");
    Matcher matcher = pattern.matcher("c\\at");
    if(matcher.find()){
      System.out.println(matcher.start());
      System.out.println(matcher.end());
      System.out.println(matcher.group());
    }
  }
}
