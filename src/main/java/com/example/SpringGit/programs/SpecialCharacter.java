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
    }
}
