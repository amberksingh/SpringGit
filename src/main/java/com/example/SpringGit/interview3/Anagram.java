package com.example.SpringGit.interview3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Anagram {

    //🔤 Anagram — simple explanation
    //
    //An anagram is when two words or phrases are made from the same characters, used the same number of times, but arranged in a different order.
    //
    //✅ Examples
    //
    //listen ↔ silent
    //
    //evil ↔ vile
    //
    //race ↔ care
    //
    //Dormitory ↔ Dirty room (ignoring spaces & case)

    static void isAnagramCharArray(String str1, String str2) {

        System.out.println("char Array original str1 = " + str1);
        System.out.println("char Array original str2 = " + str2);
        char[] arr1 = str1.toLowerCase().toCharArray();
        char[] arr2 = str2.toLowerCase().toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        if (Arrays.equals(arr1, arr2))
            System.out.println("Anagram");
        else
            System.out.println("NOT Anagram");
        System.out.println("===========");
    }

    static void isAnagramStreams(String str1, String str2) {

        System.out.println("Streams original str1 = " + str1);
        System.out.println("Streams original str2 = " + str2);
        String collect1 = Arrays.stream(str1.toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        String collect2 = Arrays.stream(str2.toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        if (collect1.equals(collect2))
            System.out.println("Anagram");
        else
            System.out.println("NOT Anagram");
        System.out.println("===========");

    }



    public static void main(String[] args) {

        String s1 = "listen";
        String s2 = "silent";
        isAnagramCharArray(s1, s2);
        isAnagramStreams(s1, s2);

        s1 = "evils";
        s2 = "vile";
        isAnagramCharArray(s1, s2);
        isAnagramStreams(s1, s2);
    }
}
