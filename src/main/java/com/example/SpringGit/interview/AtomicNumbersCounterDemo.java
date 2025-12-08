package com.example.SpringGit.interview;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {

    AtomicInteger integer = new AtomicInteger(0);

    void increment() {
        System.out.println("atomic counter : " + integer.incrementAndGet());
    }
}

public class AtomicNumbersCounterDemo {

    public static void main(String[] args) {

        AtomicCounter counter = new AtomicCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                counter.increment();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                counter.increment();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final atomic counter without synchronized : " + counter.integer);

    }
}
