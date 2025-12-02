package com.example.SpringGit.pgmRev;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureThenApplyAccept {

    public static void main(String[] args) {

        //These are non-daemon threads, so:
        //
        //🔸 Even if the main thread finishes, the JVM waits for the task threads to finish properly.
        //
        //So your code will still print the final output even without calling .join() — great insight!
        ExecutorService service = Executors.newFixedThreadPool(2);
        //supplyAsync with thenApply with thenAccept
        Supplier<String> thenApplyAcceptSupplier = () -> "Hello from thenApplyAcceptSupplier!";

        System.out.println("Main thread start before thenApplyAccept: " + Thread.currentThread().getName());

        CompletableFuture<Void> thenApplyAccept = CompletableFuture.supplyAsync(
                        () -> {
                            System.out.println("Task thenApplyAccept start: " + Thread.currentThread().getName());
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Task thenApplyAccept end: " + Thread.currentThread().getName());
                            return thenApplyAcceptSupplier.get();
                        }, service   //executor non-daemon thread
                ).thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
        System.out.println("Main thread just before join of thenApplyAccept: " + Thread.currentThread().getName());

        //Void join = thenApplyAccept.join();//for BLOCKING else use accept with executor non-daemon threads

        // if using ExecutorService service = Executors.newFixedThreadPool(2); as parameter
        // in supplyAsync, then those are not daemon threads and even if main thread finishes,
        // task is completed without using join(). Otherwise, if join is not used, then main thread exits before
        // Daemon thread and program abruptly ends without task being completed
        System.out.println("after join() of thenApplyAccept i.e main Thread on hold.");
        service.shutdown();
    }
}
