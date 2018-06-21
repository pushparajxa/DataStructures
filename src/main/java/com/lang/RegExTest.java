
package com.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {
  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("(.*)(<tag>)(.*)(</tag>)(.*)",Pattern.DOTALL);
    Matcher matcher =
        pattern.matcher(
            "<tag1>dsada</tag1>\n<tag4>dsada</tag4><tag>asd--!!##$$$^^&&||</tag>\n<tag2>add</tag2>\n<tag5>add</tag5>");
    if(matcher.find()){
     // System.out.println( matcher.group(0));
      System.out.println( matcher.group(1));
      System.out.println( matcher.group(2));
      System.out.println( matcher.group(3));
      System.out.println( matcher.group(4));
      System.out.println( matcher.group(5));
    }

    Boolean b1 = Boolean.FALSE;
    Boolean b2 = null;
    System.out.println(b1.equals(b2));
  }
}
