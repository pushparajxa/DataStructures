/*
 ** COPYRIGHT **
 */
package com.lang.bigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingModesTest {
    
    
    public static void main(String[] args) {
        BigDecimal number = new BigDecimal("3.165");
        
        System.out.println("Original Scale=" + number.scale());
        
        System.out.println(number.setScale(2, RoundingMode.HALF_UP));
        
        
       
        /*
        // Round to 2 decimal places using HALF_UP
        BigDecimal roundedHalfUp = number.setScale(2, RoundingMode.HALF_UP);
        System.out.println("HALF_UP: " + roundedHalfUp); // Output: 3.15
        
        // Round to 2 decimal places using HALF_DOWN
        BigDecimal roundedHalfDown = new BigDecimal("3.145").setScale(2, RoundingMode.HALF_DOWN);
        System.out.println("HALF_DOWN: " + roundedHalfDown); // Output: 3.14
        
        // Round to 2 decimal places using HALF_EVEN
        BigDecimal roundedHalfEven = new BigDecimal("3.145").setScale(2, RoundingMode.HALF_EVEN);
        System.out.println("HALF_EVEN (for 3.145): " + roundedHalfEven); // Output: 3.14
        BigDecimal anotherNumber = new BigDecimal("3.155");
        BigDecimal roundedHalfEven2 = anotherNumber.setScale(2, RoundingMode.HALF_EVEN);
        System.out.println("HALF_EVEN (for 3.155): " + roundedHalfEven2); // Output: 3.16
        
         */
    }
}