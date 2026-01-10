package com.example.SpringGit.interview2;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PalindromeDemo {

    static boolean isPalindrome(String str) {

        String[] split = str.split("");
        String reverse = //Arrays.stream(split)//this also works
                Stream.of(split)
                .reduce((s1, s2) -> s2 + s1)
                .orElse(null);
        System.out.println("rev reduce = " + reverse);
        return str.equals(reverse);
    }

    static boolean isPalindromeMethod(String str) {

        //works only for string words case. not reverse individual chars in single word
        String[] split = str.split("");
        String rev1 =
                Stream.of(str)
                //Stream.of(split) -> gives one one character in case of one word scenario here and reversing them doesn't make sense
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .collect(Collectors.joining());
        System.out.println("rev1 = " + rev1);
        
        StringBuffer sb = new StringBuffer(str);
        String rev = sb.reverse().toString();
        System.out.println("rev stringbuffer = " + rev);
        return rev.equals(str);
    }

    static boolean numberWay(int number) {
        int rev = 0;
        int original = number;
        while (number > 0) {
            int r = number % 10;
            rev = (rev * 10) + r;//-> no + in rev..i.e for sum case similar to armstrong, not here
            number /= 10;
        }
        System.out.println("rev number : " + rev);
        return rev == original;
    }

    public static void main(String[] args) {

        String str = "hannah";
        String str1 = "malayalam";
        String str2 = "abc";

        boolean palindrome = isPalindrome(str2);
        String res = palindrome ? "palindrome" : "NOT palindrome";
        System.out.println(res);

        boolean palindrome1 = isPalindromeMethod(str2);
        String res1 = palindrome1 ? "palindrome" : "NOT palindrome";
        System.out.println(res1);

        int number = 4053;
        int number1 = 5115;
        int number2 = 4004;
        boolean palindrome2 = numberWay(number);
        String res2 = palindrome2 ? "palindrome" : "NOT palindrome";
        System.out.println(res2);

    }
}
