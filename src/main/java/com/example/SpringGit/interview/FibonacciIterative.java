package com.example.SpringGit.interview;

public class FibonacciIterative {

    //In Fibonacci, the series usually starts from index 0 like this:
    //Index:       0  1  2  3  4  5 ...
    //Value:       0  1  1  2  3  5 ...
    //So:
    //
    //fibonacci(0) → 0
    //
    //fibonacci(1) → 1
    //
    //fibonacci(2) → 1
    //
    //fibonacci(3) → 2
    static void fibIterative(int n) {

        int a = 0, b = 1;
        System.out.println("\nFib series till " + n);
        for (int i = 0; i < n; i++) {
            System.out.print(a+" ");
            int c = a + b;
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {
        int n = 1;//n is no.of elements not index here
        fibIterative(n);
        n= 5;
        fibIterative(n);
        n= 3;
        fibIterative(n);
        n= 10;
        fibIterative(n);
    }
}
