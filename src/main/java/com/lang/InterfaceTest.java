
package com.lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterfaceTest {
  public static void main(String[] args) {
    // a a1 = new a();

     List<String> list = new ArrayList<>();
    Set<String> set = new HashSet<>();

    list = (List<String>) set;
  }

  static class a implements in2//,in
   {
    @Override
    public int meth() {
      return 10;
    }
  }

}

interface  in{

  default void hello(){
    System.out.println("From hello");
  }
  void meth();
}

interface  in2{
  default void hello(){
    System.out.println("From hello");
  }
  int meth();
}
