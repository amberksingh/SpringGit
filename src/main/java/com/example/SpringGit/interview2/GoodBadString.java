package com.example.SpringGit.interview2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoodBadString {

    //Given a string s, return true if s is a "good" string, or false otherwise.
    //A string s is good if all characters that appear in s have the same number of
    //occurrences (i.e., the same frequency).

    private static void checkGoodBad(String str3) {

        HashSet<Long> collect = Stream.of(str3.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .values()
                .stream()
                .collect(
                        Collectors.toCollection(HashSet::new)
                );
        if (collect.size() == 1)
            System.out.println("checkGoodBad : Good String");
        else
            System.out.println("checkGoodBad : Bad String");
    }

    private static void usingCollectionFrequency(String str) {

        List<String> list = Stream.of(str.split(""))
                .toList();
        Set<Integer> collect = Arrays.stream(str.split(""))
                .map(c -> Collections.frequency(list, c))
                .collect(Collectors.toSet());
        if (collect.size() == 1)
            System.out.println("usingCollectionFrequency : Good String");
        else
            System.out.println("usingCollectionFrequency : Bad String");
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
