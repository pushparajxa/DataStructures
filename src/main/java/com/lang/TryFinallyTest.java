
package com.lang;

public class TryFinallyTest {
    
    
    public static void main(String[] args) {
        TryFinallyTest test = new TryFinallyTest();
        System.out.println(test.getString());
    }
    
    public String getString()
    {
        try {
            return "hello";
        }
        finally {
            System.out.println("Finally is called");
        }
    }
}
