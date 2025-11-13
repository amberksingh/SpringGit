package com.example.SpringGit.programs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterAndWordFrequency {

    public static void main(String[] args) {

        String str = "hello guys world right west hello good country world west";

        System.out.println("Word frequency :");
        Arrays.stream(str.split(" "))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        //old way
        String[] words = str.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            if (map.containsKey(s)) {
                map.put(s, map.getOrDefault(s, 1) + 1);
            } else {
                map.put(s, 1);
            }
        }
        System.out.println("Old skool way : ");
        map.forEach((key, value) -> System.out.println(key + " -> " + value));

        //toMap
        LinkedHashMap<String, Integer> collect = Stream.of(words)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                val -> 1,
                                Integer::sum,
                                LinkedHashMap::new
                        )
                );
        System.out.println("Using toMap :");
        Iterator<Map.Entry<String, Integer>> itr = collect.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> next = itr.next();
            System.out.println(next.getKey() + " -> " + next.getValue());
        }

        System.out.println("Printed using Set<Map.Entry<String, Integer>> entrySet = collect.entrySet() : ");
        Set<Map.Entry<String, Integer>> entrySet = collect.entrySet();
        for (Map.Entry<String, Integer> e : entrySet) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}
