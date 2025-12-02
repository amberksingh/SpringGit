package com.example.SpringGit.pgmRev;

import java.util.concurrent.*;

public class ExecutorWithCallable {

    //Without get()
    //The ExecutorService’s worker threads are non-daemon by default.
    //That means the JVM will not terminate until all worker threads finish executing.
    //✅ So your Callable will still complete fully.
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(2);//non-daemon threads
        System.out.println("ExecutorService demo.."+ Thread.currentThread().getName());

        //task
        Callable<String> callable = () -> {
            System.out.println("Task starting.." + Thread.currentThread().getName());
            Thread.sleep(8000);
            System.out.println("Task ending.." + Thread.currentThread().getName());
            return "task result";
        };

        //submit the task
        //the second thread (from the thread pool) starts executing the Callable task as soon as you
        // call submit() — if a thread is available.
        Future<String> future = service.submit(callable);

        try {
            System.out.println("Just before future.get() " + Thread.currentThread().getName());
            String result = future.get();//"task result"
            System.out.println("Result of task : " + result + " : " + Thread.currentThread().getName());
            System.out.println("after get() of future i.e main Thread on hold." + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        //Shutdown the executor
        service.shutdown();

    }
}
