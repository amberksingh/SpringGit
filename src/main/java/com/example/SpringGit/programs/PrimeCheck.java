package com.example.SpringGit.programs;

public class PrimeCheck {

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
    static boolean isPrime(int number) {

        boolean flag = true;
        if (number < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {

        int num1 = 2;
        int num2 = 5;
        int num3 = 9;
        int num4 = 37;
        int num5 = 1;
        if (isPrime(num3))
            System.out.println("prime");
        else
            System.out.println("Not Prime");
    }
}
