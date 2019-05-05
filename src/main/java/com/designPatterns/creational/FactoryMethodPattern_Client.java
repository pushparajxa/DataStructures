
package com.designPatterns.creational;

//Difference between Factory Method pattern and Abstracy Factory Pattern
//https://stackoverflow.com/questions/5739611/what-are-the-differences-between-abstract-factory-and-factory-design-patterns
//http://www.buyya.com/254/Patterns/Factory-2pp.pdf -- Read slides from pages 5 till 8
//Example 1

class A {
  public void doSomething() {
    Foo f = makeFoo();
    f.whatever();
  }

  protected Foo makeFoo() {
    return new RegularFoo();
  }
}

class B extends A {
  protected Foo makeFoo() {
    //subclass is overriding the factory method
    //to return something different
    return new SpecialFoo();
  }
}

abstract class Foo{
  abstract void whatever();
}

class SpecialFoo extends  Foo{

  @Override
  void whatever() {
    System.out.println("This is from SpecialFoo");
  }
}

class RegularFoo extends  Foo{

  @Override
  void whatever() {
    System.out.println("This is from RegularFoo");
  }
}

//Example 2:
//https://en.wikipedia.org/wiki/Factory_method_pattern
public class FactoryMethodPattern_Client {

  Parent p1 = new Child();
  Parent p2 = new Child2();

}


abstract class Parent{
  Product product= createProduct();

   abstract Product createProduct();
}

interface  Product{

}

class Child extends Parent{

  @Override
  Product createProduct() {
    return new Product() {
      @Override
      public String toString(){
        return "From Child1";
      }
      @Override
      public int hashCode() {
        return super.hashCode();
      }
    };
  }
}

class Child2 extends Parent{


  @Override
  Product createProduct() {
    return new Product() {
      @Override
      public String toString(){
        return "From Child2";
      }
      @Override
      public int hashCode() {
        return super.hashCode();
      }
    };
  }
}