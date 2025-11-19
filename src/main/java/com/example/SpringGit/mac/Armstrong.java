package com.example.SpringGit.mac;

import java.util.Map;
import java.util.stream.Stream;

public class Armstrong {

    //153
    static boolean stream(int number) {

        String s = String.valueOf(number);
        double sum = Stream.of(s.split(""))
                .mapToInt(Integer::parseInt)
                .mapToDouble(num -> Math.pow(num, s.length()))
                .sum();
        System.out.println("sum = " + sum);

        double sum1 = Stream.of(s.split(""))
                .mapToInt(Integer::parseInt)
                .mapToObj(num -> Math.pow(num, s.length()))
                .reduce(Double::sum)
                .orElse(0d);
        System.out.println("sum1 = " + sum1);

        return sum == number;
    }

    static boolean oldSkool(int number) {
        String s = String.valueOf(number);
        int temp = number;
        int sum = 0;
        while (number > 0) {
            int r = number % 10;
            sum += Math.pow(r, s.length());
            number = number / 10;
        }
        System.out.println("sum oldSkool: "+sum);
        return temp == sum;
    }

    static boolean isArmstrongCharacterNumeric(int number) {
        String s = String.valueOf(number);
        double sum = s.chars()
                .map(Character::getNumericValue)
                .mapToObj(num -> Math.pow(num, s.length()))
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.println("sum isArmstrongCharacterNumeric: "+sum);
        return number == sum;
    }

    public static void main(String[] args) {

        //Number	Calculation	Armstrong?
        //370	3³ + 7³ + 0³ = 27 + 343 + 0 = 370	✅ Yes
        //371	3³ + 7³ + 1³ = 27 + 343 + 1 = 371	✅ Yes
        //9474	9⁴ + 4⁴ + 7⁴ + 4⁴ = 9474	        ✅ Yes
        //123	1³ + 2³ + 3³ = 36 ≠ 123	            ❌ No

        if (stream(123))
            System.out.println("Stream Armstrong number");
        else
            System.out.println("Stream NOT Armstrong number");

        if (oldSkool(123))
            System.out.println("oldSkool Armstrong number");
        else
            System.out.println("oldSkool NOT Armstrong number");

        if (isArmstrongCharacterNumeric(123))
            System.out.println("isArmstrongCharacterNumeric Armstrong number");
        else
            System.out.println("isArmstrongCharacterNumeric NOT Armstrong number");
    }
}
