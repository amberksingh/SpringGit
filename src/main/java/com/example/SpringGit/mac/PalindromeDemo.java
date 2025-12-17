package com.example.SpringGit.mac;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PalindromeDemo {

    static boolean stringPalindrome(String s) {

        String reduce = s.chars()
                .mapToObj(Character::toString)
                .reduce((c1, c2) -> c2 + c1)
                .orElse("null");
        System.out.println("reduce = " + reduce);

        Optional<String> optionalString = Stream.of(s.split(""))
                .reduce((s1, s2) -> s2 + s1);
        if (optionalString.isPresent()) {
            String reversed = optionalString.get();
            System.out.println("reversed = " + reversed);
            if (reversed.equals(s))
                System.out.println("palindrome using split reduce");
            else
                System.out.println("NOT palindrome using split reduce");
        }

        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            rev = rev.concat(Character.toString(s.charAt(i)));
        }
        System.out.println("rev concat :" + rev);
        return s.equals(rev);
    }

    static boolean numberPalindrome(int number) {

        String s = String.valueOf(number);

        int sum1 = Stream.of(s)
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(String::valueOf)
                .mapToInt(Integer::parseInt)
                .sum();
//                .mapToInt(Integer::parseInt)
//                .sum();
        System.out.println("sum1 = " + sum1);
        if (sum1 == number)
            System.out.println("palindrome using intstream");

//        int sum2 = s.chars()
//                .boxed()
//                .mapToInt(Character::getNumericValue)
//                .sum();
//        System.out.println("sum2 = " + sum2);
//        if (sum2 == number)
//            System.out.println("palindrome using Character::getNumericValue");


        int sum = 0;
        int temp = number;
        while (number > 0) {
            int r = number % 10;
            sum = (sum * 10) + r;
            number = number / 10;
        }
        System.out.println("sum = " + sum);
        return sum == temp;
    }

    static boolean stringPalindromeMethod(String s) {

        StringBuffer stringBuffer = new StringBuffer(s);
        String rev = stringBuffer.reverse().toString();
        System.out.println("rev buffer :" + rev);
        return s.equals(rev);
    }

    public static void main(String[] args) {

//        String stringResult = stringPalindrome("vikram") ? "Palindrome" : "NOT Palindrome";
//        System.out.println("stringResult = " + stringResult);
//        System.out.println();

        String numberResult = numberPalindrome(5445) ? "Palindrome" : "NOT Palindrome";
        System.out.println("numberResult = " + numberResult);
        System.out.println();

//        String stringReduce = stringPalindromeMethod("vikram") ? "Palindrome" : "NOT Palindrome";
//        System.out.println("stringReduce = " + stringReduce);
//        System.out.println();

    }
}
