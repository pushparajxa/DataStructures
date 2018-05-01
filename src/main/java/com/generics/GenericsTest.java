package com.generics;

public class GenericsTest {
    public static void main(String[] args) {
        System.out.println("hello");
        Class<Boolean> type = Boolean.TYPE;
        Class<Boolean> booleanClass = Boolean.class;
        System.out.println(type.toString());
        System.out.println(booleanClass.toString());

        boolean val = !(Boolean)(2==3);

    }

}
