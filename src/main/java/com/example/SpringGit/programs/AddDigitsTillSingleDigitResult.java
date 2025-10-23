package com.example.SpringGit.programs;

import java.util.stream.Stream;

public class AddDigitsTillSingleDigitResult {

    //Given a non-negative integer num, repeatedly add all its digits until the
    //result has only one digit.

    static void addDigits(int number) {

        int temp = number;
        while (number >= 10) {
            String s = String.valueOf(number);
            String[] split = s.split("");
            number = Stream.of(split)
                    .mapToInt(Integer::parseInt)
                    .sum();
        }
        System.out.println("Streams === Sum of digits of " + temp + " till result has only one digit : " + number);
    }

    static void addDigitOldSkool(int number) {

        int temp = number;
        while (number >= 10) {
            int sum = 0;
            while (number > 0) {
                int r = number % 10;
                sum += r;
                number /= 10;
            }
            number = sum;
        }
        System.out.println("Old Skool === Sum of digits of " + temp + " till result has only one digit : " + number);
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
