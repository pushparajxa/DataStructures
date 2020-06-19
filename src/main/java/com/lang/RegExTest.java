
package com.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {

  public static void main(String[] args) {

    RegExTest regExTest = new RegExTest();
   // String result = regExTest.getCapitalisedUnderScoreString(args[0]);
   // System.out.println(result);

    regExTest.reluctantQualifiers();

  }

  //https://docs.oracle.com/javase/tutorial/essential/regex/quant.html
  public void reluctantQualifiers(){
    Pattern pattern = Pattern.compile(".*+input");
    Matcher matcher = pattern.matcher("inputinput");
    boolean done = false;
    while(!done){
      if(matcher.find()){
        System.out.println(matcher.start()+" ,"+ matcher.end() );
      } else{
        System.out.println("No match found");
        done=true;
      }
    }


  }

  public void ptre(Pattern pattern, String replacement, String replaceThis) {
    pattern.matcher(replaceThis).replaceAll(replacement);
  }

  public String getCapitalisedUnderScoreString(String input) {
    // Pattern pattern = Pattern.compile("\\A([a-z]+)[A-Z]");
    Pattern pattern = Pattern.compile("^([a-z]+)[A-Z]");
    Matcher matcher = pattern.matcher(input);
    if (!matcher.find()) {
      //Handle the case where the input is "ant"
      pattern =  Pattern.compile("([a-z]+)");
      matcher = pattern.matcher(input);
      if(!matcher.find()){
        throw new RuntimeException("input should start with small letter");
      }
    }
    StringBuffer stringBuffer = new StringBuffer(matcher.group(1).toUpperCase());

    pattern = Pattern.compile("([A-Z])([a-z]+)");
    matcher = pattern.matcher(input);

    while (matcher.find()) {
      stringBuffer.append("_");
     /* System.out.println(matcher.group(0) + "--" + matcher.group(1) + "--" + matcher.group(2));
      System.out.println("Start=" + matcher.start(1) + "End=" + matcher.end(1));
      System.out.println("Start=" + matcher.start(2) + "End=" + matcher.end(2));*/
      stringBuffer.append(matcher.group(1)).append(matcher.group(2).toUpperCase());
    }
    return stringBuffer.toString();

  }


  public void doSomething() {

    Pattern pattern = Pattern.compile("(.*)(<tag>)(.*)(</tag>)(.*)", Pattern.DOTALL);
    Matcher matcher =
        pattern.matcher(
            "<tag1>dsada</tag1>\n<tag4>dsada</tag4><tag>asd--!!##$$$^^&&||</tag>\n<tag2>add</tag2>\n<tag5>add</tag5>");
    if (matcher.find()) {
      // System.out.println( matcher.group(0));
      System.out.println(matcher.group(1));
      System.out.println(matcher.group(2));
      System.out.println(matcher.group(3));
      System.out.println(matcher.group(4));
      System.out.println(matcher.group(5));
    }

    Boolean b1 = Boolean.FALSE;
    Boolean b2 = null;
    System.out.println(b1.equals(b2));
  }

}
