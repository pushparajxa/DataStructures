
package com.lang.streams;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

//Difference between handle and handleAsync https://blog.krecan.net/2013/12/25/completablefutures-why-to-use-async-methods/

public class FutureTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {


    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
        () -> {
          try {
            System.out.println("Supplier::"+Thread.currentThread().getName());
            Thread.sleep(10*1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return "From Supplier";});
    completableFuture.thenAccept(x->System.out.println(x));
   /* CompletableFuture<String> stringCompletableFuture =
        completableFuture.handle(
            (x, y) -> {
              System.out.println("handle::"+Thread.currentThread().getName());
              return "hello from handleAsync";
            });*/
    System.out.println("heyDude");
   // String result = completableFuture.get();
   // System.out.println(result);
  }
}
