package com.example.SpringGit.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolInterview {

    //create ThreadPool of 3 threads and print 1 to 100 utilising all the three threads
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

        //JUST TRYING...ABOVE IS THE CORRECT WAY TO UTILISE ALL 3 THREADS
//        service.submit(() -> {
//            for (int i = 0; i < 100; i++) {
//                System.out.println("loop outside...dummy num = " + i + " : " + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });

        service.shutdown();
    }


}
