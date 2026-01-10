package com.example.SpringGit.interview2;

import java.util.Arrays;

public class AddDigitsTillSingleDigitResult {

    //Given a non-negative integer num, repeatedly add all its digits until the
    //result has only one digit.
    static void addDigitOldSkool(int number) {

        int sum = 0;
        while (number > 9) {
            sum = 0;
            while (number > 0) {
                int r = number % 10;
                sum += r;
                number /= 10;
            }
            number = sum;
        }
        System.out.println("sum old skool way = " + sum);
    }

    static void addDigits(int number) {

        while (number > 9) {
            String num = String.valueOf(number);
            number = Arrays.stream(num.split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();
        }
        System.out.println("sum using string = " + number);
    }

    public static void main(String[] args) {

        int num = 9876;//3
        addDigits(num);
        num = 1231;//7
        addDigits(num);
        num = 1843;//16//7
        addDigits(num);
        num = 84179;//29//11//2
        addDigits(num);

        //
        num = 9876;//3
        addDigitOldSkool(num);
        num = 1231;//7
        addDigitOldSkool(num);
        num = 1843;//16//7
        addDigitOldSkool(num);
        num = 84179;//29//11//2
        addDigitOldSkool(num);
    }

}
