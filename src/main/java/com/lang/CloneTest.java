
package com.lang;

public class CloneTest implements Cloneable {
    int x = 10;
    
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest cloneTest = new CloneTest();
        System.out.println(cloneTest.x);
        System.out.println(cloneTest.clone().x);
    }
    
    
    @Override
    public CloneTest clone() throws CloneNotSupportedException {
        Object object = super.clone();
        CloneTest cloned = (CloneTest)object;
        cloned.x = 100;
        return cloned;
        
    }
    
}
