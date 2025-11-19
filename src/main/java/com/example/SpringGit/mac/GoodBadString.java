package com.example.SpringGit.mac;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoodBadString {

    //Given a string s, return true if s is a "good" string, or false otherwise.
    //A string s is good if all characters that appear in s have the same number of
    //occurrences (i.e., the same frequency).
    static void isGoodOrBadString(String str) {

        Set<Long> collect = Stream.of(str.split(""))
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
            System.out.println(str+" is Good String");
        else
            System.out.println(str+" is Bad String");
    }


    public static void main(String[] args) {

        String str = "hheelloo";//good
        String str1 = "hhelloo";//bad
        String str2 = "hannah";//good
        String str3 = "night";//good string as 1 is freq of every char here also

        isGoodOrBadString(str);
        isGoodOrBadString(str1);
        isGoodOrBadString(str2);
        isGoodOrBadString(str3);


    }
}
