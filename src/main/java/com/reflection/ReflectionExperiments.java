
package com.reflection;

import java.lang.reflect.Method;

public class ReflectionExperiments {
    {
        System.out.println("Hello ReflectionExperiements class is initialized");
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        System.out.println("hello");
        Class cls = Class.forName("com.reflection.ReflectionTestClass");

        Method[] declaredMethods = cls.getDeclaredMethods(); // gets all methods declared in the class

        Method[] methods = cls.getMethods(); //Public methods declared in the class and

        Method method1 = cls.getMethod("aStaticMethod", new Class[]{int[].class});
        Method method2 = cls.getMethod("wait", new Class[]{long.class, int.class});
        System.out.println("End");

        //get Constructors

        //access private fields

        //invoke private methods
    }
}


