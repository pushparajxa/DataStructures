
package com.generics;

import java.util.ArrayList;
import java.util.List;

public class Generics_Test1
{
    
    public static void main(String[] args) {
      /*  MyNode myNode = new MyNode(36);
        Node node = myNode;
        node.setData("hello");*/
      
      
      B b = new B();
      A a = b;
      a.setData("hello");
      ArrayList<Number> l = new ArrayList<>();
      
      ArrayList<? extends  Number> l2 = l;
      l.add(23);
      System.out.println(l2.get(0));
    }
}


class Node<T> {
    
    public T data;
    
    public Node(T data) { this.data = data; }
    
    public void setData(T data) {
        this.data = data;
    }
}

class MyNode extends Node<Integer> {
    public MyNode(Integer data) { super(data); }
    
    public void setData(Integer data) {
        super.setData(data);
    }
}

class A
{
    public void setData(Object obj)
    {
        System.out.println("From class A and method setData");
    }
}

class B extends  A
{
    public void setData(Integer number)
    {
        System.out.println("This is from sub class");
    }
}