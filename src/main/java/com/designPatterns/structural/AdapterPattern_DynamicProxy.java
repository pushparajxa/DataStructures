
package com.designPatterns.structural;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AdapterPattern_DynamicProxy {
  public static void main(String[] args) {
    Adaptee adaptee = new Adaptee();
    clientRequirement adapter = getAdapter(adaptee);
    adapter.call();
  }

  static clientRequirement getAdapter(Adaptee adaptee) {

    return (clientRequirement)
        Proxy.newProxyInstance(
            clientRequirement.class.getClassLoader(),
            new Class[] {clientRequirement.class},
            new InvocationHandler() {
              @Override
              public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("call")) {
                  adaptee.askMe();
                  return null;
                }
                return null;
              }
            });
  }

  interface clientRequirement {
    void call();
  }

  static class Adaptee {

    void askMe() {
      System.out.println("I am From Adaptee");
    }
  }

  static class Adapter implements clientRequirement {

    private final Adaptee adaptee;

    Adapter(Adaptee adaptee) {
      this.adaptee = adaptee;
    }

    @Override
    public void call() {
      adaptee.askMe();
    }
  }
}