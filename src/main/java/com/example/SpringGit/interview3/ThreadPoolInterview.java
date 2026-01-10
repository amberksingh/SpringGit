package com.example.SpringGit.interview3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolInterview {

    //create ThreadPool of 3 threads and print 1 to 100 utilising all the three threads
    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            int temp = i;
            service.submit(() -> {
                System.out.println("submit i = " + temp + "-" + Thread.currentThread().getName());
            });
            Thread.sleep(100);
        }

        for (int i = 0; i < 100; i++) {
            int temp = i;
            CompletableFuture.runAsync(() -> {
                System.out.println("CompletableFuture i = " + temp + "-" + Thread.currentThread().getName());
            }, service);
            Thread.sleep(100);
        }
        service.shutdown();
    }
}
