package com.example.SpringGit.interview3;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PalindromeDemo {

    static boolean isPalindrome(String s) {
        //reduce
        System.out.println("original string = " + s);
        String s1 = Stream.of(s.split(""))
                .reduce((c1, c2) -> c2 + c1)
                .orElse(null);
        System.out.println("rev using reduce = " + s1);
        //System.out.println("=============");
        return s1.equals(s);
    }

    static boolean isPalindromeMethod(String s) {
        System.out.println("original string = " + s);
        String collect = Stream.of(s)
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .collect(Collectors.joining());
        System.out.println("rev using StringBuffer = " + collect);
        //System.out.println("=============");
        return collect.equals(s);
    }

    static boolean numberWay(int number) {

        System.out.println("original number = " + number);
        int temp = number;
        int rev = 0;
        while (number > 0) {
            int r = number % 10;
            rev = (rev * 10) + r;
            number = number / 10;
        }
        System.out.println("rev using oldskool = " + rev);
        return temp == rev;
    }

    public static void main(String[] args) {

        String str = "hannah";
        String str1 = "malayalam";
        String str2 = "abc";

        boolean palindrome = isPalindrome(str1);
        String res = palindrome ? "palindrome" : "NOT palindrome";
        System.out.println(res);
        System.out.println("=============");
        boolean palindrome1 = isPalindromeMethod(str1);
        String res1 = palindrome1 ? "palindrome" : "NOT palindrome";
        System.out.println(res1);
        System.out.println("=============");
        int number = 4053;
        int number1 = 5115;
        int number2 = 4004;
        boolean palindrome2 = numberWay(number2);
        String res2 = palindrome2 ? "palindrome" : "NOT palindrome";
        System.out.println(res2);
    }
}
