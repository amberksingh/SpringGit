package com.example.SpringGit.mac;

public class Factorial {

    static int factorial(int n) {

        //base case
        if (n == 0 || n == 1)
            return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {

        int n = 5;
        System.out.println("factorial of " + n + " = " + factorial(n));
        n = 10;
        System.out.println("factorial of " + n + " = " + factorial(n));
        n = 7;
        System.out.println("factorial of " + n + " = " + factorial(n));
        n = 1;
        System.out.println("factorial of " + n + " = " + factorial(n));
        n = 0;
        System.out.println("factorial of " + n + " = " + factorial(n));
    }
}
