
package com.generics;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class ParameterizedTypeTest {

  public static void main(String[] args)
  {
    Collection<String> collection = new ArrayList<>();
    Type[] genericInterfaces = collection.getClass().getGenericInterfaces();

    Test<String> test = new Test<>();
    System.out.println("hello");
  }

}

class Test<E>{

}