package com.example.SpringGit.interview3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyString {

    public static void main(String[] args) {

        String str = "hello world java space branch hello nick feature branch edit world";
        //groupingBy
        Map<String, Long> collect = Stream.of(str.split("\\s+"))
                .collect(
                        Collectors.groupingBy(
                                word -> word,
                                Collectors.counting()
                        )
                );
        System.out.println("groupingBy = " + collect);

        //toMap
        Map<String, Integer> collect1 = Stream.of(str.split("\\s+"))
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                value -> 1,
                                Integer::sum
                        )
                );
        System.out.println("toMap = " + collect1);

        //oldSkool
        Map<String, Integer> map = new HashMap<>();
        for (String word : str.split("\\s+")) {
            if (map.containsKey(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            } else {
                map.put(word, 1);
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
