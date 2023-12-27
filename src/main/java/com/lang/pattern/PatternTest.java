
package com.lang.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Rule.RPattern;

public class PatternTest {
    
    public static void main(String[] args) {
    
       String patternText = "\".*+\"";
       String textToMatch = "\"This is a string\" and this is not part of the string \"This is another string\"";
       
       patternText = "<[^>]+>";
       textToMatch = "<b>bold text</b> and <i>italic text</i>";
       
       general(patternText, textToMatch);
    
    }
    
    static void general(String patternText, String textToMatch) {
        Pattern pattern = Pattern.compile(patternText);
        Matcher matcher = pattern.matcher(textToMatch);
        while(matcher.find())
        {
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            System.out.println("start=" + startIndex +" and endIndex =" + endIndex);
            System.out.println(matcher.group());
        
        }
    }
}
