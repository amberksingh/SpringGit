package com.example.SpringGit.code;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoodBadString {

    //Given a string s, return true if s is a "good" string, or false otherwise.
    //A string s is good if all characters that appear in s have the same number of
    //occurrences (i.e., the same frequency).

    static void usingCollectionFrequency(String s) {

        System.out.println("\nusingCollectionFrequency : ");
        List<String> list = Arrays.stream(s.split("")).toList();
        LinkedHashSet<Integer> collect = Stream.of(s.split(""))
                .map(c -> Collections.frequency(list, c))
                .collect(
                        //Collectors.toCollection(() -> new LinkedHashSet<>())
                        Collectors.toCollection((LinkedHashSet::new))
                );
        if (collect.size() == 1)
            System.out.println(s + ": Good String");
        else
            System.out.println(s + ": Bad String");
    }

    static void checkGoodBad(String s) {

        System.out.println("\ncheckGoodBad : ");
        Set<Long> collect = Stream.of(s.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .values()
                .stream()
                .collect(Collectors.toSet());
        if (collect.size() == 1)
            System.out.println(s + ": Good String");
        else
            System.out.println(s + ": Bad String");

    }

    public static void main(String[] args) {

        String str = "hheelloo";
        String str1 = "hhelloo";
        String str2 = "hannah";
        String str3 = "night";//good string as 1 is freq of every char here also
        checkGoodBad(str);
        checkGoodBad(str1);
        checkGoodBad(str2);
        checkGoodBad(str3);

        usingCollectionFrequency(str);
        usingCollectionFrequency(str1);
        usingCollectionFrequency(str2);
        usingCollectionFrequency(str3);

    }
}
