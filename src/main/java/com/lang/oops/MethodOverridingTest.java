
package com.lang.oops;

public class MethodOverridingTest {
    
    public static void main(String[] args) {
        
        BaseClass baseClass = new BaseClass();
        System.out.println(baseClass.getNumber("hello"));
    }
    
}

class BaseClass {
    
    public Object getNumber(String name)
    {
        return 10;
    }
    
    public String getNumber(Integer name)
    {
        return "hello";
    }
}

class SubClass extends BaseClass
{
    
    @Override
    public Float getNumber(String name)
    {
        return 20.0f;
    }
}