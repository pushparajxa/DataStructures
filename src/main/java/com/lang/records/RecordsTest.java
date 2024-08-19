
package com.lang.records;

public class RecordsTest {
    record Pair(int x, int y){}
    
    public static void main(String[] args) {
        System.out.println("Hello World");
        Pair p1 = new Pair(20,30);
        System.out.println(p1);
    }
    
}
