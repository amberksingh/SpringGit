package com.example.SpringGit.pgmRev;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableWithException {

    public static void main(String[] args) {

        System.out.println("Main thread start before task thread: " + Thread.currentThread().getName());

        ExecutorService service = Executors.newFixedThreadPool(2);
        Supplier<String> supplier = () -> "CompletableFuture exception demo";

        Function<Object, String> stringValFunction = String::valueOf;
        Function<String, String> uppercaseFunction = String::toUpperCase;
        BiFunction<String, Throwable, String> biFunction = (res, ex) -> {
            if (!Objects.isNull(ex)) {
                System.out.println("Exception occurred .handle() :" + ex.getMessage());
                return "Error Result";
            }
            System.out.println("NO Exception occurred .handle() ");
            return res;
        };

        CompletableFuture<Void> task = CompletableFuture.supplyAsync(() -> {

                    System.out.println("task thread started : " + Thread.currentThread().getName());
                    if (true)
                        throw new RuntimeException("simulation...throwing exception");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Task thread ended : " + Thread.currentThread().getName());
                    return supplier.get();
                }, service)
                .exceptionally(e -> {
                            System.out.println("Ex occurred exceptionally block : " + e.getMessage());
                            return e.getMessage();
                        }
                )
                 .handle(
                         (res, ex) -> {

                             if (!Objects.isNull(ex)) {
                                 System.out.println("res = " + res);
                                 System.out.println("Exception occurred .handle() :" + ex.getMessage());
                                 return "Error Result";
                             }
                             System.out.println("res = " + res);
                             System.out.println("NO Exception occurred .handle() ");
                             return res;
                         }
                 )
                .thenApply(uppercaseFunction)
                .thenAccept(System.out::println);

        System.out.println("Main thread after task stmts: " + Thread.currentThread().getName());
        service.shutdown();
    }
}
