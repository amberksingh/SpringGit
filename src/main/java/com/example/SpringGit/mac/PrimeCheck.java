package com.example.SpringGit.mac;

public class PrimeCheck {

    static boolean isPrime(int number) {
        boolean flag = true;
        if (number < 2) {
            flag = false;
            return flag;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        int num = 2;
        int num1 = 13;
        int num2 = 5;
        int num3 = 71;
        int num4 = 112;
        int num5 = 1;
        boolean prime = isPrime(num5);
        if (prime)
            System.out.println("PRIME number");
        else
            System.out.println("NOT PRIME number");
//        num = 5;
//        prime;
//        num = 13;
//        prime;
//        num = 224;
//        prime;
//        num = 25;
//        prime;

    }
}
