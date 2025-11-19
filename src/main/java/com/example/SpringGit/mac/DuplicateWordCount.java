package com.example.SpringGit.mac;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DuplicateWordCount {

    public static void main(String[] args) {

        String str = "hellO great wheat hello wheat night great people wheat GREAT NIGHT EAGLE feature world EAT";
        String[] words = str.toLowerCase().split(" ");
        Map<String, Long> collect = Stream.of(words)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );
        System.out.println("Duplicate words: ");
        collect.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        //Collections.frequency()
        System.out.println("Duplicate words using Collections.frequency() : ");
        List<String> list = Arrays.stream(words).toList();
        List<String> list1 = Arrays.stream(str.split(" "))
                .filter(word -> Collections.frequency(list, word) > 1)
                .distinct()
                .toList();
        System.out.println("list1 = " + list1);

    }
}
