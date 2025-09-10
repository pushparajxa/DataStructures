/*
 ** COPYRIGHT **
 */
package com.lang.strings;

public class StringLiteralsInternTest {
    
    public static void main(String[] args) {
        
        String str1 = "name";
        String str2 = "name";
        String str3 = "na" + "m"+ "e"; // string literals are interned.
        
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
    }
    
}
