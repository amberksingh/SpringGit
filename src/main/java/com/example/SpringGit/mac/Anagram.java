package com.example.SpringGit.mac;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagram {

    static boolean isAnagram(String str1, String str2) {

        char[] charArray1 = str1.toLowerCase().toCharArray();
        char[] charArray2 = str2.toLowerCase().toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2);
    }

    static boolean isAnagramUsingStreams(String s1, String s2) {

        String collect1 = Stream.of(s1.toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        String collect2 = Stream.of(s2.toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        return collect1.equals(collect2);
    }

    public static void main(String[] args) {

        String s1 = "teacHER";
        String s2 = "ReaTche";

        if (isAnagram(s1, s2)) {
            System.out.println("ANAGRAM USING array and chararray");
        } else {
            System.out.println("NOT ANAGRAM USING array and chararray");
        }

        if (isAnagramUsingStreams(s1, s2)) {
            System.out.println("ANAGRAM USING streams");
        } else {
            System.out.println("NOT ANAGRAM USING streams");
        }

    }
}
