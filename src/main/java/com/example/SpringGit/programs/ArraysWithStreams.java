package com.example.SpringGit.programs;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArraysWithStreams {

    public static void main(String[] args) {

        // Arrays.stream(String)
        String[] fruits = {"apple", "banana", "cherry"};
        System.out.println("fruits array after converting to stream using Arrays.stream(fruits) : ");

        Set<String> collect = Stream.of(fruits)
                .collect(Collectors.toSet());
        System.out.println("collect = " + collect);

        Set<String> collect1 = Arrays.stream(fruits)
                .collect(Collectors.toSet());
        System.out.println("collect1 = " + collect1);

        //Stream.of works with wrapper integer array
        //Stream.of doesn't work with int primitive array
        int[] nums = {7, 11, 9, 1, 2, 3, 4};
        Integer[] wrapperInt = {7, 11, 9, 1, 2, 3, 4};

        List<Integer> list = Arrays.stream(nums)
                .boxed()
                .toList();
        System.out.println("nums = " + list);

        List<int[]> list1 = Stream.of(nums)
                .toList();//gives List of array not list of int
        System.out.println("list1 = " + list1);

        //
        System.out.println("Stream.of(nums) instead of Arrays.stream(). Primitive array int type flattening using flatMap");
        Stream.of(nums)
                .flatMapToInt(x -> Arrays.stream(x))
                .forEach(n -> System.out.print(n+", "));

        System.out.println("Stream.of(nums) instead of Arrays.stream().boxed() . Primitive array int type flattening using flatMap");
        Stream.of(nums)
                .flatMap(x -> Arrays.stream(x).boxed())
                .forEach(n -> System.out.print(n+", "));

        List<Integer> list2 = Stream.of(wrapperInt)
                .toList();
        System.out.println("\nlist2 = " + list2);

        List<Integer> list3 = Arrays.stream(wrapperInt)
                .toList();
        System.out.println("list3 = " + list3);

        int sum = IntStream.of(1, 2, 4, 8, 0, 5, 4)
                .sum();
        System.out.println("sum = " + sum);

        OptionalDouble average = IntStream.concat(
                Arrays.stream(nums),
                Arrays.stream(wrapperInt).mapToInt(Integer::intValue)
        ).average();
        if (average.isPresent())
            System.out.println("average.getAsDouble() = " + average.getAsDouble());
    }
}
