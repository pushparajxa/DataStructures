
package com.lang;

public class NestedClass2 {
    
    public static void main(String[] args) {
        int a = 30;
        
        {
            a = 40;
            System.out.println(a);
        }
        System.out.println(a);
    }

}
