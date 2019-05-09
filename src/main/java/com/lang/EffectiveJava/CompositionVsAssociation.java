
package com.lang.EffectiveJava;

//https://stackoverflow.com/questions/11881552/implementation-difference-between-aggregation-and-composition-in-java/12431359
public class CompositionVsAssociation {

  public static void main(String[] args) {

    // A person's heart will not exist without person

    Person person = new Person("O+ve");


  }


}

class Person{
  private Heart heart;
  Person(String bloodType){
    this.heart = new Heart(bloodType);
  }

  private class Heart{

    String bloodType;
    public Heart(String bloodType) {
      this.bloodType = bloodType;
    }
  }

}
