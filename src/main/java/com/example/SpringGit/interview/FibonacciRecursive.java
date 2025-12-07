package com.example.SpringGit.interview;

public class FibonacciRecursive {

    static int fibRecursive(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public static void main(String[] args) {

        int n = 5;
        for (int i = 0; i < n; i++) {
            System.out.print(fibRecursive(i) + " ");
        }
    }
}
