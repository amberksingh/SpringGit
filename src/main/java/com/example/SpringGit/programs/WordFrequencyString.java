package com.example.SpringGit.programs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyString {

    public static void main(String[] args) {

        String str = "hello world java space hello nick feature branch edit";
        String[] words = str.split(" ");

        //old skool way
        System.out.println("old skool way : ");
        HashMap<String, Long> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.getOrDefault(word, 1L) + 1);
            } else {
                map.put(word, 1L);
            }
        }
        Iterator<Map.Entry<String, Long>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> next = iterator.next();
            System.out.println(next.getKey() + " -> " + next.getValue());
        }


        Map<String, Long> collect = Stream.of(words)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );
        System.out.println("Word Frequency = " + collect);

        //toMap
        Map<String, Integer> collect1 = Stream.of(words)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                value -> 1,
                                Integer::sum,
                                LinkedHashMap::new
                        )
                );
        System.out.println("Word Frequency = " + collect1);
    }
}
