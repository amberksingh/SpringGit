package com.example.SpringGit.interview3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoodBadString {

    //Given a string s, return true if s is a "good" string, or false otherwise.
    //A string s is good if all characters that appear in s have the same number of
    //occurrences (i.e., the same frequency).

    static void checkGoodBad(String s) {
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
            System.out.println("checkGoodBad : Good String");
        else
            System.out.println("checkGoodBad : Bad String");
    }

    static void usingCollectionFrequency(String s) {
        List<String> list = Arrays.stream(s.split(""))
                .toList();
        HashSet<Integer> collect = Arrays.stream(s.split(""))
                .map(n -> Collections.frequency(list, n))
                .collect(
                        Collectors.toCollection(
                                HashSet::new
                        )
                );
        if (collect.size() == 1)
            System.out.println("usingCollectionFrequency : Good String");
        else
            System.out.println("usingCollectionFrequency : Bad String");
    }

    public static void main(String[] args) {

        List<Object> objects = List.of("abc", "def");
        Object[] array = objects.toArray(new Object[0]);
        Object[] array1 = objects.toArray(new Object[objects.size()]);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array1));

        String str = "hheelloo";
        String str1 = "hhelloo";
        String str2 = "wooden";
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
