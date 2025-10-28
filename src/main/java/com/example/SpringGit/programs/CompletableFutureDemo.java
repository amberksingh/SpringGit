package com.example.SpringGit.programs;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> next = f.thenApply(x -> x * 2);
        System.out.println("val = " + next.join());

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<CompletableFuture<Integer>> nested =
                f1.thenApply(x -> CompletableFuture.supplyAsync(() -> x * 2));
        System.out.println("val = " + nested.join().join());//join called twice due to nested structure

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<String> chain = f2.thenCompose(x ->
                CompletableFuture.supplyAsync(() -> String.valueOf(x)));
        System.out.println("val = " + chain.join());//flattens similar to flatMap in streams

    }
}
