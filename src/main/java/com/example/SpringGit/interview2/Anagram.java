package com.example.SpringGit.interview2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagram {

    private static boolean isAnagram(String s1, String s2) {

        char[] arr1 = s1.toLowerCase().toCharArray();
        char[] arr2 = s2.toLowerCase().toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    private static boolean isAnagramUsingStreams(String s1, String s2) {
        return Stream.of(s1.toLowerCase().split(""))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining())
                .equals(
                        Stream.of(s2.toLowerCase().split(""))
                                .sorted(Comparator.naturalOrder())
                                .collect(Collectors.joining())
                );

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
