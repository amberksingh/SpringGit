package com.example.SpringGit.mac;

import java.util.Arrays;

public class AddDigitsTillSingleDigitResult {

    //Given a non-negative integer num, repeatedly add all its digits until the
    //result has only one digit.

    static void addDigits(int num) {

        //oldskool
        while (num > 9) {
            int sum = 0;
            while (num > 0) {//true//true//true
                int r = num % 10;//6//7//8//9
                //System.out.println("r = " + r);
                sum += r;//6//13//21//30
                //System.out.println("sum = " + sum);
                num /= 10;//987//98//9//3
                //System.out.println("num = " + num);
            }
            num = sum;
        }
        System.out.println("oldskool sum till more than 1 digit : " + num);
    }

    static void addUsingStreams(int num) {

        while (num > 9) {
            String s = String.valueOf(num);
            int sum = Arrays.stream(s.split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();
            num = sum;
        }
        System.out.println("addUsingStreams sum till more than 1 digit : " + num);
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
        addUsingStreams(num);
        num = 1231;//7
        addUsingStreams(num);
        num = 1843;//16//7
        addUsingStreams(num);
        num = 84179;//29//11//2
        addUsingStreams(num);
    }
}
