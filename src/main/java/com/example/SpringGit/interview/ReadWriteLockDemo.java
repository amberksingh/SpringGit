package com.example.SpringGit.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Map<String, String> cache = new HashMap<>();

    // Read
    public String get(String key) {
        lock.readLock().lock();
        try {
            String s = cache.get(key);
            System.out.println(s);
            return s;
        } finally {
            lock.readLock().unlock();
        }
    }

    // Write
    public void put(String key, String value) {
        lock.writeLock().lock();
        try {
            System.out.println("added : " + key + ", " + value);
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        Runnable r1 = () -> {
            readWriteLockDemo.put("1", "rahul");
        };

        Runnable r2 = () -> {
            readWriteLockDemo.get("1");
        };

        Runnable r3 = () -> {
            readWriteLockDemo.get("1");
        };

        Runnable r4 = () -> {
            readWriteLockDemo.get("1");
        };

        Thread t1 = new Thread(r1);
        t1.start();
        Thread t2 = new Thread(r2);
        t2.start();
        Thread t3 = new Thread(r3);
        t3.start();
        Thread t4 = new Thread(r4);
        t4.start();
        System.out.println("End main...");

    }
}
