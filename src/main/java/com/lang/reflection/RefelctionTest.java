
package com.lang.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefelctionTest {

  public static void main(String[] args) {
    RefelctionTest rt = new RefelctionTest();
    rt.doReflectionMagic();

  }

   void doReflectionMagic() {
    try {
      Method method = getClass().getMethod("getify", new Class[]{Object.class});
      try {
        method.invoke(this,new Integer("123"));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    } catch (NoSuchMethodException e) {
      System.out.println("Method is not found");
    }
  }

  public void getify(Object obj){
    System.out.println("hello object=="+obj);
  }

}
