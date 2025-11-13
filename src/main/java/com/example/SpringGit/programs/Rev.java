package com.example.SpringGit.programs;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Country {
    int rank;
    String name;
    String continent;

    double gdp;

    public Country(int rank, String name, String continent, double gdp) {
        this.rank = rank;
        this.name = name;
        this.continent = continent;
        this.gdp = gdp;
    }

    @Override
    public String toString() {
        return "Country{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", gdp=" + gdp +
                '}';
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public double getGdp() {
        return gdp;
    }
}

public class Rev {

    public static void main(String[] args) {

        //number list//
        List<Integer> numList = new ArrayList<>();
        numList.add(20);
        numList.add(10);
        numList.add(30);
        numList.add(27);
        numList.add(52);
        numList.add(48);
        numList.add(66);

        ToIntFunction<Integer> intFunction = i -> i;
        Integer integer = numList.stream()
                .max(Comparator.comparingInt(intFunction))
                .orElse(0);
        System.out.println("integer = " + integer);

        List<Country> countries = List.of(
                new Country(2, "China", "Asia", 50000D),
                new Country(3, "India", "Asia", 35000D),
                new Country(1, "Usa", "North America", 70000D),
                new Country(1, "Russia", "Europe", 40000D),
                new Country(4, "Russia", "Asia", 48000D)
        );

        //merge scenario in toMap
        List<String> names = List.of("arun", "tarun", "sharma", "orry", "vivek", "tarun");
        Map<String, Integer> collect = names.stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                String::length,
                                (oldVal, newVal) -> oldVal
                        )
                );
        collect.entrySet()
                .stream()
                .forEach((entry) -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        Map<String, List<Country>> collect1 = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent
                        )
                );
        collect1.forEach((k, v) -> System.out.println(k + " -> " + v));

        Map<String, List<String>> collect2 = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.mapping(
                                        Country::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("collect2 = " + collect2);

        Map<String, Long> collect3 = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.counting()
                        )
                );
        System.out.println("collect3 = " + collect3);

        Predicate<Country> predicate = (c) -> c.getContinent().equalsIgnoreCase("Asia");

        Map<Boolean, Integer> collect4 = countries.stream()
                .collect(
                        Collectors.partitioningBy(
                                predicate,
                                Collectors.summingInt(Country::getRank)
                        )
                );
        System.out.println("collect4 = " + collect4);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("asia", collect4.getOrDefault(true, 0));
        map.put("non-asia", collect4.getOrDefault(true, 0));
        System.out.println("map = " + map);

        List<String> list = List.of("arun", "varun", "rahul", "lamba", "punter", "arun", "samarth", "faizal", "samarth");
        List<String> list1 = list.stream()
                //.sorted(Comparator.naturalOrder())
                //.sorted(Comparator.comparing(x -> x))
                //.sorted(Comparator.comparing(Function.identity()))
                //.sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                .sorted(String::compareToIgnoreCase)
                .toList();
        System.out.println("list1 = " + list1);

        //primitive stream
        Integer[] intStream = new Integer[]{1, 8, 4, 9, 4, 45, 8, 7, 22};
        //Integer[] arr = new Integer[10];

        OptionalDouble average = Stream.of(intStream)
                .mapToInt(Integer::intValue)
                .average();
        if (average.isPresent())
            System.out.println("average.getAsDouble() = " + average.getAsDouble());

        String str = "hello world";
        Optional<String> reduce = str.chars()
                .mapToObj(Character::toString)
                .reduce((c1, c2) -> c2 + c1);
        reduce.ifPresent(System.out::println);

        List<Character> l = str.chars()
                .mapToObj(i -> (char) i)
                .toList();
        System.out.println("l = " + l);

        //Reverse the order of the words in the string
        //op : world hello
        String ored = Arrays.stream(str.split(" "))
                .reduce((s1, s2) -> s2 + " " + s1)
                .orElseGet(() -> "null");
        System.out.println("ored = " + ored);

        String collect5 = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                word -> {
                                    word.sort(Comparator.reverseOrder());
                                    return String.join(" ", word);
                                }

                        )
                );
        System.out.println("collect5 = " + collect5);


    }
}
