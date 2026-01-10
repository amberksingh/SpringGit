package com.example.SpringGit.interview2;

public class DeadlockDemo {

    public static void main(String[] args) {

        Object lockA = new Object();
        Object lockB = new Object();

        //
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("thread1 acquired lockA..");
                try {
                    Thread.sleep(100); // DOES NOT release lockA
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 trying to acquire lockB..");
                synchronized (lockB) {
                    System.out.println("thread1 acquired lockB..");//not reachable due to deadlock
                }
            }
        });

        //
        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("thread2 acquired lockB..");

                try {
                    Thread.sleep(100); // DOES NOT release lockB
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 trying to acquire lockA..");
                synchronized (lockA) {
                    System.out.println("thread2 acquired lockA..");//not reachable due to deadlock
                }
            }
        });

        t1.start();
        t2.start();

    }
}
