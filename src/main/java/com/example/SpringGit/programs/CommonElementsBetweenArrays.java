package com.example.SpringGit.programs;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonElementsBetweenArrays {

    public static void main(String[] args) {

        int arr1[] = {1, 2, 3, 4, 5, 6, 6};
        int arr2[] = {1, 2, 9, 0, 0, 6};

        Set<Integer> set1 = Arrays.stream(arr1)
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(arr2)
                .boxed()
                .collect(Collectors.toSet());
        set1.retainAll(set2);
        System.out.println("CommonElementsBetweenArrays = " + set1);

        Set<Integer> set = Arrays.stream(arr1)
                .boxed()
                .collect(Collectors.toSet());
        List<Integer> list = Arrays.stream(arr2)
                //.filter(n -> set.contains(n))
                .filter(set::contains)
                .boxed()
                .toList();
        System.out.println("CommonElementsBetweenArrays = " + list);


    }
}
