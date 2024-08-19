
package com.lang.strings;

import java.util.StringTokenizer;

public class StringTokenizerTest {
    
    public static void main(String[] args) {
        StringTokenizer stringTokenizer = new StringTokenizer("abc efg def", " ");
        while(stringTokenizer.hasMoreTokens()){
            String nextToken = stringTokenizer.nextToken();
            System.out.println(nextToken);
        }
    }
}
