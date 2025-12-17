package com.example.SpringGit.mac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyString {

    public static void main(String[] args) {

        String str = "hello world java space branch hello nick feature branch edit world";
        String[] words = str.split(" ");
        Map<String, Long> collect = Arrays.stream((words))
                .collect(
                        Collectors.groupingBy(
                                x -> x,
                                //supplier if needed
                                Collectors.counting()
                        )
                );
        System.out.println("collect = " + collect);

        collect.forEach(
                (key, value) -> System.out.println(key + " -> " + value)
        );

        collect.entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey()+ " -> "+entry.getValue()));

        //toMap
        List<String> list = Arrays.stream(words)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                value -> 1,
                                (oldVal, newVal) -> oldVal + newVal
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("\nlist = " + list);

        //oldSkool
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.getOrDefault(word, 1) + 1);
            } else {
                map.put(word, 1);
            }
        }
        System.out.println("\nmap = " + map);


    }
}
