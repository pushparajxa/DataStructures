package com.concurrency.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureTest {
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        
        CompletableFutureTest completableFutureTest = new CompletableFutureTest();
        
       // completableFutureTest.testCallableAndFuture();
        
     //   completableFutureTest.testCompletableFutures();
        
        completableFutureTest.testApplyVsCompose();
    
    
    
    
    }
    
    public void testCompletableFutures() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future  = new CompletableFuture<>();
        
        CompletableFuture<String> composedFuture = future.thenCompose(
            s1 -> CompletableFuture.supplyAsync(() -> s1 + ". New CompletableFuture is started"));
        
        CompletableFuture<String> thenApplyAsync = composedFuture.thenApplyAsync(
            in -> in + ". Then Applying");
        
        CompletableFuture<Void> thenAccept = thenApplyAsync.thenAccept(input -> {
            System.out.println(input + ". Accepted the output and proceeding to the next step");
        });
        
        
        
        
       // composedFuture.complete("Start from here.");
        future.complete("First future is done");
        String s = thenApplyAsync.get();
        //System.out.println(s);
        
    }
    
    public void testApplyVsCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future  = new CompletableFuture<>();
        
        CompletableFuture<String> composedFuture = future.thenCompose(
            s1 -> CompletableFuture.supplyAsync(() -> s1 + ". New CompletableFuture is started"));
        
        CompletableFuture<CompletableFuture<String>> thenApplyAsync = composedFuture.thenApplyAsync(
            
            in -> CompletableFuture.supplyAsync(()->  in +". ApplyAsync")
        );

        
        future.complete("First future is done");
        
        String s = thenApplyAsync.get().get();
        System.out.println(s);
        
    }
    
    public void testCallableAndFuture() throws ExecutionException, InterruptedException {
        Future<String> future = Executors.newFixedThreadPool(1).submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("From Runnable");
            }
        }, "Done");
        
        String val = future.get();
        System.out.println(val);
        
        Future<Void> future2 =
            (Future<Void>) Executors.newFixedThreadPool(1).submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hello from Runnable that returns nothing");
                }
            });
        
        future2.get();
    }
}
