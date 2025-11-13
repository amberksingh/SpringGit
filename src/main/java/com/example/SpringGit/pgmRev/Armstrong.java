package com.example.SpringGit.pgmRev;

import java.util.Arrays;
import java.util.Optional;

public class Armstrong {

    static boolean isArmstrong(int number) {

        //153 = 1^3 + 5^3 + 3^3 = 153
        String s = String.valueOf(number);
        double sum = Arrays.stream(s.split(""))
                .mapToInt(Integer::parseInt)
                .mapToDouble(x -> Math.pow(x, s.length()))
                .sum();
        System.out.println("sum streams = " + sum);
        return sum == number;

    }

    static boolean isArmstrongOldSkool(int number) {

        int temp = number;
        String s = String.valueOf(number);
        int sum = 0;
        while (number > 0) {
            int r = number % 10;
            sum += Math.pow(r, s.length());
            number /= 10;
        }
        System.out.println("sum oldskool = " + sum);
        return sum == temp;
    }

    static boolean isArmstrongCharacterNumeric(int number) {

        String s = String.valueOf(number);
        Optional<Double> sum = s.chars()
                .map(Character::getNumericValue)
                .mapToObj(x -> Math.pow(x, s.length()))
                .reduce(Double::sum);
        System.out.println("sum CharacterNumeric = " + sum);
        return (sum.isPresent() && sum.get() == number);
    }

    public static void main(String[] args) {

        //Number	Calculation	Armstrong?
        //370	3³ + 7³ + 0³ = 27 + 343 + 0 = 370	✅ Yes
        //371	3³ + 7³ + 1³ = 27 + 343 + 1 = 371	✅ Yes
        //9474	9⁴ + 4⁴ + 7⁴ + 4⁴ = 9474	        ✅ Yes
        //123	1³ + 2³ + 3³ = 36 ≠ 123	            ❌ No


        int num1 = 153;
        boolean result = isArmstrong(num1);
        if (result)
            System.out.println("Armstrong number");
        else
            System.out.println("NOT Armstrong");

        int num2 = 153;
        boolean result1 = isArmstrongOldSkool(num2);
        if (result1)
            System.out.println("Armstrong number oldskool");
        else
            System.out.println("NOT Armstrong oldskool");

        int num3 = 153;
        boolean result2 = isArmstrongCharacterNumeric(num3);
        if (result2)
            System.out.println("Armstrong number CharacterNumeric");
        else
            System.out.println("NOT Armstrong CharacterNumeric");

    }
}
