package com.example.SpringGit.mac;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class CommonElementsBetweenArrays {

    public static void main(String[] args) {

        int arr1[] = {1, 2, 3, 4, 5, 6, 6};
        int arr2[] = {1, 2, 9, 0, 0, 6};

        HashSet<Integer> set1 = Arrays.stream(arr1)
                .boxed()
                ///.collect(Collectors.toSet());
                .collect(Collectors.toCollection(HashSet::new));
        set1.retainAll(Arrays.stream(arr2).boxed().toList());
        System.out.println("common = " + set1);

        //
        IntFunction<Integer[]> intFunction = Integer[]::new;
        Set<Integer> collect = Arrays.stream(arr1)
                .boxed()
                .collect(Collectors.toSet());
        Integer[] array = Arrays.stream(arr2)
                .filter(collect::contains)
                .boxed()
                .toArray(intFunction);
        System.out.println("common = " + Arrays.toString(array));
    }
}
