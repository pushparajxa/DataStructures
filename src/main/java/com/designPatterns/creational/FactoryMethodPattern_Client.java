
package com.designPatterns.creational;

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