package com.example.SpringGit.programs;

public class PalindromeDemo {

    private static boolean stringPalindromeMethod(String input) {

        String ored = input.chars()
                .mapToObj(Character::toString)
                .reduce((c1, c2) -> c2 + c1)
                .orElse(null);
        return ored.equals(input);
    }

    private static boolean numberPalindrome(int number) {

        int temp = number;//1241
        int sum = 0;
        while (number > 0) {
            int r = number % 10;//1//4//2//1
            sum = (sum * 10) + r;//1//14//142//1421
            number /= 10;//124//12//1
        }
        System.out.println("sum = " + sum);

        return temp == sum;

    }


    private static boolean stringPalindrome(String input) {

        StringBuffer buffer = new StringBuffer(input);
        return buffer.reverse().toString().equals(input);
    }

    public static void main(String[] args) {

        String stringResult = PalindromeDemo.stringPalindrome("Arun") ? "Palindrome" : "NOT Palindrome";
        System.out.println("stringResult = " + stringResult);
        System.out.println();

        String numberResult = PalindromeDemo.numberPalindrome(1221) ? "Palindrome" : "NOT Palindrome";
        System.out.println("numberResult = " + numberResult);
        System.out.println();

        String stringReduce = PalindromeDemo.stringPalindromeMethod("hannah") ? "Palindrome" : "NOT Palindrome";
        System.out.println("stringReduce = " + stringReduce);
        System.out.println();

    }


}
