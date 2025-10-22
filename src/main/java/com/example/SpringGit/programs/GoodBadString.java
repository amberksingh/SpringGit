package com.example.SpringGit.programs;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoodBadString {

    //Given a string s, return true if s is a "good" string, or false otherwise.
    //A string s is good if all characters that appear in s have the same number of
    //occurrences (i.e., the same frequency).
    public static void main(String[] args) {

        String str = "hheelloo";
        String str1 = "hhelloo";
        String str2 = "hannah";
        String str3 = "night";//good string as 1 is freq of every char here also

        Set<Long> collect = Stream.of(str3.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
        if (collect.size() == 1)
            System.out.println("Good String");
        else
            System.out.println("Bad String");
    }
}
