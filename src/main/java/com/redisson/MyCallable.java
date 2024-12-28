package com.redisson;

import java.util.concurrent.Callable;
//import lombok.extern.slf4j.Slf4j;

//@lombok.extern.slf4j.Slf4j
class MyCallable implements Callable {

  @Override
  public Object call() throws Exception {
   // log.info("From Intellij Redisson client");
    return "hello";
  }
}
