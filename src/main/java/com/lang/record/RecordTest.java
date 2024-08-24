
package com.lang.record;

public class RecordTest {
    
    record Pair2(int x, int y){}
    
    public static void main(String[] args) {
        System.out.println("Hello World");
        Pair2 p1 = new Pair2(20,30);
        System.out.println(p1);
    }
    
}
