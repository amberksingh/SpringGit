package com.example.SpringGit.programs;

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
    static void fib(int n) {

        int a = 0, b = 1;
        System.out.println("\nFib series for " + n + " : ");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int c = a + b;
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {

        //till 10th fibonacci number, print //n=10 means 10 values to be printed till index 9
        fib(5);//0,1,1,2,3
        fib(10);//0,1,1,2,3,5,8,13,21,34
        fib(8);//0,1,1,2,3,5,8,13
    }
}
