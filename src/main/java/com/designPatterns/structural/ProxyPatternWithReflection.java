
package com.designPatterns.structural;


/*
https://www.baeldung.com/java-core-structural-patterns#proxy

Spring AOP is based  on this proxy concept : https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-understanding-aop-proxies

 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPatternWithReflection {
    
    public static void main(String[] args) {
        Client client = new Client();
        
        client.doOperations();
    }
    
    
    static class Client {
        
        public void doOperations() {
            
            Subject subject = getProxySubject();
            subject.printHello();
        }
        
    }
    
    
    private interface Subject {
        
        void printHello();
        
        void rootOnlyAccessibleMethod();
    }
    
    private static class RealSubject implements Subject {
        
        
        @Override
        public void printHello() {
            System.out.println("Hello From the real object");
        }
        
        @Override
        public void rootOnlyAccessibleMethod() {
            System.out.println(
                "This method shall only be accessible by the root"
            );
        }
        
    }
    
    private static Subject getProxySubject() {
        return (Subject)
            Proxy.newProxyInstance(
                Subject.class.getClassLoader(),
                new Class[] {Subject.class},
                new ProxyHandler());
        
    }
    
    private static class ProxyHandler implements InvocationHandler {
        
        private RealSubject realSubject;
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(realSubject == null) {
                realSubject = new RealSubject();
            }
            
            for (Method methd: realSubject.getClass().getDeclaredMethods()) {
                if (methd.getName().equals(methd.getName())) {
                    System.out.println("Invoking real subject's method from the proxy"
                        + " object");
                    return methd.invoke(realSubject, args);
                }
            }
            throw new RuntimeException("Given method doesn't exist");
        }
    }
    
}