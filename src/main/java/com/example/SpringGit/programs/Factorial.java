package com.example.SpringGit.programs;

public class Factorial {

    static int factorial(int n) {

        //base case
        if (n == 0 || n == 1)
            return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {

        int n1 = 5;//120
        int n2 = 1;//0
        int n3 = 8;//40320
        int n4 = 0;//1
        int n5 = 9;//362880
        System.out.println("Factorial of " + n1 + " is " + factorial(n1));
        System.out.println("Factorial of " + n2 + " is " + factorial(n2));
        System.out.println("Factorial of " + n3 + " is " + factorial(n3));
        System.out.println("Factorial of " + n4 + " is " + factorial(n4));
        System.out.println("Factorial of " + n5 + " is " + factorial(n5));
    }
}
