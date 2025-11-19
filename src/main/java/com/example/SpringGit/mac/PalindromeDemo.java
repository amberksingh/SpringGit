package com.example.SpringGit.mac;

public class PalindromeDemo {

    static boolean stringPalindrome(String s) {

        String reduce = s.chars()
                .mapToObj(Character::toString)
                .reduce((c1, c2) -> c2 + c1)
                .orElse("null");
        System.out.println("reduce = " + reduce);

        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            rev = rev.concat(Character.toString(s.charAt(i)));
        }
        System.out.println("rev concat :" + rev);
        return s.equals(rev);
    }

    static boolean numberPalindrome(int number) {

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

        String stringResult = stringPalindrome("vikram") ? "Palindrome" : "NOT Palindrome";
        System.out.println("stringResult = " + stringResult);
        System.out.println();

        String numberResult = numberPalindrome(8547) ? "Palindrome" : "NOT Palindrome";
        System.out.println("numberResult = " + numberResult);
        System.out.println();

        String stringReduce = stringPalindromeMethod("vikram") ? "Palindrome" : "NOT Palindrome";
        System.out.println("stringReduce = " + stringReduce);
        System.out.println();

    }
}
