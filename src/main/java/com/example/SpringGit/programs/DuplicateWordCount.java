package com.example.SpringGit.programs;

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
        
        //using Collectors.groupingBy
        Map<String, Long> collect = Stream.of(words)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );
        System.out.println("words with frequencies = " + collect);
        
        List<String> list = collect.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Duplicate words : " + list);
        
        //using Collections.frequency
        List<String> list1 = Arrays.stream(words).toList();
        List<String> dupWords = list1.stream()
                .distinct()
                .filter(word -> Collections.frequency(list1, word) > 1)
                .toList();
        System.out.println("dupWords = " + dupWords);


    }
}
