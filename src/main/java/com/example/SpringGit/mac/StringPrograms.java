package com.example.SpringGit.mac;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringPrograms {

    public static void main(String[] args) {

        String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        //Calculate the average length of each word in the string
        double v = Arrays.stream(charStringRepeating.split(" "))
                .mapToInt(String::length)
                .average()
                .orElse(0d);
        System.out.println("v = " + v);

        double i = Stream.of(charStringRepeating.split(" "))
                .collect(
                        Collectors.averagingInt(String::length)
                );
        System.out.println("i = " + i);
        
        //collectingAndThen
        OptionalDouble collect = Stream.of(charStringRepeating.split(" "))
                .map(String::length)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                l -> l.stream().mapToInt(Integer::intValue).average()
                        )
                );
        System.out.println("collect.getAsDouble() = " + collect.getAsDouble());

        String str = "hello world";

        //reverse full string
        String revs = Arrays.stream(str.split(""))
                .reduce((c1, c2) -> c2 + c1)
                .orElse(null);
        System.out.println("revs = " + revs);

        Optional<String> reduce = str.chars()
                .mapToObj(Character::toString)
                .reduce((c1, c2) -> c2 + c1);
        reduce.ifPresent(s -> System.out.println("reduce = " + s));

        List<Character> list = str.chars()
                .mapToObj(s -> (char) s)
                .toList();
        System.out.println("list = " + list);

        str.chars()
                .map(s -> (char)s)//still IntStream
                .forEach(System.out::println);//prints integers/unicode/ascii code still

        //Reverse the order of the words in the string
        //op : world hello
        String wordsRev = Stream.of(str.split(" "))
                .reduce((c1, c2) -> c2 +" "+c1)
                .orElse(null);
        System.out.println("wordsRev = " + wordsRev);
        
        //2nd way
        String collect1 = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                l -> {
                                    l.sort(Comparator.reverseOrder());
                                    //return Collectors.joining(" ");
                                    return String.join(" ", l);
                                }
                        )
                );
        System.out.println("collect1 = " + collect1);

    }
}
