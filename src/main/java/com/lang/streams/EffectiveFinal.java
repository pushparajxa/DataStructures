
package com.lang.streams;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class EffectiveFinal {
  public static void main(String[] args) {
    Optional<String> stringOptional = Optional.of("hello");

    Optional<Boolean> aBoolean = stringOptional.map(x -> {
      if(x.length()==0){
        return  Boolean.TRUE;
      }else{
        return Boolean.FALSE;
      }
    }).map(xy-> {
      if(xy){
        return Boolean.TRUE;
      }else{
        return Boolean.FALSE;
      }
    });
  }

  static class xyz{
    int val;

    public void setVal(int s){
      val=s;
    }
  }
}
