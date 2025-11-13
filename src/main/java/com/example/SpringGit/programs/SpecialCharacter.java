package com.example.SpringGit.programs;

public class SpecialCharacter {

    public static void main(String[] args) {

        String str = "bE$ _\"=+g%3";
        System.out.println("Original string : " + str);
        char[] arr = str.toCharArray();
        System.out.println("Special characters : ");
        for (int i = 0; i < arr.length; i++) {
            if (!(Character.isWhitespace(arr[i]) || Character.isAlphabetic(arr[i]) || Character.isDigit(arr[i]))) {
                System.out.print(arr[i]);
            }
        }
        System.out.println();
        System.out.println("val = "+Character.isAlphabetic(65));//true//can pass integer which will be checked as unicode
        System.out.println("val = "+Character.isAlphabetic('A'));//true, same as above
        System.out.println("val = "+Character.isDigit(65));//false
        System.out.println("val = "+Character.isWhitespace(65));//false
    }
}
