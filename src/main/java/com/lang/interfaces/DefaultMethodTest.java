
package com.lang.interfaces;


// Reference: https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html
public class DefaultMethodTest {
    
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(default_method_interface.giveMeAString());
        // System.out.println(default_method_interface.giveMeAnIntegr());
        dummy dummy1 = new dummy();
        System.out.println(((default_method_interface)dummy1).giveMeAnInteger());
        System.out.println(dummy1.giveMeAnInteger());
        System.out.println(dummy1.takeAnInterger());
        
        Rummy rummy = new Rummy();
        System.out.println(rummy.giveMeAnInteger());
        // System.out.println(((default_method_interface)rummy).giveMeAString()); --> Gives  compilation error
        System.out.println(default_method_interface.giveMeAString());
        System.out.println(Rummy.giveMeAString());
    }
}


class dummy implements default_method_interface
{

    int takeAnInterger()
    {
        System.out.println("From dummy class" + default_method_interface.giveMeAString());
        return giveMeAnInteger();
    }
    
   public  int giveMeAnInteger()
    {
        return 30;
    }
}


interface default_method_interface
{
    static String giveMeAString() {
        return "Here you go!!";
    }
    
    default int giveMeAnInteger(){
        return 10;
    }
}

class Rummy implements default_method_interface
{
    static String giveMeAString() {
        return "Here you go Rummy!!";
    }

}