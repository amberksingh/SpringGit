package com.example.SpringGit.code;

import java.util.Arrays;

public class Armstrong {

    private static boolean isArmstrong(int num1) {

        String s = String.valueOf(num1);
        double sum = Arrays.stream(s.split(""))
                .mapToInt(Integer::parseInt)
                .mapToDouble(x -> Math.pow(x, s.length()))
                .sum();
        System.out.println("isArmstrong sum = " + sum);
        return num1 == sum;

    }

    private static boolean isArmstrongOldSkool(int num2) {
        String s = String.valueOf(num2);
        int temp = num2;
        int sum = 0;
        while (num2 > 0) {
            int r = num2 % 10;
            sum += Math.pow(r, s.length());
            num2 /= 10;
        }
        System.out.println("isArmstrongOldSkool sum = " + sum);
        return temp == sum;
    }

    private static boolean isArmstrongCharacterNumeric(int num3) {
        String s = String.valueOf(num3);
        double sum = s.chars()
                .map(Character::getNumericValue)
                .mapToObj(x -> Math.pow(x, s.length()))
                .reduce(Double::sum)
                .orElse(0D)
                .doubleValue();
        System.out.println("isArmstrongCharacterNumeric sum = " + sum);
        return sum == num3;
    }

    public static void main(String[] args) {

        //Number	Calculation	Armstrong?
        //370	3³ + 7³ + 0³ = 27 + 343 + 0 = 370	✅ Yes
        //371	3³ + 7³ + 1³ = 27 + 343 + 1 = 371	✅ Yes
        //9474	9⁴ + 4⁴ + 7⁴ + 4⁴ = 9474	        ✅ Yes
        //123	1³ + 2³ + 3³ = 36 ≠ 123	            ❌ No


        int num1 = 123;
        boolean result = isArmstrong(num1);
        if (result)
            System.out.println("Armstrong number");
        else
            System.out.println("NOT Armstrong");

        int num2 = 123;
        boolean result1 = isArmstrongOldSkool(num2);
        if (result1)
            System.out.println("Armstrong number oldskool");
        else
            System.out.println("NOT Armstrong oldskool");

        int num3 = 123;
        boolean result2 = isArmstrongCharacterNumeric(num3);
        if (result2)
            System.out.println("Armstrong number CharacterNumeric");
        else
            System.out.println("NOT Armstrong CharacterNumeric");

    }

}
