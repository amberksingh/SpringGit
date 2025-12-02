package com.example.SpringGit.pgmRev;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureThenApply {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(2);

        //supplyAsync with thenApply
        Supplier<String> thenApplySupplier = () -> "Hello from thenApply!";

        System.out.println("Main thread start before thenApply: " + Thread.currentThread().getName());

        CompletableFuture<String> thenApply = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Task thenApply start: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Task thenApply end: " + Thread.currentThread().getName());
                    return thenApplySupplier.get();
                },service
        ).thenApply(String::toUpperCase);//Executed in same thread that completed the previous stage (by default)

        //Thread.sleep(8000); //for testing without .join() so that main thread doesn't exit before task ends.
        // Otherwise, use executor service thread.

        System.out.println("Main thread just before join of thenApply: " + Thread.currentThread().getName());
        String res = thenApply.join(); //for blocking, can use thenAccept instead of join here
        System.out.println("Result of thenApply : " + res);
        System.out.println("after join() of thenApply i.e main Thread on hold.");
        service.shutdown();
    }
}
