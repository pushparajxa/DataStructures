
package com.reflection;

public class ReflectionTestClass {
    private static final boolean val =true;
    static void printVal(){
        System.out.println(val);
    }
    static boolean aStaticMethod(int [] a){
            return true;
    }
    static boolean aStaticMethod(float [] a) {
        return true;
    }

    public static void main(String[] args) {
        System.out.println(String.class.getName());
        String clas = "java.lang.String";
        try {
            try {
                Object instance = Class.forName(clas).newInstance();
                System.out.println(instance.getClass());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
