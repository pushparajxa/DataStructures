

package com.lang;

// Call to super must be first line in the constructor.
public class ContructorTest2 extends super2
{
    
    public ContructorTest2()
    {
       // int c2 = 10;
       // Call to super must be first line in the constructor.
        super(12);
    }
    
    
    public static void main(String[] args)
    {
    
    }
    
}

class super2
{
    private int x;
    public super2(int c)
    {
        this.x= c;
    }
}
