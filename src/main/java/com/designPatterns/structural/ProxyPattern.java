
package com.designPatterns.structural;


/*

Wikipedia: https://en.wikipedia.org/wiki/Proxy_pattern#cite_note-4


What problems can the Proxy design pattern solve?
---------------------------------------------------
* The access to an object should be controlled.
* Additional functionality should be provided when accessing an object. When accessing sensitive
            objects, for example, it should be possible to check that clients have the needed access rights.

Types of usages::
=======================

Remote proxy::
---------------
In distributed object communication, a local object represents a remote object (one that belongs to a different address space).
The local object is a proxy for the remote object, and method invocation on the local object results in remote method invocation on the remote object.
 An example would be an ATM implementation, where the ATM might hold proxy objects for bank information that exists in the remote server.


Virtual proxy:: Used in Lazy loading
------------------------------------
In place of a complex or heavy object, a skeleton representation may be advantageous in some cases.
When an underlying image is huge in size, it may be represented using a virtual proxy object, loading the real object on demand.


Protection proxy::
------------------
A protection proxy might be used to control access to a resource based on access rights.

 */

public class ProxyPattern {
    
    public static void main(String[] args) {
        Client client = new Client();
        
        client.doOperations();
    }

}

class Client {
    
    public void doOperations() {
        
        Subject subject =  new ProxySubject();
        subject.rootOnlyAccessibleMethod();
    }
    
}


interface Subject {
    
    void printHello();
    
    void rootOnlyAccessibleMethod();
}

class RealSubject implements Subject {
    
    
    @Override
    public void printHello() {
        System.out.println("From the real object");
    }
    
    @Override
    public void rootOnlyAccessibleMethod() {
        System.out.println(
            "This method shall only be accessible by the root"
        );
    }
    
}

class ProxySubject implements Subject {
    private RealSubject realSubject;
    
    // Lazy loading of real subject
    @Override
    public void printHello() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.printHello();
    }
    
    //Lazy loading and access control of the real subject.
    @Override
    public void rootOnlyAccessibleMethod() {

        if (System.getenv("user") == "root") {
            if (realSubject == null) {
                realSubject = new RealSubject();
            }
            
            realSubject.rootOnlyAccessibleMethod();
        }
        else {
            System.out.println("Only root users can access or invoke this method");
        }
    }
}