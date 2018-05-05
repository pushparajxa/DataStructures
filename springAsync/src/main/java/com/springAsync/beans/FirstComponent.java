package com.springAsync.beans;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class FirstComponent {
    public void testMe(){
        System.out.println("Hello this is from the first Component");
    }

    //Methods with annotation needs to declared as public
    @Async
    public void asyncMethodWithVoidReturnType() {
        System.out.println("Execute method asynchronously. "
                + Thread.currentThread().getName());
    }

    @Async
    public Future<Integer> asyncMethodWithReturnType() {
        System.out.println("Executing method asynchronously in  - "
                + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<>(23);
        } catch (InterruptedException e) {
            //
        }

        return null;
    }

}
