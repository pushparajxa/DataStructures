
package com.lang.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationTestBasic
{
    @TestAnnotation
    public void testMethod()
    {
        System.out.println("This is from test method");
    }
    
 
    
    public static void main(String[] args) throws ClassNotFoundException {
        //Class<?> clas = Class.forName("AnnotationTestBasic");
        
    /*    for(Method m : clas.getDeclaredMethods()) {
        
        }*/
    
        List<String> l1 = new ArrayList<>();
        ArrayList<String> l2 = new ArrayList<>();
        
        System.out.println(l1.getClass().isAssignableFrom(l2.getClass()));
    }
}

