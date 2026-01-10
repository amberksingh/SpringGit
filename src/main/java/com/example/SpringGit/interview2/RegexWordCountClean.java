package com.example.SpringGit.interview2;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class RegexWordCountClean {

    public static void main(String[] args) {

        //String str = "hello ,world . java space branch hello nick feature branch edit world";
        String str1 = " hello ,world . java space branch hello nick feature branch edit world ";
        System.out.println("str =" + str1);

        String cleaned = str1.toLowerCase()
                .replaceAll("[^a-zA-Z ]", " "); // replace punctuation with space
        System.out.println("cleaned =" + cleaned);

        Map<String, Long> counts = Arrays.stream(cleaned.split("\\s+")) // split on 1+ whitespace
                .filter(s -> !s.isBlank()) // safety (in case string starts/ends with spaces)
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                ));

        System.out.println("Counts : " + counts);

        //
        //split("\\w+")
        //👉 removes all words
        //👉 returns only what lies between words
        //⚠️ Rule to remember forever
        //You split by	You REMOVE	You GET
        //\\w+	words	symbols
        //\\W+	symbols	words
        String str = "hello_world, java8.0 is fun!";
        String[] wordsValid = str.split("\\w+");//[a-zA-Z0-9_] -> [, , , .,  ,  , !]
        //["", ", ", ".", " ", " ", "!"]
        System.out.println("\\w+ -> "+Arrays.toString(wordsValid));
        System.out.println("\\w+ -> "+wordsValid.length);

        String[] wordsInvalid = str.split("\\W+");//[^a-zA-Z0-9_] -> [hello_world, java8, 0, is, fun]
        System.out.println("\\W+ -> "+Arrays.toString(wordsInvalid));
        System.out.println("\\W+ -> "+wordsInvalid.length);

    }
}
