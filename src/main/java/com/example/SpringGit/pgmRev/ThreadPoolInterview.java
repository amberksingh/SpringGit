package com.example.SpringGit.pgmRev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolInterview {

    //create threadpool of 3 threads and print 1 to 100 utilising all the three threads
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            int num = i;
            service.submit(() -> {
                System.out.println("num = " + num + " : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        service.shutdown();
    }


}
