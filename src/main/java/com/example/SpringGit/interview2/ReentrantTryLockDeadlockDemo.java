package com.example.SpringGit.interview2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryLockDeadlockDemo {

    //Creates an instance of ReentrantLock with the given fairness policy.
    //Params:
    //fair – true if this lock should use a fair ordering policy
    //•	Threads acquire lock roughly in arrival order
    //•	Slight performance cost
    //ReentrantLock lockA = new ReentrantLock(true);


    //STILL NEED TRANSACTION/PESSIMISTIC LOCK FOR ROW LOCKING FOR DEBIT/CREDIT TXN
    //AS MULTIPLE INSTANCES CAN BE THERE FOR THE APP AND PER JVM MULTIPLE REQUEST COULD HIT DB FOR SAME
    //USERID LEADING TO RACE/CORRUPT DATA..SO THIS LOCK ALONE NOT ENOUGH.
    ReentrantLock lockA = new ReentrantLock();
    ReentrantLock lockB = new ReentrantLock();

    public void transfer() throws InterruptedException {

        if (lockA.tryLock(2, TimeUnit.SECONDS)) {
            try {
                System.out.println("lockA acquired by Thread : " + Thread.currentThread().getName());
                if (lockB.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("lockB acquired by Thread : " + Thread.currentThread().getName());
                    Thread.sleep(3000); // 👈 force timeout in other thread//timeout simulation
                    try {
                        System.out.println("business logic by Thread: " + Thread.currentThread().getName());
                    } finally {
                        System.out.println("finally block releasing lockB by Thread: " + Thread.currentThread().getName());
                        lockB.unlock();
                    }
                }
            } finally {
                System.out.println("finally block releasing lockA by Thread: " + Thread.currentThread().getName());
                lockA.unlock();
                System.out.println("Thread "+Thread.currentThread().getName()+" finished gracefully...");//timeout simulation
                return;//timeout simulation
            }
        }
        System.out.println("Thread "+Thread.currentThread().getName()+" TIMED OUT...");//timeout simulation

    }

    public static void main (String[] args) {

        ReentrantTryLockDeadlockDemo tryLockDeadlockDemo = new ReentrantTryLockDeadlockDemo();
        Runnable r = () -> {
            try {
                tryLockDeadlockDemo.transfer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread ending...");
    }
}
