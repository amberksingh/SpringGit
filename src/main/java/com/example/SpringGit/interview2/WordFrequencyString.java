package com.example.SpringGit.interview2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyString {

    public static void main(String[] args) {

        String str = "hello world java space branch hello nick feature branch edit world";
        Map<String, Long> collect = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.groupingBy(
                                x -> x,
                                Collectors.counting()
                        )
                );

        System.out.println("groupingBy : ");
        collect.forEach((key, value) -> System.out.println(key + " -> " + value));

        //toMap
        LinkedHashMap<String, Integer> collect1 = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                val -> 1,
                                Integer::sum,
                                LinkedHashMap::new
                        )
                );
        System.out.println("toMap : ");
        collect1.entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        //oldSkool
        Map<String, Integer> map = new HashMap<>();
        for (String word : str.split(" ")) {
            if (map.containsKey(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            } else {
                map.put(word, 1);
            }
        }

        System.out.println("oldSkool : ");
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey()+" -> "+next.getValue());
        }

    }
}
