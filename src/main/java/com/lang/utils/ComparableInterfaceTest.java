/*
 ** COPYRIGHT **
 */
package com.lang.utils;

import java.math.BigDecimal;

public class ComparableInterfaceTest {
    
    public static void main(String[] args) {
        // Natural Ordering inconsistent with equals.
        
        BigDecimal b1 = new BigDecimal("4.00");
        BigDecimal b2 = new BigDecimal("4.0");
        
        System.out.println(b1.equals(b2));
        
        System.out.println(b1.compareTo(b2));
    }
}
