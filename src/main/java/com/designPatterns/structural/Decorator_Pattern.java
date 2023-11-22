
package com.designPatterns.structural;

public class Decorator_Pattern {
    
    
    private static class Component {
        
        public void operate()
        {
            System.out.println("This is from base component class");
        }
        
    }
    
    public static void main(String[] args) {
    
        Component c1 = new Component() {
            
            
            @Override
            public void operate()
            {
                System.out.println("This is from dynamic class");
                super.operate();
            }
            
        };
        
        
        c1.operate();
    
    }
    
}