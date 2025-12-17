package com.example.SpringGit.mac;

//Why i <= sqrt(num) is enough?
//Because:
//
//If num = a * b, then at least one of the factors a or b must be ≤ √num
//
//Let’s see examples:
//
//🔸 Example 1: num = 36
//Factors:
//
//(2, 18)
//
//(3, 12)
//
//(4, 9)
//
//(6, 6) ← square root
//
//After this, pairs like (9, 4) and (18, 2) repeat in reverse.
//
//✅ So if num has a divisor, it will be found before or at √num.
//
//🔸 Example 2: num = 37 (a prime)
//sqrt(37) ≈ 6.08
//
//So we only check divisibility for: i = 2 to 6
//
//Since none of these divide 37, it's prime ✅

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
