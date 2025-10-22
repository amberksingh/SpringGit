package com.example.SpringGit.programs;

import java.util.Optional;
import java.util.stream.Stream;

public class ArmstrongCheck {

    //Number	Calculation	Armstrong?
    //370	3³ + 7³ + 0³ = 27 + 343 + 0 = 370	✅ Yes
    //371	3³ + 7³ + 1³ = 27 + 343 + 1 = 371	✅ Yes
    //9474	9⁴ + 4⁴ + 7⁴ + 4⁴ = 9474	        ✅ Yes
    //123	1³ + 2³ + 3³ = 36 ≠ 123	            ❌ No

    static boolean isAnagram(int number) {

        //153
        //1^3 + 5^3 + 3^3 = 153
        String num = String.valueOf(number);
        double sum = Stream.of(num.split(""))
                .mapToInt(Integer::parseInt)
                .mapToDouble(n -> Math.pow(n, num.length()))
                .sum();
        System.out.println("sum = " + sum);
        return number == sum;
    }

    static boolean isAnagramGetNumericValueWay(int number) {

        String num = String.valueOf(number);
        Optional<Double> sum = num.chars()
                .map(Character::getNumericValue)
                .mapToObj(n -> Math.pow(n, num.length()))
                .reduce(Double::sum);
        System.out.println("sum = " + sum);
        return (sum.isPresent() && sum.get() == number);
    }

    static boolean isAnagramOldSkool(int number) {

        //153
        int length = String.valueOf(number).length();
        int temp = number;
        int sum = 0;
        while (number > 0) {
            int r = number % 10;//3//5//1
            sum += Math.pow(r, length);//27//152//153
            number /= 10;//15//1
        }
        System.out.println("sum = " + sum);
        return temp == sum;
    }

    public static void main(String[] args) {

        int num = 153;
        int num1 = 371;
        int num2 = 123;

        String result = isAnagram(num) ? "Armstrong number" : "NOT Armstrong";
        System.out.println("isAnagram = " + result);

        String result1 = isAnagramGetNumericValueWay(num) ? "Armstrong number" : "NOT Armstrong";
        System.out.println("isAnagramGetNumericValueWay = " + result1);

        String result2 = isAnagramOldSkool(num) ? "Armstrong number" : "NOT Armstrong";
        System.out.println("isAnagramOldSkool = " + result2);

    }
}
