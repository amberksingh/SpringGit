package com.example.SpringGit.programs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Anagram {

    static boolean isAnagram(String s1, String s2) {

        char[] array1 = s1.toLowerCase().toCharArray();
        char[] array2 = s2.toLowerCase().toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        return Arrays.equals(array1, array2);
    }

    static boolean isAnagramStreamWay(String s1, String s2) {

        String str1 = Arrays.stream(s1.toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        String str2 = Arrays.stream(s2.toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        return str1.equals(str2);
    }

    public static void main(String[] args) {

        String s1 = "TeAcher";
        String s2 = "cheatER";
        if (isAnagram(s1, s2))
            System.out.println("Anagram using toCharArray");
        else
            System.out.println("NOT Anagram using toCharArray");

        //
        if (isAnagramStreamWay(s1, s2))
            System.out.println("Anagram using streams");
        else
            System.out.println("NOT Anagram using streams");
    }
}
