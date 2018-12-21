package com.springAsync.execution;

import com.springAsync.beans.FirstComponent;
import com.springAsync.config.ConfigurationClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//Credits to  http://www.baeldung.com/spring-async

public class AppExecutor {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationClass.class);
        FirstComponent fc = (FirstComponent)context.getBean("firstComponent");
        System.out.println("Current Thread name is="+Thread.currentThread().getName());
       // fc.asyncMethodWithVoidReturnType();
        Future<Integer> integerFuture = fc.asyncMethodWithReturnType();
        while(!integerFuture.isDone()){
            Thread.sleep(100);
            System.out.println("Waiting for future result");
        }
        try {
            System.out.println("Future result is="+integerFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
