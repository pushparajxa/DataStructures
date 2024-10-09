
package com.lang.records;

public class RecordsTest {
     record Pair(int x, int y){}
    
    public static void main(String[] args) {
        System.out.println("Hello World");
        Pair p1 = new Pair(20,30);
        //p1.x = 40; Doesn't allow setting members as they are final.
        System.out.println(p1);
    }
    
}
