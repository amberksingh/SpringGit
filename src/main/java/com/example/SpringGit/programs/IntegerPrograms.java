package com.example.SpringGit.programs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntegerPrograms {

    public static void main(String[] args) {

        //Find the count/sum/average/minimum/maximum of integers
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        IntSummaryStatistics intSummaryStatistics = Arrays.stream(arr)
                .summaryStatistics();
        System.out.println("count = " + intSummaryStatistics.getCount());
        System.out.println("sum = " + intSummaryStatistics.getSum());
        System.out.println("average = " + intSummaryStatistics.getAverage());
        System.out.println("minimum = " + intSummaryStatistics.getMin());
        System.out.println("maximum = " + intSummaryStatistics.getMax());

        //square of every number
        List<Double> list = Arrays.stream(arr)
                .mapToObj(n -> Math.pow(n, 2))
                .toList();
        System.out.println("square list = " + list);

        //Find the product of all elements in the array
        int product = Arrays.stream(arr)
                .reduce(1, (x1, x2) -> x1 * x2);
        System.out.println("product = " + product);

        //Partition the array into two lists: one with even numbers and another with odd numbers
        Map<Boolean, List<Integer>> collect = Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.partitioningBy(
                                n -> n % 2 == 0
                        )
                );
        Map<String, List<Integer>> evenOdd = new HashMap<>();
        evenOdd.put("even", collect.get(true));
        evenOdd.put("odd", collect.get(false));

        System.out.println("evenOdd = " + evenOdd);

        evenOdd.forEach((key, value) -> System.out.println(key + " -> " + value));

        evenOdd.entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));


        Collection<List<Integer>> values = evenOdd.values();
        List<Integer> list1 = values.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("flatmap of values = " + list1);

        //Group elements by their remainder when divided by 3?
        Map<Integer, List<Integer>> collect1 = IntStream.of(arr)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                num -> num % 3
                        )
                );
        System.out.println("collect1 = " + collect1);

        //Calculate sum of numeric values in a string
        String numString = "384";
        int sum = numString.chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println("sum = " + sum);

        Optional<Integer> reduce = Stream.of(numString.split(""))
                .map(Integer::parseInt)
                .reduce(Integer::sum);
        if (reduce.isPresent())
            System.out.println("Sum using reduce : "+reduce.get());

        int sum1 = Stream.of(numString.split(""))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("sum1 = " + sum1);

    }
}
