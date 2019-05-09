
package com.designPatterns.structural;

public class AdapterPattern {
  public static void main(String[] args) {
    clientRequirement clientRequirement = new Adapter(new Adaptee());
   //Client Calling
    clientRequirement.call();
  }
}


interface clientRequirement{
  void call();
}

class Adaptee {

  void askMe(){
    System.out.println("I am From Adaptee");
  }
}




class Adapter implements clientRequirement{

  private final Adaptee adaptee;

  Adapter(Adaptee adaptee){
    this.adaptee = adaptee;
  }

  @Override
  public void call() {
    adaptee.askMe();
  }
}