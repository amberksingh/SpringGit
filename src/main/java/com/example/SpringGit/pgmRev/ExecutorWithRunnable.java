package com.example.SpringGit.pgmRev;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorWithRunnable {

    //Without get()
    //The ExecutorService’s worker threads are non-daemon by default.
    //That means the JVM will not terminate until all worker threads finish executing.
    //✅ So your Runnable will still complete fully.
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);//non-daemon threads
        System.out.println("Main thread..." + Thread.currentThread().getName());

        //similar to Callable<Void> scenario
        Runnable runnable = () -> {
            System.out.println("Runnable task starting executed by: " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runnable task ending executed by: " + Thread.currentThread().getName());
            //return;
        };

        Future<?> submit = service.submit(runnable);
        try {
            System.out.println("Main thread...just before get() "+ Thread.currentThread().getName());
            Object o = submit.get();//null as Runnable ,blocks below main thread stuff similar to Callable<Void> scenario
            System.out.println("o = " + o);//blocked because of submit.get()
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main thread...ending: " + Thread.currentThread().getName());
        service.shutdown();
    }
}
