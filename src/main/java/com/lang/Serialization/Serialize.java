
package com.lang.Serialization;

import java.io.Serializable;

public class Serialize {

  public static void main(String[] args) {
    y y1 = new y();
    System.out.println(y1 instanceof Serializable);
  }

  static class x implements Serializable{

  }

  static class y extends x{

  }



}

