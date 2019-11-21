package com.lang.reflection;

import java.util.Arrays;

public class ReflectionTest2 {
    public static void main(String[] args) {
        Class<ReflectionTest> reflectionTestClass = ReflectionTest.class;
        Class<?>[] declaredClasses = reflectionTestClass.getDeclaredClasses();

        for(Class delaredClass: declaredClasses){
            System.out.println(delaredClass+" "+delaredClass.isMemberClass()+" "+ delaredClass.isEnum());
            for(Object enumConstant : delaredClass.getEnumConstants()){
                if(enumConstant.toString().equals("val1")){
                    System.out.println("val1 is found");
                }else if(enumConstant.toString().equals("val2")){
                    System.out.println("Val2 is found");
                }
            }
        }



    }
}
