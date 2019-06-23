
package com.test;

import java.util.Arrays;

public class ArCesiumTest {

  int ArCesiumTest(){
    return 10;
  }

  ArCesiumTest(int a){
    System.out.println(a);
  }

  public static void main(String[] args) {

    ArCesiumTest arCesiumTest = new ArCesiumTest(20);
    System.out.println(arCesiumTest.ArCesiumTest());
      int [] ay = {1,2,233};
      int x=20,y=30;
     int a =  x>y? 40: 50;
    System.out.println(Arrays.toString(ay));
  }



}
