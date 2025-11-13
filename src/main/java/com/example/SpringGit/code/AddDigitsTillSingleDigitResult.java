package com.example.SpringGit.code;

import java.util.Arrays;

public class AddDigitsTillSingleDigitResult {

    //Given a non-negative integer num, repeatedly add all its digits until the
    //result has only one digit.
    private static void addDigitOldSkool(int num) {
        System.out.println("addDigitOldSkool : ");
        int temp = num;
        while (num > 9) {
            int sum = 0;
            while (num > 0) {
                int r = num % 10;
                sum += r;
                num = num / 10;
            }
            num = sum;
        }
        System.out.println("sum for " + temp + " = " + num);
    }

    private static void addDigits(int num) {
        System.out.println("addDigits : ");
        int temp = num;
        int sum = 0;
        while (num > 9) {
            sum = Arrays.stream(String.valueOf(num).split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();
            num = sum;
        }
        System.out.println("sum for " + temp + " = " + num);
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
