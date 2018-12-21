package com.designPatterns.creational;

interface Car_Factory {
  Car createCar();
}

interface Car {
  String tellYourModel();
}

//https://en.wikipedia.org/wiki/Abstract_factory_pattern
public class AbstractFactoryPattern_Client {

  private final Car_Factory factory_interface;

  AbstractFactoryPattern_Client(Car_Factory factory_interface) {
    this.factory_interface = factory_interface;
  }

  public static void main(String[] args) {
    Car_Factory factory = new Maruti_Car_Factory();
    AbstractFactoryPattern_Client client = new AbstractFactoryPattern_Client(factory);
    System.out.println(client.giveMeModelName());

    factory = new Benz_Car_Factory();
    client = new AbstractFactoryPattern_Client(factory);
    System.out.println(client.giveMeModelName());
  }

  public String giveMeModelName() {
    return factory_interface.createCar().tellYourModel();
  }
}

class Maruti_Car_Factory implements Car_Factory {

  @Override
  public Car createCar() {
    return new Maruti();
  }
}

class Benz_Car_Factory implements Car_Factory {

  @Override
  public Car createCar() {
    return new Benz();
  }
}

class Maruti implements Car {

  @Override
  public String tellYourModel() {
    return "Maruti";
  }
}

class Benz implements Car {

  @Override
  public String tellYourModel() {
    return "Benz";
  }
}
