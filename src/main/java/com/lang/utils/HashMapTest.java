
package com.lang.utils;

import java.util.HashMap;

public class HashMapTest {

  static class Shape{
    private int area;
      Shape(int area){
        this.area = area;
      }

      @Override
    public  int hashCode(){
        return area;
    }

    @Override
    public boolean equals(Object other){
        if(! (other instanceof Shape)){
          return false;
        }
        return this.area == ((Shape)other).area;
    }
  }

  public static void main(String[] args) {
    HashMap<Shape, String> map = new HashMap<>();
    Shape s1 =new Shape(4);
    Shape s2 = new Shape(4);
    map.put(s1,"hello");
    System.out.println(map.get(s2));
  }
}
