package com.example.SpringGit.pgmRev;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureValReturnDemo {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);

        //supplyAsync
        Supplier<String> supplier = () -> "Hello from supplyAsync!";

        System.out.println("Main thread start before supplyAsync: " + Thread.currentThread().getName());

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Task supplyAsync start: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Task supplyAsync end: " + Thread.currentThread().getName());
                    return supplier.get();
                }/*, service*/
        );
        System.out.println("Main thread just before join of supplyAsync: " + Thread.currentThread().getName());
        String result = supplyAsync.join();
        System.out.println("Result of supplyAsync : " + result);
        System.out.println("after join() of supplyAsync i.e main Thread on hold.");
        service.shutdown();
    }
}
