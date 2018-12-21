package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FinalPivateStaticMember {

    public static void main(String[] args) {
        System.out.println("Initial value == "+Test.val);
        try {
           // Class cls = Class.forName("com.reflection.FinalPivateStaticMember$Test");
            Class cls = Class.forName("com.reflection.ReflectionTestClass");
            try {
                Field file_systems_loaded = cls.getDeclaredField("val");
                file_systems_loaded.setAccessible(true);

                Field modifiers = Field.class.getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                try {
                    System.out.println("--"+file_systems_loaded.getModifiers());
                    modifiers.setInt(file_systems_loaded,file_systems_loaded.getModifiers() & ~Modifier.FINAL);
                    System.out.println("--"+file_systems_loaded.getModifiers());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    file_systems_loaded.setBoolean(null,false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.print("Final value == "+Test.val);

    }

    static class Test{

        private  final static    boolean val = true;
    }

}

