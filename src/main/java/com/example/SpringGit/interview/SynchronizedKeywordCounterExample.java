package com.example.SpringGit.interview;

class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
        System.out.println("count = " + count + "-" + Thread.currentThread().getName());
    }
    //BELOW IS A BETTER APPROACH
    //AtomicInteger integer = new AtomicInteger(0);
    //
    //    void increment() {
    //        System.out.println("atomic counter : " + integer.incrementAndGet());
    //    }
}


public class SynchronizedKeywordCounterExample {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        Runnable runnable1 = () -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable runnable2 = () -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Final counter : " + counter.count);

    }
}
