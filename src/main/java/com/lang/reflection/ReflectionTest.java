
package com.lang.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

  public static void main(String[] args) {
    ReflectionTest rt = new ReflectionTest();
    rt.doReflectionMagic();


  }

  public enum myEnum{
    val1,val2
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
